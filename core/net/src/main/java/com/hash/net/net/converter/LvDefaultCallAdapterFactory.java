package com.hash.net.net.converter;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hash.net.net.utils.Utils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;
import java.util.concurrent.Executor;

import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class LvDefaultCallAdapterFactory extends CallAdapter.Factory {
    private final @Nullable
    Executor callbackExecutor;

    LvDefaultCallAdapterFactory(@Nullable Executor callbackExecutor) {
        this.callbackExecutor = callbackExecutor;
    }

    public static LvDefaultCallAdapterFactory create() {
        return new LvDefaultCallAdapterFactory(new MainThreadExecutor());
    }


    static class MainThreadExecutor implements Executor {
        private final Handler handler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable r) {
            handler.post(r);
        }
    }


    @Override
    public @Nullable
    CallAdapter<?, ?> get(
            @NonNull Type returnType, @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
        if (getRawType(returnType) != Call.class) {
            return null;
        }
        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalArgumentException(
                    "Call return type must be parameterized as Call<Foo> or Call<? extends Foo>");
        }
        final Type responseType = Utils.getParameterUpperBound(0, (ParameterizedType) returnType);

        return new CallAdapter<Object, Call<?>>() {
            @NonNull
            @Override
            public Type responseType() {
                return responseType;
            }

            @NonNull
            @Override
            public Call<Object> adapt(Call<Object> call) {
                return new ExecutorCallbackCall<>(callbackExecutor, call);
            }
        };
    }

    static final class ExecutorCallbackCall<T> implements Call<T> {
        final Executor callbackExecutor;
        final Call<T> delegate;

        ExecutorCallbackCall(Executor callbackExecutor, Call<T> delegate) {
            this.callbackExecutor = callbackExecutor;
            this.delegate = delegate;
        }

        @Override
        public void enqueue(@NonNull final Callback<T> callback) {
            Objects.requireNonNull(callback, "callback == null");
            try {
                Response<T> response = delegate.execute();
                callbackExecutor.execute(() -> {
                    if (delegate.isCanceled()) {
                        Log.e("LvHttp ------> ", "请求取消");
                    } else {
                        callback.onResponse(ExecutorCallbackCall.this, response);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("LvHttp ------> ", "全局异常处理：" + e.toString());
            }

           /* delegate.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Call<T> call, final Response<T> response) {
                    callbackExecutor.execute(() -> {
                        try{
                            if (delegate.isCanceled()) {
                                // Emulate OkHttp's behavior of throwing/delivering an IOException on cancellation.
                                callback.onFailure(ExecutorCallbackCall.this, new IOException("Canceled"));
                            } else {
                                callback.onResponse(ExecutorCallbackCall.this, response);
                            }
                        }catch (Exception e){
                            Log.e("LvHttp ------> ", "全局异常处理：1" + e.toString());
                        }
                    });
                }
                @Override
                public void onFailure(Call<T> call, final Throwable t) {
                    try {
                        callbackExecutor.execute(() -> callback.onFailure(ExecutorCallbackCall.this, t));
                    } catch (Exception e) {
                        Log.e("LvHttp ------> ", "全局异常处理：2" + e.toString());
                    }
                }
            });*/
        }

        @Override
        public boolean isExecuted() {
            return delegate.isExecuted();
        }

        @Override
        public Response<T> execute() throws IOException {
            return delegate.execute();
        }

        @Override
        public void cancel() {
            delegate.cancel();
        }

        @Override
        public boolean isCanceled() {
            return delegate.isCanceled();
        }

        // Performing deep clone.
        @NonNull
        @Override
        public Call<T> clone() {
            return new ExecutorCallbackCall<>(callbackExecutor, delegate.clone());
        }

        @NonNull
        @Override
        public Request request() {
            return delegate.request();
        }

        @Override
        public Timeout timeout() {
            return null;
        }
    }
}

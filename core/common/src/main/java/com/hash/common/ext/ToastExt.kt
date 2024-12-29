package com.hash.common.ext

import android.app.Activity
import android.content.Context
import android.view.Gravity
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hash.common.IApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * @name ToastExt
 * @package com.hash.common.ext
 * @author 345 QQ:1831712732
 * @time 2024/12/19 22:21
 * @description
 */
fun Context.showToast(message: String) {
    CoroutineScope(Dispatchers.Main).launch() {
        val toast = Toast.makeText(this@showToast, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }
}

fun Context.showToastLong(message: String) {
    CoroutineScope(Dispatchers.Main).launch() {
        val toast = Toast.makeText(this@showToastLong, message, Toast.LENGTH_LONG)
        toast.setGravity(android.view.Gravity.CENTER,0,0)
        toast.show()
    }
}

fun Activity.showToast(message: String) {
    CoroutineScope(Dispatchers.Main).launch() {
        val toast =  Toast.makeText(this@showToast, message, Toast.LENGTH_SHORT)
        toast.setGravity(android.view.Gravity.CENTER,0,0)
        toast.show()

    }
}

fun Fragment.showToast(message: String) {
    CoroutineScope(Dispatchers.Main).launch() {
        val toast =  Toast.makeText(this@showToast.context, message, Toast.LENGTH_SHORT)
        toast.setGravity(android.view.Gravity.CENTER,0,0)
        toast.show()
    }
}

fun Context.showToast(message: Int) {
    CoroutineScope(Dispatchers.Main).launch() {
        val toast =  Toast.makeText(this@showToast, message, Toast.LENGTH_SHORT)
        toast.setGravity(android.view.Gravity.CENTER,0,0)
        toast.show()
    }
}

fun Activity.showToast(message: Int) {
    CoroutineScope(Dispatchers.Main).launch() {
        val toast =  Toast.makeText(this@showToast, message, Toast.LENGTH_SHORT)
        toast.setGravity(android.view.Gravity.CENTER,0,0)
        toast.show()
    }
}

fun Fragment.showToast(message: Int) {
    CoroutineScope(Dispatchers.Main).launch() {
        val toast =  Toast.makeText(this@showToast.context, message, Toast.LENGTH_SHORT)
        toast.setGravity(android.view.Gravity.CENTER,0,0)
        toast.show()
    }
}

fun Int.showToast(){
    CoroutineScope(Dispatchers.Main).launch() {
        val toast =   Toast.makeText(IApp.instant, IApp.instant.getString(this@showToast), Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }
}

fun String.showToast(){
    CoroutineScope(Dispatchers.Main).launch() {
        val toast =   Toast.makeText(IApp.instant,this@showToast, Toast.LENGTH_LONG)
        toast.setGravity(android.view.Gravity.CENTER,0,0)
        toast.show()
    }
}
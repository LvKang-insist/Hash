plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
}

android {
    namespace = "com.hash.net"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {}
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:bean"))

    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    //协程基础库
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
    //协程 Android 库，提供 UI 调度器
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
//
//    api("com.github.LvKang-insist:LvHttp:1.2.0")

    // Gson 解析容错：https://github.com/getActivity/GsonFactory
    implementation("com.github.getActivity:GsonFactory:9.6")
    // Json 解析框架：https://github.com/google/gson
    implementation("com.google.code.gson:gson:2.10.1")
    // Kotlin 反射库：用于反射 Kotlin data class 类对象，1.5.10 请修改成当前项目 Kotlin 的版本号
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.10")

    // Test dependencies
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
}
package com.byd.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication: Application() {
    companion object {
        //全局可以获取context
        @SuppressLint("StaticFieldLeak") //忽略“静态字段可能造成内存泄漏”
        lateinit var context: Context //持有的是 applicationContext（生命周期与进程一致，不算泄漏）
        const val API_TOKEN = "SKrLRnn1BsU62nB8" //const 是 Kotlin 里用来声明编译期常量的关键字
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
package com.byd.sunnyweather.logic.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceCreator {
    //单例类：Retrofit对象（全局唯一） Retrofit 构建器的最佳写法(根据传入的接口类，生成该接口的动态代理对象，有了动态代理对象后，就可以随意调用接口中定义的所有方法)

    //1.创建ServiceCreator单例类
    //2.内部定义retorfit对象
    //3.对外提供create()方法，接受Class类型参数
    //4.泛型实化功能：使用inline关键字修饰方法，使用reified关键字来修饰泛型，是泛型实化的两大前提

    private const val BASE_URL = "https://api.caiyunapp.com/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // 返回动态代理对象
    fun <T> create(serviceClass: Class<T>): T{
        return retrofit.create(serviceClass)
    }

    // 泛型实化
    inline fun <reified T> create(): T {
        return create(T::class.java)
    }
}
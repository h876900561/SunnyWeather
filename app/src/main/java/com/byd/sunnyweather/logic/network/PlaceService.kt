package com.byd.sunnyweather.logic.network

import com.byd.sunnyweather.SunnyWeatherApplication
import com.byd.sunnyweather.logic.datamodel.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

//Retrofit 标准接口
interface PlaceService {
    //1.访问地址中的query参数是动态指定的，其他固定不变，因此使用@Query注解方式实现（访问地址格式：https://api.caiyunapp.com/v2/place?query={query}&token={token}lang=zh_CN ）
    //2.返回对象指定为 Call<PlaceResponse>，Retrofit会自动将返回的JSON数据解析成PlaceResponse对象

    @GET("v2/place?token=${SunnyWeatherApplication.API_TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>

    //Retrofit接口方法必须返回 Call<T> 类型，在实际调用过程中会返回该类型对象，通过该对象调用enqueue()方法可以 开启子线程 发起网络请求，并在响应数据回调到enqueue传入的Callback实现中后，回到主线程

}
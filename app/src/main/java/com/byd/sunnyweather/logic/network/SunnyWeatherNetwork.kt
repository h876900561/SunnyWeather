package com.byd.sunnyweather.logic.network

import com.byd.sunnyweather.logic.datamodel.PlaceResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {

    private val placeService = ServiceCreator.create<PlaceService>()

    suspend fun searchPlace(query: String) : PlaceResponse {
        return placeService.searchPlaces(query).await()
    }


    /**
     * 拓展函数，对所有Call类型返回对象统一使用，发起网络请求并接受响应结果
     *
     * */
    private suspend fun <T> Call<T>.await(): T{
        return suspendCoroutine { continuation ->
            //拓展函数，相当于在Call类中添加函数，因此可以直接调用enqueue
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    // Retrofit标准返回结果：调用response.body()方法得到Retrofit解析JSON后的结果对象
                    val res = response.body()
                    if (res != null) {
                        continuation.resume(res)
                    } else {
                        continuation.resumeWithException(RuntimeException("response body is null!"))
                    }
                }

                override fun onFailure(call: Call<T>, throwable: Throwable) {
                    continuation.resumeWithException(throwable)
                }
            })
        }
    }
}
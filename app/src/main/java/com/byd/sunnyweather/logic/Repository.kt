package com.byd.sunnyweather.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.byd.sunnyweather.logic.datamodel.Place
import com.byd.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

//仓库层代码（单例类），用于判断调用方请求是 1.从本地数据源获取数据 or 2.从网络数据源中获取数据
object Repository {
    //TODO:示例demo获取数据每次直接从网络发起请求，可以考虑添加本地缓存，如果本地有缓存数据就从本地获取，否则发起网络请求获取
    const val DEFAULT_TIMEOUT = 5000L
    
    /**
     * lifecycle-livedata-ktx 中的 liveData 是一个 Kotlin 协程构建器（Coroutine Builder），用于在协程中异步地产生或转换 LiveData 的值。它让你可以直接在 liveData { } 代码块里调用挂起函数（suspend function），然后通过 emit 发射结果，而无需手动管理线程或生命周期。
     * @timeoutInMs：超时时间。当 LiveData 变为非活跃状态（例如界面不可见）后，协程不会立即取消，而是会等待这段时间，若期间重新变为活跃则继续执行；超过时限后才真正取消。
     *
     * */
    fun searchPlace(query: String): LiveData<Result<List<Place>>> {
        return liveData(Dispatchers.IO, DEFAULT_TIMEOUT) {
            val result = try {
                val placeResponse = SunnyWeatherNetwork.searchPlace(query)

                if (placeResponse.status == "OK"){
                    val places = placeResponse.places
                    Result.success(places)
                } else {
                    Result.failure(RuntimeException("response status is ${placeResponse.status}"))
                }

            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result) //类似于调用livedata的setValue()方法来通知数据变化
        }
    }
}
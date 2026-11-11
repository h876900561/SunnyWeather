package com.byd.sunnyweather.ui.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.byd.sunnyweather.logic.Repository
import com.byd.sunnyweather.logic.datamodel.Place

class PlaceViewModel: ViewModel() {
    // 2.6.0版本之前是下面的方法，之后可以直接在LiveData对象上调用.switchMap{} 变成了LiveData的扩展函数
    // Transformations.map()方法： 将一个LiveData对象 转换为另外一个LiveData对象，可用于ViewModel内部 整体对象到切片对象的转换
    // Transformations.switchMap()方法； 将转换函数内部（闭包内）调用另外方法从外部获取的LiveData对象 转换为 ViewModel内部一个可观察的LiveData对象，注意调用外部获取的LiveData对象每次获取的实例不是同一个

    private val queryLiveData = MutableLiveData<String>()
    // 1.外界调用传入参数触发livedata变化
    fun searchPlace(query: String) {
        queryLiveData.value = query
    }


    // 2.观察queryLiveData变化，通过switchMap函数实现将 调用仓库层获取的livedata 转换为 可观察的livedata
    val placesListLiveData: LiveData<Result<List<Place>>> = queryLiveData.switchMap { query ->
        Repository.searchPlace(query)  //！！！注意这里必须返回一个 LiveData对象，因为switchMap()方法的原理就是将转换函数中返回的livedata对象转换成可观察的livedata对象；这也是为什么仓库层searchPlaces返回的是一个LiveData对象
    }

    // 3，定义placeList集合，用户对界面上显示的城市数据进行缓存！不是每次query发生变化，都要通过仓库层获取城市信息，可以在ViewModel中缓存 TODO：添加query变化读取缓存的逻辑，当前逻辑是：只要query参数发生变化，就一定会触发网络请求
    private val placeList = ArrayList<Place>()

}
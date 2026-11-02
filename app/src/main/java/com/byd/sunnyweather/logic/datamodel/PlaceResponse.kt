package com.byd.sunnyweather.logic.datamodel

import com.google.gson.annotations.SerializedName


//接口请求返回JSON格式 {"places":[{"formatted_address":"中国 北京市 北京","id":"CY_CN_d9b61a","place_id":"CY_CN_d9b61a","name":"中国 北京市 北京","location":{"lat":39.904989,"lng":116.405285}},
// {"formatted_address":"中国 北京市 延庆","id":"CY_CN_d95423","place_id":"CY_CN_d95423","name":"中国 北京市 延庆","location":{"lat":40.465325,"lng":115.985006}}],"query":"北京","status":"ok"}
data class PlaceResponse(val status: String, val places: List<Place>)

//@SerializedName("formatted_address") 是 Gson 库的注解，用来告诉 Gson：这个 Kotlin/Java 字段在 JSON 里对应的键名是 "formatted_address"，而不是字段本身的名字。
data class Place(val id: String, val name: String, val location: Location, @SerializedName("formatted_address") val address: String, @SerializedName("place_id") val placeId: String)

data class Location(val lang: String, val lat: String)
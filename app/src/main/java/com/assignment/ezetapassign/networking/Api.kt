package com.example.codegamaassignment

import Jsondata
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("android_assignment.json")
    fun getUiData(): Call<Jsondata>

    @GET("android_assignment.json")
    fun getImageData(): Call<Jsondata>

}
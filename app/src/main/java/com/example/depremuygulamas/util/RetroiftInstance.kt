package com.example.depremuygulamas.util

import com.example.depremuygulamas.util.Constant.BASE_URL
import com.example.depremuygulamas.service.EarthquakeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroiftInstance {
   private val retrofit=Retrofit.Builder()
       .baseUrl(BASE_URL)
       .addConverterFactory(GsonConverterFactory.create())
       .build()

    val api= retrofit.create(EarthquakeService::class.java)

}
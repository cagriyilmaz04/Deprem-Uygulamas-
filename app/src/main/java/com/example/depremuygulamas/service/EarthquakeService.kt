package com.example.depremuygulamas.service

import com.example.depremuygulamas.model.Earthquake
import com.example.depremuygulamas.util.Constant.SECOND_PART
import retrofit2.Response
import retrofit2.http.GET

interface EarthquakeService {
    @GET(SECOND_PART)
    suspend fun getData():Response<Earthquake>


}
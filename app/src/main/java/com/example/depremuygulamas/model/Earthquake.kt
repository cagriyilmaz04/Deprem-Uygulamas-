package com.example.depremuygulamas.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.parcelize.RawValue

data class Earthquake (val status:String, @SerializedName("result") val result:List<SubModel>)

data class SubModel (
    val mag:String,
    val lng:Double,
    val lat:Double,
    val lokasyon:String,
    val depth:Double,
    val coordinates:List<Double>,
    val title:String,
    val rev:String,
    val timestamp:Long,
    val date_stamp:String,
    val date:String,
    val hash:String,
    val hash2:String
        )

@Parcelize
data class ParcelableClass(
    val main_data:@RawValue SubModel
):Parcelable
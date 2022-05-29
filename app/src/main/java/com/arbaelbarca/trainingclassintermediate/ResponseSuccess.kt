package com.arbaelbarca.trainingclassintermediate


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseSuccess(
    @SerializedName("message")
    val message: String?
) : Parcelable
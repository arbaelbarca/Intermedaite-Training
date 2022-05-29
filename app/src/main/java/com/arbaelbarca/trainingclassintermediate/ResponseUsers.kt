package com.arbaelbarca.trainingclassintermediate


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class ResponseUsers : ArrayList<ResponseUsers.ResponseUsersItem>() {
    @Parcelize
    data class ResponseUsersItem(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("password")
        val password: String?,
        @SerializedName("username")
        val username: String?
    ) : Parcelable
}
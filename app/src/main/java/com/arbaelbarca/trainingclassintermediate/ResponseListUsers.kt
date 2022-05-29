package com.arbaelbarca.trainingclassintermediate


import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResponseListUsers(
    @SerializedName("data")
    val `data`: List<Data?>?,
    @SerializedName("page")
    val page: Int?,
    @SerializedName("per_page")
    val perPage: Int?,
    @SerializedName("support")
    val support: Support?,
    @SerializedName("total")
    val total: Int?,
    @SerializedName("total_pages")
    val totalPages: Int?
) : Parcelable {
    @Parcelize
    data class Data(
        @SerializedName("avatar")
        val avatar: String?,
        @SerializedName("email")
        val email: String?,
        @SerializedName("first_name")
        val firstName: String?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("last_name")
        val lastName: String?
    ) : Parcelable

    @Parcelize
    data class Support(
        @SerializedName("text")
        val text: String?,
        @SerializedName("url")
        val url: String?
    ) : Parcelable
}
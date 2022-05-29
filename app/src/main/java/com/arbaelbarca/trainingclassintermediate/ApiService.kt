package com.arbaelbarca.trainingclassintermediate

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("users/all")
    fun getAllUsers(

    ): Call<List<ResponseUsers.ResponseUsersItem>>


    @POST("users")
    fun createUsers(
        @Body requestUsers: RequestUsers
    ): Call<ResponseSuccess>

    @PUT("users/{id_users}")
    fun updateUsers(
        @Path("id_users") idUsers: String,
        @Body requestUsers: RequestUsers
    ): Call<ResponseSuccess>

    @DELETE("users/{id_users}")
    fun deleteUsers(
        @Path("id_users") idUsers: String,
    ): Call<ResponseSuccess>
}
package com.gereso.terramaster

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/login")
    fun loginAccount(@Body post:LoginRequest): Call<String>

    @GET("/experts/all")
    fun getAllExperts(): Call<List<FetchAll>>

    @POST("experts/add")
    fun addExperts(@Body expert: ExpertModel): Call<ExpertModel>

    @POST("/signup")
    fun signupAccount(@Body newUser: UserModel): Call<String>

}

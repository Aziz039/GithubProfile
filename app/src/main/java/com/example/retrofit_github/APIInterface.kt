package com.example.retrofit_github

import android.content.SharedPreferences
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIInterface {
    @GET("/users/{username}")
    fun doGetListResources(@Path("username") username: String): Call<resultModel?>?
}
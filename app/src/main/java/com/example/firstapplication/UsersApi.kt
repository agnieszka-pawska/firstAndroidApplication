package com.example.firstapplication

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET

interface UsersApi {

    @GET("/users")
    fun getUsers(): Single<List<User>>
}
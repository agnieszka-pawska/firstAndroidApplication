package com.example.firstapplication

import retrofit2.http.GET

interface UsersApi {

    @GET("/users")
    suspend fun getUsers(): List<User>
}
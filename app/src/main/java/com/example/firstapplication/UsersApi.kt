package com.example.firstapplication

import retrofit2.http.GET
import retrofit2.http.Path

interface UsersApi {

    @GET("/users")
    suspend fun getUsers(): List<User>

    @GET("/user/{userId}/todos")
    suspend fun getToDoList(@Path("userId") userId: String): List<ToDo>
}
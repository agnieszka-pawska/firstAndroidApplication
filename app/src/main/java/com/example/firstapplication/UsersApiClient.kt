package com.example.firstapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UsersApiClient {

    private val usersApiClient: UsersApi = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UsersApi::class.java)

    suspend fun fetchUsers(): List<User> {
        return usersApiClient.getUsers()
    }

    suspend fun fetchToDoList(userId: String): List<ToDo> {
        return usersApiClient.getToDoList(userId)
    }
}
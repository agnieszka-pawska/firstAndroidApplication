package com.example.firstapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.lang.Exception

class UserRepository(
    private val usersApiClient: UsersApiClient
) {

    private var users: MutableLiveData<List<User>> = MutableLiveData(emptyList())

    suspend fun fetchAndUpdateUsers(): List<User> {
        return try {
            val fetchedUsers = usersApiClient.fetchUsers()
            update(fetchedUsers)
            fetchedUsers
        } catch (e: Exception) {
            println("UsersApiClient exception, cause: ${e.cause}")
            val hardcodedUser = listOf(
                User("1234", "user-name", "user-email")
            )
            update(hardcodedUser)
            hardcodedUser
        }
    }

    fun getUsers(): LiveData<List<User>> {
        return users
    }

    fun add(user: User) {
        val currentList = users.value
        if (currentList == null) {
            users.value = listOf(user)
        } else {
            val updatedList = currentList.toMutableList()
            users.value = updatedList + user
        }
    }

    fun remove(userId: String) {
        val currentList = users.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            val userToRemove = updatedList.find { it.id == userId }
            updatedList.remove(userToRemove)
            users.value = updatedList
        }
    }

    private fun update(userList: List<User>) {
        users.value = userList
    }
}

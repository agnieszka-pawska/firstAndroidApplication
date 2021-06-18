package com.example.firstapplication

import androidx.lifecycle.MutableLiveData

class UserRepository {

    private val usersApiClient: UsersApiClient = UsersApiClient()

    private var users: MutableLiveData<List<User>> = MutableLiveData(emptyList())

    fun fetchUsers() {
        usersApiClient.fetchUsers { users -> update(users) }
    }

    fun getUsers(): MutableLiveData<List<User>> {
        return users
    }

    fun add(user: User) {
        val currentList = users.value
        if (currentList == null) {
            users.value = listOf(user)
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(user)
            users.value = updatedList
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

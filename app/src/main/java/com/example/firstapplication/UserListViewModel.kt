package com.example.firstapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserListViewModel: ViewModel() {

    val users: MutableLiveData<List<User>> by lazy {
        MutableLiveData<List<User>>()
    }

    init {
        users.postValue(mutableListOf(
            User("user-id-1", "user-name-1", "user-1-email"),
            User("user-id-2", "user-name-2", "user-2-email"),
            User("user-id-3", "user-name-3", "user-3-email"),
            User("user-id-4", "user-name-4", "user-4-email"),
            User("user-id-5", "user-name-5", "user-5-email")
        ))
    }

    fun add(user: User) {
        val currentList = users.value
        if (currentList == null) {
            users.postValue(listOf(user))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(user)
            users.postValue(updatedList)
        }
    }

    fun remove(userId: String) {
        val currentList = users.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            val userToRemove = updatedList.find { it.id == userId }
            updatedList.remove(userToRemove)
            users.postValue(updatedList)
        }
    }
}
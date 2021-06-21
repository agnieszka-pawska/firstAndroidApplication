package com.example.firstapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class UserListViewModel: ViewModel() {

    private val userRepository: UserRepository = UserRepository()

    init {
        userRepository.fetchUsers()
    }

    fun getUsers(): LiveData<List<User>> {
        return userRepository.getUsers()
    }

    fun add(user: User) {
        userRepository.add(user)
    }

    fun remove(userId: String) {
        userRepository.remove(userId)
    }

    override fun onCleared() {
        userRepository.clearDisposables()
        super.onCleared()
    }
}
package com.example.firstapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserListViewModel: ViewModel() {

    private val userRepository: UserRepository = UserRepository()

    init {
        viewModelScope.launch {
            userRepository.fetchAndUpdateUsers()
        }
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
}
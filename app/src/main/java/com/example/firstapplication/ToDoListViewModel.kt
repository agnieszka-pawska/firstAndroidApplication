package com.example.firstapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async

class ToDoListViewModel(
    private val repository: ToDoListRepository
): ViewModel() {

    fun getToDoList(userId: String): LiveData<List<ToDo>> {
        return MutableLiveData<List<ToDo>>().also { liveData ->
            viewModelScope.async {
                val existingTodos = repository.getToDos(userId)
                if (existingTodos.value.isNullOrEmpty()) {
                    liveData.value = repository.fetchAndUpdateList(userId)
                } else {
                    liveData.value = existingTodos.value
                }
            }
        }
    }
}
package com.example.firstapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.lang.Exception

class ToDoListRepository(
    private val client: UsersApiClient
) {

    private val toDoList: MutableLiveData<List<ToDo>> = MutableLiveData(
        emptyList()
    )

    fun getToDos(userId: String): LiveData<List<ToDo>> {
        val toDos = toDoList.value?.filter { it.userId == userId } ?: emptyList()
        return MutableLiveData(toDos)
    }

    suspend fun fetchAndUpdateList(userId: String): List<ToDo> {
        return try {
            val fetchedToDoList = client.fetchToDoList(userId)
            add(fetchedToDoList)
            fetchedToDoList
        } catch (e: Exception) {
            println("UsersApiClient exception, cause: ${e.cause}")
            val toDos = listOf(
                ToDo("1", "1234", "todo-description-1", false),
                ToDo("2", "1234", "todo-description-2", false),
                ToDo("3", "1234", "todo-description-3", false)
            )
            add(toDos)
            toDos
        }
    }

    private fun add(toDos: List<ToDo>) {
        val currentValue = toDoList.value ?: emptyList()
        toDoList.value = currentValue + toDos
    }
}
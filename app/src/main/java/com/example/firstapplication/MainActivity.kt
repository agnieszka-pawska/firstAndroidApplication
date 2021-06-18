package com.example.firstapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapplication.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val usersViewModel: UserListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userAdapter = UserAdapter(usersViewModel.users.value?.toMutableList() ?: mutableListOf()) { userId -> usersViewModel.remove(userId) }

        val recyclerView = binding.recyclerView
        recyclerView.adapter = userAdapter

        usersViewModel.users.observe(
            this,
            {
                userAdapter.notifyDataSetChanged(it)
            }
        )
        actionOnNewUserButton()
    }

    private fun actionOnNewUserButton() {
        val newUserButton = binding.newUserButton
        newUserButton.setOnClickListener {
            usersViewModel.add(generateNewUser())
        }
    }

    private fun generateNewUser(): User {
        val id = UUID.randomUUID().toString()
        return User(id, "user-${id.take(4)}", "user-${id.take(4)}@gmail.com")
    }
}
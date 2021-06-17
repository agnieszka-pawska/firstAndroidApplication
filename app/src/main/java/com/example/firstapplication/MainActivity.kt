package com.example.firstapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapplication.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var usersViewModel: UserListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerView

        usersViewModel = UserListViewModel()
        usersViewModel.users.observe(
            this,
            { updatedUserList ->
                recyclerView.adapter = UserAdapter(updatedUserList.toMutableList())
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
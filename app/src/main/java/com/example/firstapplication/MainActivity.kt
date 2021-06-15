package com.example.firstapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapplication.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userAdapter = UserAdapter(
            mutableListOf(
                User("user-id-1", "user-name-1", "user-1-email"),
                User("user-id-2", "user-name-2", "user-2-email"),
                User("user-id-3", "user-name-3", "user-3-email"),
                User("user-id-4", "user-name-4", "user-4-email"),
                User("user-id-5", "user-name-5", "user-5-email")
            )
        )
        val recyclerView = binding.recyclerView
        recyclerView.adapter = userAdapter

        val newUserButton = binding.newUserButton
        newUserButton.setOnClickListener {
            userAdapter.add(generateNewUser())
        }
    }

    private fun generateNewUser(): User {
        val id = UUID.randomUUID().toString()
        return User(id, "user-${id.take(4)}", "user-${id.take(4)}@gmail.com")
    }
}
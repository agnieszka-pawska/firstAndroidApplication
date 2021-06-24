package com.example.firstapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapplication.databinding.ActivityMainBinding
import java.util.UUID
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val usersViewModel: UserListViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userAdapter = UserAdapter(
            { userId -> usersViewModel.remove(userId) },
            { user -> openUserDetailsActivity(user) }
        )

        val recyclerView = binding.recyclerView
        recyclerView.adapter = userAdapter

        usersViewModel.getUsers().observe(
            this,
            {
                userAdapter.notifyDataSetChanged(it)
            }
        )
        actionOnNewUserButton()
    }

    private fun openUserDetailsActivity(user: User) {
        val intent = Intent(this, UserDetailsActivity::class.java).apply {
            putExtra(UserDetailsActivity.EXTRA_ID_KEY, user.id)
            putExtra(UserDetailsActivity.EXTRA_NAME_KEY, user.name)
            putExtra(UserDetailsActivity.EXTRA_EMAIL_KEY, user.email)
        }
        startActivity(intent)
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
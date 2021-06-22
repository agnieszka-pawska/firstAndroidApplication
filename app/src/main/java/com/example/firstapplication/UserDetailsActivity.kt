package com.example.firstapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapplication.databinding.UserDetailsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var binding: UserDetailsBinding
    private val viewModel: ToDoListViewModel by viewModel()

    companion object {
        const val EXTRA_ID_KEY = "id"
        const val EXTRA_NAME_KEY = "name"
        const val EXTRA_EMAIL_KEY = "email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toDoListAdapter = ToDoListAdapter()

        val recyclerView = binding.todoItemRecyclerView
        recyclerView.adapter = toDoListAdapter

        val intent = this.intent
        binding.userDetailsName.text = intent.getStringExtra(EXTRA_NAME_KEY)
        binding.userDetailsEmail.text = intent.getStringExtra(EXTRA_EMAIL_KEY)

        viewModel.getToDoList(intent.getStringExtra(EXTRA_ID_KEY) ?: "").observe(
            this,
            {
                toDoListAdapter.notifyDataSetChanged(it)
            }
        )

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}
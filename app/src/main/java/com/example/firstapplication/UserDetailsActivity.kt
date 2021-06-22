package com.example.firstapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapplication.databinding.UserDetailsBinding

class UserDetailsActivity: AppCompatActivity() {

    private lateinit var binding: UserDetailsBinding

    companion object {
        const val EXTRA_NAME_KEY = "name"
        const val EXTRA_EMAIL_KEY = "email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = UserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = this.intent
        binding.userDetailsName.text = intent.getStringExtra(EXTRA_NAME_KEY)
        binding.userDetailsEmail.text = intent.getStringExtra(EXTRA_EMAIL_KEY)

        binding.backButton.setOnClickListener {
            val context = it.context
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }
}
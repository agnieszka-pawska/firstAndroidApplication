package com.example.firstapplication

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.firstapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        changeTextInTextViewWhenClickButton(binding.textView, binding.counterButton)

        setContentView(binding.root)
    }

    private fun changeTextInTextViewWhenClickButton(textView: TextView, button: Button) {
        var counter = 0

        button.setOnClickListener {
            counter++
            textView.text = getString(R.string.counter, counter)
        }
    }
}
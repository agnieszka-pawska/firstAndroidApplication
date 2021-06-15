package com.example.firstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.counter_button)
        val textView: TextView = findViewById(R.id.textView)
        var counter = 0

        button.setOnClickListener(View.OnClickListener {
            counter++
            textView.text = getString(R.string.counter, counter.toString())
        })
    }
}
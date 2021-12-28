package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db)

        val re = findViewById<Button>(R.id.btReturn)

        re.setOnClickListener {
            this.finish()

            return@setOnClickListener
        }
    }
}
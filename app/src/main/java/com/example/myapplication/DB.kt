package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
//import kotlinx.android.synthetic.main.activity_main.*

class DB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db)

        val re = findViewById<Button>(R.id.btReturn)
        val dateTV = findViewById<TextView>(R.id.dateTV)
        //val date = LocalDate.now()
        val date = intent.getStringExtra("date")

        //dateTV.text = SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN).format(date)
        //not sure what is the function of second parameter, Locale.TAIWAN or Locale.getDefault()
        //dateTV.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.TAIWAN).format(Date())
        dateTV.text = date // output: yyyy-MM-dd, cannot format like other form

        re.setOnClickListener {
            this.finish()

            return@setOnClickListener
        }


    }
}


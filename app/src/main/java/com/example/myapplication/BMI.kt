package com.example.myapplication

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.lang.Math.pow
import java.lang.Math.round

class BMI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        //val intent = Intent(this, MainActivity::class.java)
        //val intent = getIntent()
        val back = findViewById<Button>(R.id.btBack)
        val next = findViewById<Button>(R.id.btNext)
        val showName = findViewById<TextView>(R.id.nameShowCase)
        val showAge = findViewById<TextView>(R.id.ageShowCase)
        val result = findViewById<TextView>(R.id.BMIResult)

        val name = intent.getStringExtra("name")
        val age = intent.getIntExtra("age", 18)
        val weight = intent.getIntExtra("weight", 60)
        val height = intent.getIntExtra("height", 180)

        val bmi = round(weight / pow(height/100.0, 2.0))

        println("test $name")

        showName.text = "" + name
        showAge.text = "Age: " + age
        result.text = "Your BMI is " + bmi


        back.setOnClickListener {
            this.finish()

            return@setOnClickListener
        }
        next.setOnClickListener {
            val intent = Intent(this, DB::class.java)

            startActivity(intent)
            this.finish()

            return@setOnClickListener
        }
    }
}
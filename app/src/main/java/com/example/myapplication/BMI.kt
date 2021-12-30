package com.example.myapplication

import android.content.Intent
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Typeface
import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
//import java.lang.Math.pow
//import java.lang.Math.round
import java.time.LocalDate
import java.util.*
import kotlin.math.pow
import kotlin.math.round

class BMI : AppCompatActivity() {
    private lateinit var back: Button //lateinit to void init must have value
    private lateinit var next: Button
    private lateinit var showName: TextView
    private lateinit var showAge: TextView
    private lateinit var result: TextView

    private lateinit var date: Any // Any equal Object, wew
    private lateinit var name: String
    private lateinit var age: String
    private lateinit var weight: String
    private lateinit var height: String
    private lateinit var bmi: String

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)

        //var date = Date()
        //val date = LocalDate.now()

        name = intent.getStringExtra("name").toString()
        age = intent.getStringExtra("age").toString()
        weight = intent.getStringExtra("weight").toString()
        height = intent.getStringExtra("height").toString()

        bmi = (round(weight.toInt() / (height.toInt() / 100.0).pow(2.0) * 100) / 100).toString()

        initView()
        //dbHelper has not been initialized
        dbHelper = DBHelper(this)

        showName.text = name
        showAge.text = "Age: " + age
        result.text = "Your BMI is " + bmi


        back.setOnClickListener {
            this.finish()

            return@setOnClickListener
        }
        next.setOnClickListener {
            //val intent = Intent(this, DB::class.java)

            putData()
            getData()
            //startActivity(intent)


            //this.finish()

            return@setOnClickListener
        }
    }

    private fun putData(){
        date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.TAIWAN).format(Date())
        val data = BMIModel(date = date.toString(), name = name, age = age, bmi = bmi)
        val status = dbHelper.insertBMIData(data)

        if(status > -1){
            Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getData(){
        val bmiList = dbHelper.getAllData()
        //Log.e("datattttttt", "${bmiList.size}")
    }

    private fun initView(){ //init objects here
        back = findViewById<Button>(R.id.btBack)
        next = findViewById<Button>(R.id.btNext)
        showName = findViewById<TextView>(R.id.nameShowCase)
        showAge = findViewById<TextView>(R.id.ageShowCase)
        result = findViewById<TextView>(R.id.BMIResult)
    }
}
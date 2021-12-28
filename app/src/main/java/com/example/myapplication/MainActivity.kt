package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val submit = findViewById<Button>(R.id.btSubmit)
        val name = findViewById<EditText>(R.id.name)
        val age = findViewById<EditText>(R.id.age)
        val weight = findViewById<EditText>(R.id.weight)
        val height = findViewById<EditText>(R.id.height)
        //val nameTV = findViewById<TextView>(R.id.nameTV)

        submit.setOnClickListener {
            if(weight.text.toString() == "" || height.text.toString() == "" || age.text.toString() == ""){
                Toast.makeText(this, "Weight, height or age is empty!", Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }
            if(weight.text.toString().toInt() <= 0 || height.text.toString().toInt() <= 0){
                Toast.makeText(this, "Error input!", Toast.LENGTH_LONG).show()

                return@setOnClickListener
            }

            val intent = Intent(this, BMI::class.java)
            //println("main ${name.toString()}")

            //nameTV.text = name
            intent.putExtra("name", name.text.toString())
            intent.putExtra("age", age.text.toString().toInt())
            intent.putExtra("weight", weight.text.toString().toInt())
            intent.putExtra("height", height.text.toString().toInt())
            startActivity(intent)

            return@setOnClickListener
        }
    }
}
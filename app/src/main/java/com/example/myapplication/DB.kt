package com.example.myapplication

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*
//import kotlinx.android.synthetic.main.activity_main.*

class DB : AppCompatActivity() {
    private lateinit var reBT: Button
    private lateinit var titleTV: TextView

    private lateinit var dbHelper: DBHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: BMIAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_db)

        titleTV = findViewById(R.id.DBtitle)
        reBT = findViewById<Button>(R.id.btReturn)

        dbHelper = DBHelper(this)
        recyclerView = findViewById(R.id.recyclerView)

        //val dateTV = findViewById<TextView>(R.id.dateTV)
        //val date = LocalDate.now()
        //val date = intent.getStringExtra("date")

        //dateTV.text = SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN).format(date)
        //not sure what is the function of second parameter, Locale.TAIWAN or Locale.getDefault()
        //dateTV.text = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.TAIWAN).format(Date())
        //dateTV.text = date // output: yyyy-MM-dd, cannot format like other form

        //initialize RecyclerView here to show data
        titleTV.text = intent.getStringExtra("name").toString() + "'s BMI History"
        initRecyclerView()
        getData()

        reBT.setOnClickListener {
            this.finish()

            return@setOnClickListener
        }


    }

    private fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = BMIAdapter()
        recyclerView.adapter = adapter
    }

    private fun getData(){
        val bmiList = dbHelper.getAllData()
        //Log.e("datattttttt", "${bmiList.size}")
        adapter?.addItem(bmiList)
    }
}


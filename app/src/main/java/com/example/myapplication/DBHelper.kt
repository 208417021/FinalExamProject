package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import java.lang.Exception

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{ //still dunno it, probably some kinda construct?
        private const val DATABASE_NAME = "bmi.db"
        private const val DATABASE_VERSION = 1
        private const val TBL_BMI = "tbl_bmi"
        private const val ID = "id"
        private const val DATE = "date"
        private const val NAME = "name"
        private const val AGE = "age"
        private const val BMI = "bmi"
    }

    override fun onCreate(db: SQLiteDatabase?) { //idk what the fuck is this API, hey there is question mark
        val createTblBMI = ("CREATE TABLE $TBL_BMI($ID INTEGER PRIMARY KEY,$DATE TEXT,$NAME TEXT,$AGE TEXT,$BMI TEXT)")
        //val createTblBMI = ("CREATE TABLE " + TBL_BMI + "(" + ID + " INTEGER PRIMARY KEY," + DATE + " TEXT," + NAME + " TEXT," + AGE + " TEXT," + BMI + " TEXT" + ")")
        //CREATE TABLE tbl_bmi (id, date, name, age, bmi), probably it's SQL syntax

        db?.execSQL(createTblBMI) //can't figure out what the fuck is this
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_BMI") //SQL API again, fuck my life
        onCreate(db)
    }

    fun insertBMIData(data: BMIModel): Long{
        val db = this.writableDatabase //dunno what is writableDatabase
        val contentValues = ContentValues() //android API here

        //finally I can understand a thing here
        contentValues.put(ID, data.id)
        contentValues.put(DATE, data.date)
        contentValues.put(NAME, data.name)
        contentValues.put(AGE, data.age)
        contentValues.put(BMI, data.bmi)
        Log.e("goiengioergnior", "$contentValues")
        val success = db.insert(TBL_BMI, null, contentValues)
        db.close()
        return success
    }

    fun getAllData(): ArrayList<BMIModel>{
        val bmiList: ArrayList<BMIModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_BMI"
        val db = this.readableDatabase //dunno what is readableDatabase, SQLite API anyway

        val cursor: Cursor // dunno what is cursor

        try{
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)

            return ArrayList()
        }

        var id: Int
        var date: String
        var name: String
        var age: String
        var bmi: String

        if(cursor.moveToFirst()){
            do{ // fetch data from here
                //Log.e("GETTF: ", "${cursor.getColumnIndex("id")} ${cursor.getColumnIndex("date")} ${cursor.getColumnIndex("name")} ${cursor.getColumnIndex("age")} ${cursor.getColumnIndex("bmi")}")
                //id = cursor.getInt(cursor.getColumnIndex("id"))
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                //date = cursor.getString(cursor.getColumnIndex("date"))
                date = cursor.getString(cursor.getColumnIndexOrThrow("date"))
                //name = cursor.getString(cursor.getColumnIndex("name"))
                name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                //age = cursor.getString(cursor.getColumnIndex("age"))
                age = cursor.getString(cursor.getColumnIndexOrThrow("age"))
                //bmi = cursor.getString(cursor.getColumnIndex("bmi"))
                bmi = cursor.getString(cursor.getColumnIndexOrThrow("bmi"))

                val data = BMIModel(id = id, date = date, name = name, age = age, bmi = bmi)
                bmiList.add(data) //use arraylist too store data of model
                //Log.e("cursor: ", "${cursor.moveToNext()}")
            }while(cursor.moveToNext())
        }
        //Log.e("bmiList: ", "${bmiList.size}")
        return bmiList
    }
}
package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{ //still dunno it, probably some kinda construct?
        private const val DATABASE_NAME = "bmi.db"
        private const val DATABASE_VERSION = 1
        private const val TBL_BMI = "tbl_bmi"
        private const val ID = "id"
        private const val DATE = "date"
        private const val NAME = "name"
        private const val AGE = "age"

    }

    override fun onCreate(db: SQLiteDatabase?) { //change p0 to db here, idk what the fuck is this API, hey there is question mark
        val createTblBMI = ("CREATE TABLE" + TBL_BMI + "(" + ID + "INTEGER PRIMARY KEY" + DATE + "TEXT," + NAME + "TEXT," + AGE + " TEXT" + ")")
        //CREATE TABLE tbl_bmi (id, date, name, age), probably it's SQL syntax

        db?.execSQL(createTblBMI) //can't figure out what the fuck is this, and default claim is p0
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_BMI") //SQL API again, fuck my life
        onCreate(db)
    }

    fun insertBMIData(bmi: BMIModel): Long{ //why long type here? idk every APIs
        val db = this.writableDatabase //like how compiler know writable-thing here
        val contentValues = ContentValues()

        //finally I can understand a thing here
        contentValues.put(ID, bmi.id)
        contentValues.put(DATE, bmi.date)
        contentValues.put(NAME, bmi.name)
        contentValues.put(AGE, bmi.age)

        val success = db.insert(TBL_BMI, null, contentValues) //wondering what really will print
        db.close()
        return success
    }

}
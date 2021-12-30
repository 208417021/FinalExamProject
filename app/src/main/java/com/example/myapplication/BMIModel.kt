package com.example.myapplication

import java.util.*

data class BMIModel(
    var id: Int = getAutoId(),
    var date: String = "",
    var name: String = "",
    var age: String = "",
    var bmi: String = ""
) {
    companion object{
        fun getAutoId(): Int{
            return Random().nextInt(100) //random int for ID, used for primary key
        }
    }

}
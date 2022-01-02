package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BMIAdapter:RecyclerView.Adapter<BMIAdapter.BMIViewHolder>() {
    private var bmiList: ArrayList<BMIModel> = ArrayList()

    fun addItem(item: ArrayList<BMIModel>){
        this.bmiList = item //transfer data from model?
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BMIViewHolder( //idk the api and what is the function
        LayoutInflater.from(parent.context).inflate(R.layout.bmi_items, parent, false)
    )

    override fun onBindViewHolder(holder: BMIViewHolder, position: Int) { //neither does the api
        val data = bmiList[position] // idk what is position parameter, and how to control index of bmiList

        holder.bindView(data)
    }

    override fun getItemCount(): Int {
        return bmiList.size
    }

    class BMIViewHolder(view: View): RecyclerView.ViewHolder(view){
        //private var id = view.findViewById<TextView>(R.id.DBidTV)
        private var date = view.findViewById<TextView>(R.id.DBdateTV)
        //private var name = view.findViewById<TextView>(R.id.DBnameTV)
        private var age = view.findViewById<TextView>(R.id.DBageTV)
        private var bmi = view.findViewById<TextView>(R.id.DBbmiTV)

        fun bindView(data: BMIModel){
            date.text = data.date
            //name.text = data.name
            age.text = "Age: " + data.age
            bmi.text = "BMI: " + data.bmi
        }
    }
}
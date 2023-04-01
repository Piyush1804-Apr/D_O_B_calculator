package com.practice.dob_cal

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
   private var tvselectedateid :TextView ?=null
    private var tvAgeInMin :TextView ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // firstly we need to initialise the button
        val btnDatepicker: Button = findViewById(R.id.Datepic_btn)
        tvselectedateid =findViewById(R.id.tvselectedateid)
        tvAgeInMin =findViewById(R.id.tvAgeInMin)
          btnDatepicker.setOnClickListener {
            clickDatePicker()

          }
    }
    private fun clickDatePicker(){
        val mycalender = Calendar.getInstance()
        val year = mycalender.get(Calendar.YEAR)
        val month = mycalender.get(Calendar.MONTH)
        val day = mycalender.get(Calendar.DAY_OF_MONTH)
     val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view,year,month,dayofMonth ->
                // month+1 beacause here the index started from the zero
                Toast.makeText(this,
                    "year was $year,Month was ${month+1},day of month $dayofMonth",Toast.LENGTH_LONG).show()
                val selectedDate ="$dayofMonth/ ${month+1}/$year"
                tvselectedateid?.text = selectedDate
                val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)

                theDate?.let {
                    val selectDateInMinute = theDate.time /60000
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time/60000
                        val differenceInMinutes = currentDateInMinutes-selectDateInMinute
                        tvAgeInMin?.text =differenceInMinutes.toString()
                    }

                }


            },
          year,
            month,
            day
            )
        dpd.datePicker.maxDate = System.currentTimeMillis()-86400000
         dpd.show()

    }
}
package com.example.kickitaustin

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TimePicker
import kotlinx.android.synthetic.main.activity_create_game.*
import java.text.DateFormat
import java.time.Year
import java.util.*

class CreateGame : AppCompatActivity() {

    private lateinit var HOUR: String
    private lateinit var MINUTE: String
    private lateinit var STARTTIME: String

    private lateinit var DAY: String
    private lateinit var MONTH: String
    private lateinit var YEAR: String
    private lateinit var DATEOFGAME: String

    private lateinit var LOPOPTION: String
    private lateinit var LOCATION: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_game)

        //Sets up all spinners for creating game page.
        prepareOptionsForCreate()

        button_create.setOnClickListener {
            DataSource.list.add(
                GamePost(LOCATION, "https://image-cdn.hypb.st/https%3A%2F%2Fhypebeast.com%2Fwp-content%2Fblogs.dir%2F6%2Ffiles%2F2019%2F11%2Fspongebob-squarepants-spinoff-squidward-netflix-series-info-1.jpg?q=75&w=800&cbr=1&fit=max",
                    "BOFA", STARTTIME, 1))
            GameRecyclerAdapter().submitList(DataSource.list)
            finish()
        }
        buttonCancel.setOnClickListener {
            finish()
        }
    }

    private fun prepareOptionsForCreate() {
        val levelOfPlayOption = resources.getStringArray(R.array.levelOfPlayOptions)
        if(lopSpinner != null) {
            val lopAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, levelOfPlayOption)
            lopSpinner.adapter = lopAdapter
            Log.d("XXX", "IN LOP")
            LOPOPTION = ""

            lopSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                            LOPOPTION = levelOfPlayOption[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val locationOption = resources.getStringArray(R.array.locationOptions)
        if(locationSpinner != null) {
            val locationAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, locationOption)
            locationSpinner.adapter = locationAdapter
            Log.d("XXX", "IN Loc")
            LOCATION = ""

            locationSpinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    LOCATION = locationOption[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        HOUR = ""
        MINUTE = ""
        STARTTIME = ""

        // Create a new instance of TimePickerDialog and return it
        timePicker.setOnClickListener {
            val timePickerDialog = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                //set time
                HOUR = hourOfDay.toString()
                if(minute.toString().length > 1)
                    MINUTE = minute.toString()
                else
                    MINUTE = "0"+minute.toString()
                Log.d("XXX", HOUR+":"+MINUTE)
                STARTTIME = HOUR+":"+MINUTE
            }, hour, minute, false)
            timePickerDialog.show()
        }



        DAY = ""
        MONTH = ""
        YEAR = ""
        DATEOFGAME = ""

        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of TimePickerDialog and return it
        datePicker.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                //set time
                DAY = dayOfMonth.toString()
                MONTH = month.toString()
                YEAR = year.toString()
                DATEOFGAME = ""+month+"/"+dayOfMonth+"/"+year
                Log.d("XXX", DATEOFGAME)
            }, year, month, day)
            datePickerDialog.show()
        }
    }
}

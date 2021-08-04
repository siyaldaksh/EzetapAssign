package com.assignment.ezetapassign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val nameValue = intent.getStringExtra("name")
        val numberValue = intent.getStringExtra("number")
        val cityValue = intent.getStringExtra("city")
        val name : TextView = findViewById(R.id.name)
        val number : TextView = findViewById(R.id.number)
        val city : TextView = findViewById(R.id.city)

        name.text = nameValue
        number.text = numberValue
        city.text = cityValue
    }
}
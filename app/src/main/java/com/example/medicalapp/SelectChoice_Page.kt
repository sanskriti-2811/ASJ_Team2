package com.example.medicalapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class SelectChoice_Page : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_choice_page)

        val card1 = findViewById<CardView>(R.id.BioMedicalWaste)
        card1.setOnClickListener{
            val intent = Intent(this, WardsPage::class.java)
            startActivity(intent)
        }

        val card2 = findViewById<CardView>(R.id.HandHygiene)
        card1.setOnClickListener{
            val intent = Intent(this, HandHygiene::class.java)
            startActivity(intent)
        }
    }
}
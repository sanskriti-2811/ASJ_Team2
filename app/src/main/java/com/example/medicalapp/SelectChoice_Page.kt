package com.example.medicalapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class SelectChoice_Page : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_choice_page)

        val card2 = findViewById<CardView>(R.id.HandHygiene)

        val card1 = findViewById<CardView>(R.id.BioMedicalWaste)


        card1.setOnClickListener{
            val intent = Intent(this, WardsPage::class.java)
            startActivity(intent)
        }


        card2.setOnClickListener{
            val intent = Intent(this, HandHygiene::class.java)
            startActivity(intent)
        }


        val sideAnimation = AnimationUtils.loadAnimation(this,R.anim.slide)
        card1.startAnimation(sideAnimation)
        Handler().postDelayed({

                              },100)

        val sideAnimation1 = AnimationUtils.loadAnimation(this,R.anim.slide)
        card2.startAnimation(sideAnimation)
        Handler().postDelayed({
                              },100)



    }
}
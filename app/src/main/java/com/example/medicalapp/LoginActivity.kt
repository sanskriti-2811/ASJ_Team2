package com.example.medicalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button: Button =findViewById(R.id.login)
        button.setOnClickListener{
            val intent = Intent(this, WardsPage::class.java)
            startActivity(intent)
        }
    }
}
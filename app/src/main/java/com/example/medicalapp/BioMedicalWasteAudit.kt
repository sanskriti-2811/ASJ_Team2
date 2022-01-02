package com.example.medicalapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.medicalapp.databinding.ActivityBioMedicalWasteAuditBinding
import com.google.firebase.auth.FirebaseAuth

class BioMedicalWasteAudit : AppCompatActivity() {
   private lateinit var binding: ActivityBioMedicalWasteAuditBinding
   private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBioMedicalWasteAuditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        checkUser()

       //logoutbtnclick logout user
        binding.logout.setOnClickListener{
           firebaseAuth.signOut()
           checkUser()
        }
    }

    private fun checkUser() {
        //get current user
        val firebaseUser = firebaseAuth.currentUser
        if(firebaseUser==null) {
            //logged out
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}
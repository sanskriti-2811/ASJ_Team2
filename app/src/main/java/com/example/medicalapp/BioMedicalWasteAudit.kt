package com.example.medicalapp

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.example.medicalapp.databinding.ActivityBioMedicalWasteAuditBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class BioMedicalWasteAudit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)





    }





   private lateinit var binding: ActivityBioMedicalWasteAuditBinding
   private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBioMedicalWasteAuditBinding.inflate(layoutInflater)
        setContentView(binding.root)

       /* val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("Waste")*/

        val c1 = findViewById<CheckBox>(R.id.checkBox)
        val c2 = findViewById<CheckBox>(R.id.checkBox2)
        val c3 = findViewById<CheckBox>(R.id.checkBox3)
        val c4 = findViewById<CheckBox>(R.id.checkBox4)
        val c5 = findViewById<CheckBox>(R.id.checkBox5)
        val c6 = findViewById<CheckBox>(R.id.checkBox6)
        val c7 = findViewById<CheckBox>(R.id.checkBox7)
        val c8 = findViewById<CheckBox>(R.id.checkBox8)
        val c9 = findViewById<CheckBox>(R.id.checkBox9)
        val c10 = findViewById<CheckBox>(R.id.checkBox10)
        val c11 = findViewById<CheckBox>(R.id.checkBox11)
        val c12 = findViewById<CheckBox>(R.id.checkBox12)



        val btn = findViewById<Button>(R.id.Save)

        val av1 = findViewById<CheckBox>(R.id.checkBoxav1)
        val av2 = findViewById<CheckBox>(R.id.checkBoxav2)
        val av3 = findViewById<CheckBox>(R.id.checkBoxav3)
        val av4 = findViewById<CheckBox>(R.id.checkBoxav4)

        c1.visibility = GONE
        c2.visibility = GONE

        c4.visibility = GONE
        c5.visibility = GONE

        c7.visibility = GONE
        c8.visibility = GONE

        c10.visibility = GONE
        c11.visibility = GONE



        av1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                c1.visibility = VISIBLE
                c2.visibility = VISIBLE
            } else {
                c1.visibility = GONE
                c2.visibility = GONE
            }
        }

        av2.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                c4.visibility = VISIBLE
                c5.visibility = VISIBLE
            } else {
                c4.visibility = GONE
                c5.visibility = GONE
            }
        }

        av3.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                c7.visibility = VISIBLE
                c8.visibility = VISIBLE
            } else {
                c7.visibility = GONE
                c8.visibility = GONE
            }
        }

        av4.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                c10.visibility = VISIBLE
                c11.visibility = VISIBLE
            } else {
                c10.visibility = GONE
                c11.visibility = GONE
            }
        }






        //on clicking button check the states of Checkboxes
        btn.setOnClickListener(View.OnClickListener {
        if (c1.isChecked){
            var str: String = ""
            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("General Waste")
            str += " YES "
            myRef.child("1").setValue(str)

        }
            if (c2.isChecked){
                var str: String = ""
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("General Waste")
                str += " No"
                myRef.child("2").setValue(str)


            }
            if (c3.isChecked){
                var str: String = ""
                val database = FirebaseDatabase.getInstance()
                val myRef = database.getReference("General Waste")
                str += " Not Applicable"
                myRef.child("3").setValue(str)


            }
          /*  if (c4.isChecked){
                str += "Infected Plastics: Yes"
                myRef.setValue(str)
            }
            if (c5.isChecked){

                str += "Infected Plastics: No"
                myRef.setValue(str)
            }
            if (c6.isChecked){

                str += "Infected Plastics: Not Applicable"
                myRef.child(str).setValue(str)
            }
            if (c7.isChecked){
                str += "Infected Waste: Yes"
                myRef.setValue(str)
            }
            if (c8.isChecked){
                str += "Infected Waste: No"
                myRef.setValue(str)
            }
            if (c9.isChecked){
                str += "Infected Waste: Not Applicable"
                myRef.setValue(str)
            }
            if (c10.isChecked){
                str += "Glassware: Yes"
                myRef.setValue(str)
            }
            if (c11.isChecked){
                str += "Glassware: No"
                myRef.setValue(str)
            }
            if (c12.isChecked){
                str += "Glassware: Not Applicable"
                myRef.setValue(str)
            }*/
        })



        firebaseAuth = FirebaseAuth.getInstance()

       // checkUser()

       //logoutbtnclick logout user
       /* binding.logout.setOnClickListener{
           firebaseAuth.signOut()
           checkUser()
        }*/
    }



    /*  private fun checkUser() {
          //get current user
          val firebaseUser = firebaseAuth.currentUser
          if(firebaseUser==null) {
              //logged out
              startActivity(Intent(this, MainActivity::class.java))
              finish()
          }

      }*/

}




class ActivityBioMedicalWasteAuditBinding {

}

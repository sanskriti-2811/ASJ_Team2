package com.example.medicalapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.medicalapp.databinding.ActivityMainBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

//view binding
    private lateinit var binding: ActivityMainBinding

    //if code sending failed, will used to resend
    private var forceResendingToken: PhoneAuthProvider.ForceResendingToken? = null

    private var mCallBacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null
    private var mVerificationId: String? = null
    private lateinit var firebaseAuth: FirebaseAuth

    private val TAG: String = "MAIN_TAG"

    //progress dialog
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.phoneLl.visibility = View.VISIBLE
        binding.codeLl.visibility = View.GONE

        firebaseAuth = FirebaseAuth.getInstance()

        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please Wait")
        progressDialog.setCanceledOnTouchOutside(false)

        mCallBacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

            override fun onVerificationCompleted(phoneAuthCredential:  PhoneAuthCredential) {
                 Log.d(TAG, " onVerificationCompleted: ")
                signInWithPhoneAuthCredential(phoneAuthCredential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
              progressDialog.dismiss()
                Log.d(TAG, "onVerificationFailed: ${e.message}")
                Toast.makeText(this@MainActivity, "${e.message}", Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                Log.d(TAG, "onCodeSent: $verificationId")
                mVerificationId = verificationId
                forceResendingToken = token
                progressDialog.dismiss()

                Log.d(TAG, "onCodeSent: $verificationId ")

                //hide phone layout show code
                binding.phoneLl.visibility= View.GONE
                binding.codeLl.visibility=View.VISIBLE
                Toast.makeText(this@MainActivity, "Verification Code Sent", Toast.LENGTH_SHORT).show()
                binding.codeSent.text = "Plz type OTP sent to ${binding.phoneEt.text.toString().trim()}"
            }
        }

        binding.phoneContinueBtn.setOnClickListener {
          //input phone
            val phone = binding.phoneEt.text.toString().trim()
            //validate phone
            if(TextUtils.isEmpty(phone)){
                Toast.makeText(this, "Pleas enter phone number", Toast.LENGTH_SHORT).show()
            }
            else {
                startPhoneNumberVerification(phone)
            }
        }

        binding.resend.setOnClickListener{
            //input phone
            val phone = binding.phoneEt.text.toString().trim()
            //validate phone
            if(TextUtils.isEmpty(phone)){
                Toast.makeText(this, "Pleas enter phone number", Toast.LENGTH_SHORT).show()
            }
            else {
                resendVerificationCode(phone, forceResendingToken)
            }
        }
        binding.codeBtn.setOnClickListener{
           //input verification code
            val code = binding.codeEt.text.toString().trim()
            
            if (TextUtils.isEmpty(code)){
                Toast.makeText(this, "Pleas enter otp", Toast.LENGTH_SHORT).show()

            }
            else {
                verifyPhoneNumberWithCode(mVerificationId, code)
            }
        }
    }

    private fun startPhoneNumberVerification(phone: String){
        Log.d(TAG, "startPhoneNumberVerification: $phone")
        progressDialog.setMessage("Verifying Phone Number...")
        progressDialog.show()

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(mCallBacks!!)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun resendVerificationCode(phone: String, token: PhoneAuthProvider.ForceResendingToken?){
        progressDialog.setMessage("Resending Code...")
        progressDialog.show()

        Log.d(TAG, "resendVerificationCode: $phone")

        val options = token?.let {
            PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phone)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(mCallBacks!!)
                .setForceResendingToken(it)
                .build()
        }
        options?.let { PhoneAuthProvider.verifyPhoneNumber(it) }
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        Log.d(TAG, "verifyPhoneNumberWithCode: $verificationId $code")
        progressDialog.setMessage("Verifying Code....")
        progressDialog.show()

        val credential = PhoneAuthProvider.getCredential(verificationId.toString(),code)
        signInWithPhoneAuthCredential(credential)

    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        Log.d(TAG, "signInWithPhoneAuthCredential: ")
         progressDialog.setMessage("Logging In")
         firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                //login success
                progressDialog.dismiss()
                val phone = firebaseAuth.currentUser?.phoneNumber
                Toast.makeText(this, "Logged in as $phone", Toast.LENGTH_SHORT).show()

                //start new activity
                startActivity(Intent(this, SelectChoice_Page::class.java))
                finish()
            }
             .addOnFailureListener{ e->
                 //login failed
                 progressDialog.dismiss()
                 Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
             }

    }
}
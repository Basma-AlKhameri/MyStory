package com.example.mystory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mystory.databinding.ActivityForgotPasswordBinding
import com.example.mystory.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPassword : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()
        resetPass()
    }

    private fun resetPass() {
binding.resetButton.setOnClickListener {
    var email= binding.emailF.text.toString()
    if(email.isEmpty()){
        binding.emailF.error="enter your email"
    }else{
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "success", Toast.LENGTH_LONG).show()
finish()
                }else{
                    Toast.makeText(this, "error", Toast.LENGTH_LONG).show()

                }
            }
    }
}
    }
}
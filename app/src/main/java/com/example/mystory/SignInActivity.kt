package com.example.mystory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mystory.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        login()

        binding.forgetPass.setOnClickListener {
            val intentForgot = Intent(this, ForgotPassword::class.java)
            startActivity(intentForgot)
        }

        binding.goSignUp.setOnClickListener {
            val intentSignUp = Intent(this, SignUpActivity::class.java)
            startActivity(intentSignUp)
        }
    }


    private fun login() {
        binding.loginButton.setOnClickListener {

            val email = binding.emailSignIn.text.toString()
            val password = binding.passSignIn.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "success", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        intent.putExtra("email", email)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "check your data", Toast.LENGTH_LONG).show()

                    }
                }
            }
        }
    } // end fun login

} //end of class


/*fun to checkFields
if(binding.UserName.text.isEmpty()){
   binding.UserName.error = "enter your email"
}else if (binding.pass.text.isEmpty()){
   binding.pass.error=("enter your password")
}
} */




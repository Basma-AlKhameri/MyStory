package com.example.mystory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mystory.databinding.ActivityLoginiBinding

class LoginiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        login()
    }

    private fun login() {
        var UserArray:ArrayList<User> = ArrayList()
        UserArray.add(User("basma@gmail.com", "123"))
        UserArray.add(User("b@gmail.com", "1234"))
        UserArray.add(User("s@gmail.com", "12345"))

        binding.loginButton.setOnClickListener {

          val username=  binding.UserName.text.toString()
            val pass = binding.pass.text.toString()
            val user= User(username, pass)

            for(uA in UserArray){
                if(uA.Email == user.Email && uA.pass== user.pass){
                    Toast.makeText(this,"welcome", Toast.LENGTH_SHORT).show()
                    break
                }else{
                    Toast.makeText(this,"check your data", Toast.LENGTH_SHORT).show()
                }
        }

        }

    }
}
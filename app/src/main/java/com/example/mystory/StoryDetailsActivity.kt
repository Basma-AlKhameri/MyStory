package com.example.mystory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.example.mystory.databinding.ActivityMainBinding
import com.example.mystory.databinding.ActivityStoryDetailsBinding

class StoryDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStoryDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoryDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title= intent.getStringExtra("title")
        val description= intent.getStringExtra("desc")
        supportActionBar?.title= title

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.tvDesc.text=description
        binding.tvDesc.movementMethod=ScrollingMovementMethod()
    }
}
package com.example.mystory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mystory.databinding.ActivityAddStoryBinding
import com.example.mystory.databinding.ActivityMainBinding

class AddStoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddStoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddStoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        fieldsValidation()

    }

    private fun fieldsValidation() {
        binding.addButton.setOnClickListener {
            val title = binding.edTitle.text.toString()
            val subTitle = binding.edSubTitle.text.toString()
            val desc = binding.edDesc.text.toString()
            when {
                title.isEmpty() -> {
                    binding.edTitle.error = getString(R.string.enter_title)
                }
                subTitle.isEmpty() -> {
                    binding.edSubTitle.error = getString(R.string.enter_subtitle)
                }
                desc.isEmpty() -> {
                    binding.edDesc.error = getString(R.string.add_description)
                }
                else -> {
                    this.finish()
                    val i = Intent(this, MainActivity::class.java)
                    i.putExtra("title", title)
                    i.putExtra("subtitle", subTitle)
                    i.putExtra("desc", desc)
                    startActivity(i)
                }
            }
        }

    }
}
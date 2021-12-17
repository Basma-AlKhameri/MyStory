package com.example.mystory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.mystory.databinding.ActivityLoginiBinding
import com.example.mystory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){
private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val email = intent.getStringExtra("email")

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpDrawer()
        updateEmailInHeader(email)
        drawerClicks()
        openAddStoryActivity()
        displayStory()
    }

    private fun displayStory() {
        val storyArray= ArrayList<Story>()
        storyArray.add(Story("this is first Story"," this is subTitle",
        "welcome to my story"))

        storyArray.add(Story("this is second Story"," this is second subTitle",
            "welcome to my story"))

        storyArray.add(Story("this is third Story"," this is third subTitle",
            "welcome to my story"))

        val customAdapter= CustomAdapter(storyArray,this)
        binding.recyclerview.adapter=customAdapter
    }

    private fun openAddStoryActivity() {
        binding.floatinBbutton.setOnClickListener {
            val i =Intent(this,AddStoryActivity::class.java )
            startActivity(i)
        }
    }

    private fun drawerClicks() {
        binding.navigation.setNavigationItemSelectedListener {
            when (it.itemId){
                R.id.home-> {

                    binding.drawer.closeDrawer(GravityCompat.START)
                    true
                    }
                R.id.logout -> {
                    finish()
                    val i= Intent(this, LoginiActivity::class.java)
                    startActivity(i)
                    true
                }
                else -> true
            }
        }
    }

    private fun updateEmailInHeader(email: String?) {
val headerView= binding.navigation.getHeaderView(0)
        val tvEmail= headerView.findViewById<TextView>(R.id.tvEmail)
        tvEmail.text=email
    }

    private fun setUpDrawer() {
        val toggle= ActionBarDrawerToggle(this, binding.drawer,R.string.open, R.string.close)
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home->{
                binding.drawer.openDrawer(GravityCompat.START)
                true
            }else-> true
        }
    }

}
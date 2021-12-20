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
import java.lang.RuntimeException

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
        try{
            updateEmailInHeader(email)
        }catch (e:RuntimeException){
            "error"
        }
        drawerClicks()
        openAddStoryActivity()
        displayStory()
    }

    private fun displayStory() {
        val storyArray= ArrayList<Story>()
        storyArray.add(Story("Android studio"," first week",
        "Android Studio \n is the official integrated development environment (IDE) for Android application development." +
                "\n It is based on the IntelliJ IDEA, a Java integrated development environment for software, and incorporates its code editing and developer tools.\n" +
                "\n To support application development within the Android operating system, Android Studio uses a Gradle-based build system, emulator, code templates, and Github integration." +
                " \n Every project in Android Studio has one or more modalities with source code and resource files. These modalities include Android app modules, Library modules, and Google App Engine modules.\n" +
                "\n Android Studio uses an Instant Push feature to push code and resource changes to a running application. A code editor assists the developer with writing code and offering code completion, refraction, and analysis." +
                " Applications built in Android Studio are then compiled into the APK format for submission to the Google Play Store.\n")
        )


        storyArray.add(Story("OOP and classes"," second week",
            "welcome to my story"))

        storyArray.add(Story("ui Design"," third week",
            "welcome to my story"))
        storyArray.add(Story("Activities and fragments"," fourth week",
            "welcome to my story"))

        val customAdapter= CustomAdapter(storyArray,this)
        binding.recyclerview.adapter=customAdapter
        if(intent.getStringExtra("title")!=null ){

            val title= intent.getStringExtra("title")
            val subtitle= intent.getStringExtra("subtitle")
            val desc= intent.getStringExtra("desc")
            val newStory= Story(title!!,subtitle!!, desc!!)
            storyArray.add(newStory)
            customAdapter.notifyDataSetChanged()


        }
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
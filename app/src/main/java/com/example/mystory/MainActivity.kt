package com.example.mystory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.mystory.DataClass.Story
import com.example.mystory.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import java.lang.RuntimeException

class MainActivity : AppCompatActivity(){
private lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        val email = intent.getStringExtra("email")

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setUpDrawer()

        try {
            updateEmailInHeader(email!!)
        } catch (e:RuntimeException){
            "error"
        }
        drawerClicks()
        openAddStoryActivity()
        displayStory()
    } //end fun onCreate

    override fun onStart() {
        super.onStart()
        if (auth.currentUser==null){
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }
    } //end fun onStart

    private fun displayStory() {
        val storyArray= ArrayList<Story>()
        storyArray.add(
            Story("Astronaut Annie"," first story",
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit,\n" +
                "            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n" +
                "            Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi\n" +
                "            ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in\n" +
                "            voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint\n" +
                "            occaecat cupidatat non proident,sunt in culpa qui officia deserunt mollit anim id est laborum \n " +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit,\n" +
                "            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.\n" +
                "            Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi\n" +
                "            ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in\n" +
                "            voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint\n" +
                "            occaecat cupidatat non proident,sunt in culpa qui officia deserunt mollit anim id est laborum")
        )
        storyArray.add(
            Story("the Night Knights"," second story",
            "welcome to my story")
        )

        storyArray.add(
            Story("Pillowland"," third story",
            "welcome to my story")
        )
        storyArray.add(
            Story("sleep My Bunny"," fourth story",
            "welcome to my story")
        )
        storyArray.add(
            Story("Tomorrow I will be Brave"," fifth story",
            "welcome to my story")
        )
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
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this, SignInActivity::class.java))

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
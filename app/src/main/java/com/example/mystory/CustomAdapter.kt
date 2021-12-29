package com.example.mystory

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.mystory.DataClass.Story
import java.util.*
import kotlin.collections.ArrayList

class CustomAdapter(private val StoriesList: ArrayList<Story>, val context: Context ):RecyclerView.Adapter<CustomAdapter.DataHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHolder {
        val dataHolder= DataHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.custom_layout,parent,false))
        return dataHolder
    }

    override fun onBindViewHolder(holder: DataHolder, position: Int) {
        val story= StoriesList[position]
        holder.storyTitle.text= story.title
        holder.storySubTitle.text= story.subTitle
        holder.storyLetter.text= story.title.first().toString()

        generateColors(holder, position)
        holder.itemView.setOnClickListener{
            val i= Intent(context, StoryDetailsActivity::class.java)
            i.putExtra("title",story.title)
            i.putExtra("desc",story.Description)
            context.startActivity(i)
        }
    }

    private fun generateColors(holder: DataHolder, position: Int) {
        val r = Random()
        val red= r.nextInt(255 +position)
        val green= r.nextInt(255 +position+1)
        val blue= r.nextInt(255 +position+1)
holder.cardViewLetter.setCardBackgroundColor(Color.rgb(red, green, blue))

    }

    override fun getItemCount(): Int {
        return StoriesList.size
    }
    class DataHolder(item: View): RecyclerView.ViewHolder(item){
        val storyTitle:TextView= item.findViewById(R.id.title)
        val storySubTitle:TextView= item.findViewById(R.id.subTitle)
        val storyLetter:TextView= item.findViewById(R.id.story_letter)
        var cardViewLetter:CardView= item.findViewById(R.id.circle)


    }
}
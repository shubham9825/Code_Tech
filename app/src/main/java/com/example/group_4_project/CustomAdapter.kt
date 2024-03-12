package com.example.group_4_project

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomAdapter(private val dataList: List<Candidate>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.candidate_name)
        val title: TextView = view.findViewById(R.id.candidate_title)
        val photo: ImageView = view.findViewById(R.id.candidate_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val candidate = dataList[position]
        holder.name.text = candidate.name
        holder.title.text = candidate.title

        // Using Glide to load the image
        Glide.with(holder.photo.context).load(candidate.photoUrl).into(holder.photo)
    }

    override fun getItemCount() = dataList.size
}

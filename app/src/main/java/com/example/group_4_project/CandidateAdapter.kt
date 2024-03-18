package com.example.group_4_project

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class CandidateAdapter(options: FirebaseRecyclerOptions<Candidate>) :
    FirebaseRecyclerAdapter<Candidate, CandidateAdapter.MyViewHolder>(options) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Candidate) {
        holder.txtName.text = model.name
        holder.txtRole.text = model.role

         val storRef: StorageReference =
            FirebaseStorage.getInstance().getReferenceFromUrl(model.image_url)
        Glide.with(holder.imgCandidate.context).load(storRef).into(holder.imgCandidate)

//        send data with intent
        holder.itemView.setOnClickListener { v: View ->
            val myIntent = Intent(v.context, DetailActivity::class.java)
            myIntent.putExtra("name", model.name)
            myIntent.putExtra("role", model.role)
            myIntent.putExtra("address", model.address)
            myIntent.putExtra("education", model.education)
            myIntent.putExtra("image", model.image)
            v.context.startActivity(myIntent)
        }
    }

    class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
        inflater.inflate(R.layout.item_view, parent, false)
    ) {
        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtRole: TextView = itemView.findViewById(R.id.txtRole)
        val imgCandidate: ImageView = itemView.findViewById(R.id.imgCandidate)
    }
}
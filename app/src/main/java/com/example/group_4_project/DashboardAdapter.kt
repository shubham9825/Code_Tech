package com.example.group_4_project

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class DashboardAdapter(options: FirebaseRecyclerOptions<Dashboard>) :
    FirebaseRecyclerAdapter<Dashboard, DashboardAdapter.MyViewHolder>(options) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, model: Dashboard) {
        holder.txtCompanyName.text = model.companyName
        holder.txtJobTitle.text = model.jobTitle
        holder.txtDescription.text = model.description
        val storRef: StorageReference =
            FirebaseStorage.getInstance().getReferenceFromUrl(model.companyImageUrl)
        Glide.with(holder.imgCompany.context).load(storRef).into(holder.imgCompany)
    }

    class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
        inflater.inflate(R.layout.dashboard_recycler, parent, false)
    ) {
        val txtCompanyName: TextView = itemView.findViewById(R.id.txtCompanyName)
        val txtJobTitle: TextView = itemView.findViewById(R.id.txtJobTitle)
        val txtDescription: TextView = itemView.findViewById(R.id.txtDescription)
        val imgCompany: ImageView = itemView.findViewById(R.id.imgCompany)
    }
}

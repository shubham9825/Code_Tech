package com.example.group_4_project

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CandidateAdapter(public val list: List<Candidate>) :
    RecyclerView.Adapter<CandidateAdapter.CandidateHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CandidateHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CandidateHolder(inflater, parent)
    }

    override fun getItemCount(): Int = list.size
    override fun onBindViewHolder(holder: CandidateHolder, position: Int) {
        val candidate: Candidate = list[position]
        holder.txtName.text = candidate.name
        holder.txtRole.text = candidate.role
        holder.itemView.setOnClickListener {
            val i = Intent(it.context, DetailActivity::class.java)
            i.putExtra("name", candidate.name)
            i.putExtra("role", candidate.role)
            it.context.startActivity(i)
        }
    }

    class CandidateHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_view, parent, false)) {

        val txtName: TextView = itemView.findViewById(R.id.txtName)
        val txtRole: TextView = itemView.findViewById(R.id.txtRole)
    }
}
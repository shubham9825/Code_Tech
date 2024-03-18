package com.example.group_4_project


import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name = intent.getStringExtra("name")
        val role = intent.getStringExtra("role")
        val education = intent.getStringExtra("education")
        val image = intent.getStringExtra("image")
        val address = intent.getStringExtra("address")

        val txtName: TextView = findViewById(R.id.txtName)
        val txtRole: TextView = findViewById(R.id.txtRole)
        val txtEducation: TextView = findViewById(R.id.txtEducation)
        val txtAddress: TextView = findViewById(R.id.txtAddress)
        val imgCandidate: ImageView = findViewById(R.id.ImgCandidate)

        txtName.text = name
        txtRole.text = role
        txtAddress.text = address
        txtEducation.text = education
        Glide.with(this).load(image).into(imgCandidate)

        var connect = findViewById<Button>(R.id.btnConnect)

        connect.setOnClickListener { v: View ->
            Toast.makeText(this, "Connection added", Toast.LENGTH_SHORT).show()
        }
    }
}

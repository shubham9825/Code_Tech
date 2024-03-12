package com.example.group_4_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    //  binding data to the RecyclerView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomAdapter
    private lateinit var logoutButton: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Setup Toolbar
        val toolbar: MaterialToolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        // RecyclerView setup
        recyclerView = findViewById(R.id.home_recyclerview)
        adapter = CustomAdapter(listOf())
        recyclerView.adapter = adapter
        auth = FirebaseAuth.getInstance()
        fetchFeed()


        findViewById<FloatingActionButton>(R.id.fab_navigate).setOnClickListener {

            startActivity(Intent(this, CandidateActivity::class.java))
        }

        logoutButton = findViewById(R.id.btnLogout);
        logoutButton.setOnClickListener {
            signOut()
        }

    }

    private fun signOut() {
        auth.signOut()
        Toast.makeText(this@MainActivity, "Logged out successfully", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
     }

    private fun fetchFeed() {

    }
}

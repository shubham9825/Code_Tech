package com.example.group_4_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class DashboardActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var logoutButton: Button
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private var rView: RecyclerView? = null
    private var adapter: DashboardAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)

        auth = FirebaseAuth.getInstance()
        databaseReference = FirebaseDatabase.getInstance().reference.child("jobs")

        logoutButton = findViewById(R.id.btnLogout)
        logoutButton.setOnClickListener {
            signOut()
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_dashboard -> {

                    true
                }
                R.id.navigation_candidate -> {
                    startActivity(Intent(this, CandidateActivity::class.java))
                    true
                }
                else -> false
            }
        }

        // RecyclerView setup
        rView = findViewById(R.id.dashboardRecyclerView)
        rView?.layoutManager = LinearLayoutManager(this)
        val options = FirebaseRecyclerOptions.Builder<Dashboard>()
            .setQuery(databaseReference, Dashboard::class.java)
            .build()
        adapter = DashboardAdapter(options)
        rView?.adapter = adapter
    }



    private fun signOut() {
        auth.signOut()
        Toast.makeText(this@DashboardActivity, "Logged out successfully", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
}

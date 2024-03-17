package com.example.group_4_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class CandidateActivity : AppCompatActivity() {
    private val Candidate = listOf(
        Candidate("Jon", "jon@jon.com", "Professor at Conestoga"),
        Candidate("Steve", "sjobs@apple.com", "Cofounder of Apple"),
        Candidate("Sandeep", "sandeep@sandy.com", "Programmer"),
        Candidate("Jon", "jon@jon.com", "Professor at Conestoga"),
        Candidate("Steve", "sjobs@apple.com", "Cofounder of Apple"),
        Candidate("Sandeep", "sandeep@sandy.com", "Programmer"),
        Candidate("Jon", "jon@jon.com", "Professor at Conestoga"),
        Candidate("Steve", "sjobs@apple.com", "Cofounder of Apple"),
        Candidate("Sandeep", "sandeep@sandy.com", "Programmer")
    )
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var logoutButton: Button
    private lateinit var auth: FirebaseAuth
    private var rView: RecyclerView? = null;
    private var adapter: CandidateAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate)

        auth = FirebaseAuth.getInstance()
        var database = FirebaseDatabase.getInstance().reference.child("candidates")

//        val myRef = FirebaseDatabase.getInstance().reference.child("candidates")
//        val options = FirebaseRecyclerOptions.Builder<Candidate>().setQuery(query, Candidate::class.java).build()

//        val query = FirebaseDatabase.getInstance().reference.child("phones")
//        val options = FirebaseRecyclerOptions.Builder<Candidate>().setQuery(query, Candidate::class.java).build()
//        adapter = CandidateAdapter(options)

        rView = findViewById(R.id.rView)
        rView?.layoutManager = LinearLayoutManager(this, )
        rView?.adapter = adapter

        auth = FirebaseAuth.getInstance()
        logoutButton = findViewById(R.id.btnLogout);
        logoutButton.setOnClickListener {
            signOut()
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_dashboard -> {
                    startActivity(Intent(this, DashboardActivity::class.java))
                    true
                }

                R.id.navigation_candidate -> {
                    startActivity(Intent(this, CandidateActivity::class.java))
                    true
                }
                else -> false
            }
        }

        val recyclerView: RecyclerView = findViewById(R.id.rView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = CandidateAdapter(Candidate)
        recyclerView.adapter = adapter


    }

    class FirebaseRecyclerOptions {

    }

    private fun signOut() {
        auth.signOut()
        Toast.makeText(this@CandidateActivity, "Logged out successfully", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
}

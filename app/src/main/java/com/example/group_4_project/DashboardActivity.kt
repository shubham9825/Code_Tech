package com.example.group_4_project
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var logoutButton: Button
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)
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
    }
    private fun signOut() {
        auth.signOut()
        Toast.makeText(this@DashboardActivity, "Logged out successfully", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
    }
}

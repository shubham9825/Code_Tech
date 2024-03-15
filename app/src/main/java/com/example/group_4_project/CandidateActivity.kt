package com.example.group_4_project

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class CandidateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_candidate)

        val candidateId = intent.getStringExtra("CANDIDATE_ID") ?: return
        fetchCandidateData(candidateId)
    }

    private fun fetchCandidateData(candidateId: String) {
        FirebaseFirestore.getInstance().collection("candidates").document(candidateId)
            .get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    val candidate = document.toObject(Candidate::class.java)
                    updateUI(candidate)
                }
            }
            .addOnFailureListener { e ->
                // Handle error
            }
    }

    private fun updateUI(candidate: Candidate?) {
    }
}

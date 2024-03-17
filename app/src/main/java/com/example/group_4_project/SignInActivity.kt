package com.example.group_4_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignInActivity: AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passEditText: EditText
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

         firebaseAuth = FirebaseAuth.getInstance()
         emailEditText = findViewById(R.id.txtEmail) // Find email EditText by ID
         passEditText = findViewById(R.id.txtPassword) // Find password EditText by ID

        val signUpTextView = findViewById<TextView>(R.id.txtSignUp)
        signUpTextView.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }

//        Validation
        val signInButton = findViewById<Button>(R.id.btnSignUp)
        signInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val pass = passEditText.text.toString()

            val nonEmptyPattern = ".+".toRegex()
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
            val passwordPattern =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$".toRegex()

            if (!email.matches(nonEmptyPattern) || !pass.matches(nonEmptyPattern)) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
            }else if(!email.matches(emailPattern)){
                 Toast.makeText(this, "Enter valid email!", Toast.LENGTH_SHORT).show()
            }
            else if (!pass.matches(passwordPattern)) {
                Toast.makeText(
                    this,
                    "Password must contain at least one digit, one lowercase and uppercase letter, one special character, and have minimum 8 characters!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                // Sign in with email and password
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign-in successful
                        val intent = Intent(this, IntroActivity::class.java)
                        startActivity(intent)
                    } else {
                        // Sign-in failed, display error message
                        Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        // Check if user is already signed in, if yes, navigate to IntroActivity
        if(firebaseAuth.currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
package com.example.group_4_project


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passEditText: EditText
    private lateinit var confirmEditText: EditText
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var signUpTextView: TextView
    private lateinit var signUpButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        firebaseAuth = FirebaseAuth.getInstance()
        emailEditText = findViewById(R.id.txtEmail)  // Find email EditText by ID
        passEditText = findViewById(R.id.txtPassword)
        confirmEditText = findViewById(R.id.txtCpassword) // Find password EditText by ID
        signUpTextView = findViewById(R.id.txtSignIn)

        signUpTextView.setOnClickListener {
            val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
            startActivity(intent)
        }

//     Validation
        signUpButton = findViewById(R.id.btnSignUp)
        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val pass = passEditText.text.toString()
            val cPass = confirmEditText.text.toString()

            val nonEmptyPattern = ".+".toRegex()
            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
            val passwordPattern =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$".toRegex()

            if (!email.matches(nonEmptyPattern) || !pass.matches(nonEmptyPattern) || !cPass.matches(
                    nonEmptyPattern
                )
            ) {
                Toast.makeText(this, "All fields are required!", Toast.LENGTH_SHORT).show()
            } else if (!email.matches(emailPattern)) {
                Toast.makeText(this, "Enter valid email!", Toast.LENGTH_SHORT).show()
            } else if (!pass.matches(passwordPattern)) {
                Toast.makeText(
                    this,
                    "Password must contain at least one digit, one lowercase and uppercase letter, one special character, and have minimum 8 characters!",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (pass != cPass) {
                Toast.makeText(this, "Password is not matching", Toast.LENGTH_SHORT).show()
            } else {
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, SignInActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }


    override fun onStart() {
        super.onStart()
        //   Check if user is already signed in, if yes, navigate to IntroActivity
        if (firebaseAuth.currentUser != null) {
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
        }
    }
}

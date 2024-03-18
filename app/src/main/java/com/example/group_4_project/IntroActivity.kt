package com.example.group_4_project

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class IntroActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        val imgLogo: ImageView = findViewById(R.id.imgLogo)
        imgLogo.setImageResource(R.drawable.logo)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }

    private fun animateText(imageView: ImageView) {
        val fadeIn = AlphaAnimation(0.0f, 1.0f).apply {
            duration = 1500
            fillAfter = true
        }

        val slideUp = TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 0f,
            Animation.RELATIVE_TO_SELF, 1f,
            Animation.RELATIVE_TO_SELF, 0f
        ).apply {
            duration = 1500
            fillAfter = true
        }

        val animationSet = AnimationSet(true).apply {
            addAnimation(fadeIn)
            addAnimation(slideUp)
            fillAfter = true
        }

        imageView.startAnimation(animationSet)
    }
}

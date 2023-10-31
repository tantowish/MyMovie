package com.example.mymovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.mymovie.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var  binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        with(binding) {
            tvTap.visibility = View.INVISIBLE
            parent.setOnClickListener {
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            Handler().postDelayed({
                tvTap.visibility = View.VISIBLE
            }, 3000)
        }
    }
}
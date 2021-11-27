package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.databinding.ActivitySplashBinding

class MainActivity : AppCompatActivity() {

    //view binding
    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //handle click login
        binding.loginBtn.setOnClickListener{
            // :TODO
            startActivity(Intent(this, LoginActivity::class.java))
        }

        //handle click, skip and continue to main screen
        binding.skipBtn.setOnClickListener {
            // :TODO
            startActivity(Intent(this, DashboardUserActivity::class.java))
        }
    }
}
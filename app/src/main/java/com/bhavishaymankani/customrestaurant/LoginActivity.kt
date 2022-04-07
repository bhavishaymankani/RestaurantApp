package com.bhavishaymankani.customrestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bhavishaymankani.customrestaurant.databinding.ActivityLoginBinding
import com.bhavishaymankani.customrestaurant.ui.login.LoginFragment

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Pizza Palace"


        actionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction()?.replace(R.id.frameLayout, LoginFragment())?.commit()
    }
}
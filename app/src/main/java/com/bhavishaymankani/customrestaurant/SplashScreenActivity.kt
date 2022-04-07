package com.bhavishaymankani.customrestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bhavishaymankani.customrestaurant.base.BaseActivity

class SplashScreenActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spalsh_screen)




        Handler().postDelayed({
            Intent(this, MainActivity::class.java).apply {
                startActivity(this)
            }
            finish()
        }, 5000)
    }
}
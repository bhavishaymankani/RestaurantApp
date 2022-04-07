package com.bhavishaymankani.customrestaurant.base

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.recyclerview.widget.RecyclerView

open class BaseActivity : AppCompatActivity() {

    fun createRecyclerView(context: Context, recyclerView: RecyclerView, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>, isHorizontal : Boolean = false){
        BaseFragment().createRecyclerview(context, recyclerView, adapter, isHorizontal)
    }

    fun setDarkMode(bool: Boolean) {
        if (bool) AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES) else AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
    }

    fun createToastMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}
package com.bhavishaymankani.customrestaurant.pageradapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bhavishaymankani.customrestaurant.ui.login.login.SigninFragment
import com.bhavishaymankani.customrestaurant.ui.login.register.RegisterFragment
import com.bhavishaymankani.customrestaurant.utils.Constants.FRAG_TITLE_LOGIN
import com.bhavishaymankani.customrestaurant.utils.Constants.FRAG_TITLE_REGISTER

class LoginPagerAdapter(fragment: Fragment, titleList: List<String>) : FragmentStateAdapter(fragment) {

    private lateinit var titleList: List<String>

    init {
        this.titleList = titleList
    }


    override fun getItemCount(): Int {
        return titleList.size
    }

    

    override fun createFragment(position: Int): Fragment {
        var fragment = Fragment()
       when(position) {
           0 -> {
               fragment = SigninFragment().apply {
                   Bundle().putString(FRAG_TITLE_LOGIN, titleList[0])
               }
               return fragment
           }
           1 -> {
               fragment = RegisterFragment().apply {
                   Bundle().putString(FRAG_TITLE_REGISTER, titleList[1])
               }
               return fragment
           }
           else -> {
               return fragment
           }
       }
    }

}
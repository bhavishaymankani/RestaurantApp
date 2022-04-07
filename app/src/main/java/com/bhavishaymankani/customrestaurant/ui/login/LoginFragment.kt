package com.bhavishaymankani.customrestaurant.ui.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.bhavishaymankani.customrestaurant.R
import com.bhavishaymankani.customrestaurant.databinding.FragmentLoginBinding
import com.bhavishaymankani.customrestaurant.pageradapter.LoginPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewPager: ViewPager2

    companion object {
        fun newInstance() = LoginFragment()
    }

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        val list = mutableListOf("Login", "Register")
        viewPager = binding.viewPager2
        viewPager.adapter = LoginPagerAdapter(this, list)
        val tabLayout = binding.tabLayout

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position) {
                0 -> {
                    tab.text = list[0]
                }
                1 -> {
                    tab.text = list[1]
                }
            }
        }.attach()

        // TODO: Use the ViewModel
    }

}
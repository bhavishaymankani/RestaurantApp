package com.bhavishaymankani.customrestaurant.ui.more

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bhavishaymankani.customrestaurant.AddressesActivity
import com.bhavishaymankani.customrestaurant.LoginActivity
import com.bhavishaymankani.customrestaurant.R
import com.bhavishaymankani.customrestaurant.SettingsActivity
import com.bhavishaymankani.customrestaurant.base.BaseFragment
import com.bhavishaymankani.customrestaurant.databinding.MoreFragmentBinding
import com.bhavishaymankani.customrestaurant.ui.address.AddressesFragment
import com.bhavishaymankani.customrestaurant.ui.login.LoginFragment
import com.bhavishaymankani.customrestaurant.utils.Constants.USER_DATA_KEY
import com.bhavishaymankani.customrestaurant.utils.Constants.USER_ID_KEY
import kotlin.concurrent.fixedRateTimer

class MoreFragment : BaseFragment(), View.OnClickListener {

    private var _binding: MoreFragmentBinding? = null

    companion object {
        fun newInstance() = MoreFragment()
    }

    private lateinit var viewModel: MoreViewModel
    private val  binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = MoreFragmentBinding.inflate(inflater, container, false)
        val root = binding.root

        val textview = binding.moreTv

        val sharedPreferences = activity?.getSharedPreferences(USER_DATA_KEY, Context.MODE_PRIVATE)
        val msg = sharedPreferences?.all?.get(USER_ID_KEY)
        val userHasSignedIn = sharedPreferences?.contains(USER_ID_KEY)
        createToastMessage(userHasSignedIn.toString())
        if (userHasSignedIn == true) {
            textview.text = "Logout"
            textview.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(requireContext(), R.drawable.ic_logout), null, null, null)
        }

        textview.setOnClickListener(this)
        binding.settingsTv.setOnClickListener(this)
        binding.savedAddressesTv.setOnClickListener(this)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MoreViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onClick(view: View?) {
        view?.let {
            when (it) {
                binding.moreTv -> {
                    val sharedPreferences = activity?.getSharedPreferences(USER_DATA_KEY, Context.MODE_PRIVATE)
                    if (sharedPreferences?.contains(USER_ID_KEY) == false) {
                        Intent(context, LoginActivity::class.java).apply {
                            startActivity(this)
                        }
                    } else {
                        sharedPreferences?.edit()?.clear()?.apply()
                        binding.moreTv.text = "Login"
                        binding.moreTv.setCompoundDrawablesRelativeWithIntrinsicBounds(ContextCompat.getDrawable(requireContext(), R.drawable.ic_login), null, null, null)

                    }
                    //fragmentManager?.beginTransaction()?.replace(R.id.more_layout, LoginFragment())?.commit()
                }
                binding.savedAddressesTv -> {
                    Intent(context, AddressesActivity::class.java).apply {
                        startActivity(this)
                    }
                }
                binding.settingsTv -> {
                    Intent(context, SettingsActivity::class.java).apply {
                        startActivity(this)
                    }
                }
                else -> {}
            }


        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}
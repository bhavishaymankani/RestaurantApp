package com.bhavishaymankani.customrestaurant.ui.login.login

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bhavishaymankani.customrestaurant.MainActivity
import com.bhavishaymankani.customrestaurant.R
import com.bhavishaymankani.customrestaurant.base.BaseFragment
import com.bhavishaymankani.customrestaurant.databinding.FragmentSigninBinding
import com.bhavishaymankani.customrestaurant.utils.Constants
import com.bhavishaymankani.customrestaurant.utils.Constants.USER_DATA

class SigninFragment : BaseFragment(), View.OnClickListener {

    private var _binding: FragmentSigninBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SigninViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSigninBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(SigninViewModel::class.java)
        binding.loginBtn.setOnClickListener(this)



        viewModel.validateUser.observe(viewLifecycleOwner) {
            if (it.status == null) {
                Intent(context, MainActivity::class.java).apply {
                    putExtra(USER_DATA, it)
                    startActivity(this)
                    activity?.finish()
                }

            } else {
                createToastMessage("Wrong email/password")
            }
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(view: View?) {
        view?.let {
            when(view) {
                binding.loginBtn -> {
                    val email = binding.emailEt
                    val password = binding.passwordEt

                    loginUser(viewModel, email, password)
                }
                else -> {}
            }
        }
    }

}
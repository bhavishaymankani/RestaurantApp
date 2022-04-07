package com.bhavishaymankani.customrestaurant.ui.login.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bhavishaymankani.customrestaurant.R
import com.bhavishaymankani.customrestaurant.base.BaseFragment
import com.bhavishaymankani.customrestaurant.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment(), View.OnClickListener {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.registeBtnr.setOnClickListener(this)

        viewModel.addUser.observe(viewLifecycleOwner) {

        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onClick(view: View?) {
        view?.let {
            when(it) {
                binding.registeBtnr -> {
                    val firstName = binding.firstnameEt
                    val lastName = binding.lastnameEt
                    val email = binding.emailEt
                    val mobNum = binding.mobileNumEtt
                    val password = binding.passwordEt
                    val confirmPassword = binding.confirmPasswordEt
                    val checkBox = binding.checkBox

                    signUpUser(
                        viewModel,
                        firstName,
                        lastName,
                        email,
                        mobNum,
                        password,
                        confirmPassword,
                        checkBox
                    )

                }
                else -> {}
            }
        }
    }

}
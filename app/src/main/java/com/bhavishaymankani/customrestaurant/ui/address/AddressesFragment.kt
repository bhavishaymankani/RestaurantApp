package com.bhavishaymankani.customrestaurant.ui.address

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bhavishaymankani.customrestaurant.R
import com.bhavishaymankani.customrestaurant.databinding.FragmentAddressesBinding

class AddressesFragment : Fragment() {

    private var _binding: FragmentAddressesBinding? = null

    private val  binding get() = _binding!!

    private lateinit var viewModel: AddressesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this).get(AddressesViewModel::class.java)
        _binding = FragmentAddressesBinding.inflate(inflater, container, false)

        val root = binding.root

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}
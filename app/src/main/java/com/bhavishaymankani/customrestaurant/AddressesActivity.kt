package com.bhavishaymankani.customrestaurant

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bhavishaymankani.customrestaurant.base.BaseActivity
import com.bhavishaymankani.customrestaurant.databinding.ActivityAddressesBinding
import com.bhavishaymankani.customrestaurant.recyclerviewadapters.AddressesAdapter
import com.bhavishaymankani.customrestaurant.utils.Constants.USER_DATA_KEY
import com.bhavishaymankani.customrestaurant.utils.Constants.USER_ID_KEY

class AddressesActivity : BaseActivity(), OnAddressSelectedListener {
    private lateinit var binding: ActivityAddressesBinding
    private lateinit var viewModel: AddressesViewModel
    private lateinit var addressesAdapter: AddressesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddressesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Manage Addresses"
        actionBar?.setDisplayHomeAsUpEnabled(true)

        viewModel = ViewModelProvider(this)[AddressesViewModel::class.java]

        val addressRv = binding.addressesRv
        addressesAdapter = AddressesAdapter(this)

        viewModel.addresses.observe(this) {
            addressesAdapter.setAddressList(it.addresses)
            createRecyclerView(
                this,
                addressRv,
                addressesAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>
            )

        }


        val sharedPreferences = getSharedPreferences(USER_DATA_KEY, Context.MODE_PRIVATE)



        if (sharedPreferences.contains(USER_ID_KEY)) {
            val uid = sharedPreferences.all[USER_ID_KEY]
            viewModel.getAddresses(uid as String)
        }



    }

    override fun onAddressClicked(position: Int) {
        createToastMessage(position.toString())
    }
}
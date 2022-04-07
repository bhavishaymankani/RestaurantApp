package com.bhavishaymankani.customrestaurant

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.bhavishaymankani.customrestaurant.base.BaseActivity
import com.bhavishaymankani.customrestaurant.databinding.ActivityCartBinding
import com.bhavishaymankani.customrestaurant.databinding.AddressBottomSheetDialogBinding
import com.bhavishaymankani.customrestaurant.datasource.local.CartItemDatabase
import com.bhavishaymankani.customrestaurant.recyclerviewadapters.AddressesAdapter
import com.bhavishaymankani.customrestaurant.recyclerviewadapters.OrderListAdapter
import com.bhavishaymankani.customrestaurant.ui.home.CartItemRepository
import com.bhavishaymankani.customrestaurant.ui.home.HomeViewModelFactory
import com.bhavishaymankani.customrestaurant.utils.Constants
import com.google.android.material.bottomsheet.BottomSheetDialog

class CartActivity : BaseActivity(), View.OnClickListener, OnAddressSelectedListener {

    private lateinit var binding: ActivityCartBinding
    private lateinit var bottomSheetBinding: AddressBottomSheetDialogBinding
    private lateinit var cartViewModel: CartViewModel
    private lateinit var addressViewModel: AddressesViewModel
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var addressesAdapter: AddressesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Cart"

        actionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.addressTv.setOnClickListener(this)
        binding.paymentBtn.setOnClickListener(this)


        val cartItemRepository =
            CartItemRepository(CartItemDatabase(applicationContext))
        val factory = CartViewModelFactory(cartItemRepository)

        cartViewModel = ViewModelProvider(this, factory)[CartViewModel::class.java]
        addressViewModel =  ViewModelProvider(this)[AddressesViewModel::class.java]

        val itemsRv = binding.itemsRv

        cartViewModel.cartItems.observe(this) {
            createRecyclerView(applicationContext, itemsRv, OrderListAdapter(it, cartViewModel) as RecyclerView.Adapter<RecyclerView.ViewHolder>)
        }

        cartViewModel.totalItems.observe(this) {
            supportActionBar?.subtitle = "$it items"
        }

        cartViewModel.totalCost.observe(this) {
            binding.costTv.text = it.toString()
            binding.deliveryChargersTv.text = "20"
            binding.netTotalTv.text = "${it + 20}"
        }
        cartViewModel.getTotalCost()

        cartViewModel.getCartItems()

        cartViewModel.getTotalItems()
    }

    override fun onClick(view: View?) {
        view.let {
            when(it) {
                binding.addressTv -> {
                    bottomSheetDialog = BottomSheetDialog(this)
                    bottomSheetBinding = AddressBottomSheetDialogBinding.inflate(LayoutInflater.from(this))
                    bottomSheetDialog.setContentView(bottomSheetBinding.root)

                    val addressRecyclerView = bottomSheetBinding.bottomSheetRv
                    addressesAdapter = AddressesAdapter(this)

                    addressViewModel.addresses.observe(this) {
                        addressesAdapter.setAddressList(it.addresses)
                        createRecyclerView(
                            applicationContext,
                            addressRecyclerView,
                            addressesAdapter as RecyclerView.Adapter<RecyclerView.ViewHolder>
                        )
                    }

                    val sharedPreferences = getSharedPreferences(Constants.USER_DATA_KEY, Context.MODE_PRIVATE)

                    if (sharedPreferences.contains(Constants.USER_ID_KEY)) {
                        val uid = sharedPreferences.all[Constants.USER_ID_KEY]
                        addressViewModel.getAddresses(uid as String)
                    }




                    bottomSheetDialog.show()
                }
                binding.paymentBtn -> {

                }
            }
        }
    }

    override fun onAddressClicked(position: Int) {
        val address = addressesAdapter.getAddressByPosition(position)
        binding.addressTv.text = address.addressName
        binding.subAddressTv.visibility = View.VISIBLE
        binding.subAddressTv.text = "${address.houseNum} ${address.building} ${address.landmark} ${address.pinCode}"
        bottomSheetDialog.dismiss()
    }
}
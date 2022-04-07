package com.bhavishaymankani.customrestaurant.recyclerviewadapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhavishaymankani.customrestaurant.AddressesViewModel
import com.bhavishaymankani.customrestaurant.OnAddressSelectedListener
import com.bhavishaymankani.customrestaurant.databinding.AddressListBinding
import com.bhavishaymankani.customrestaurant.datasource.models.Address


class AddressesAdapter( val onAddressSelectedListener: OnAddressSelectedListener) : RecyclerView.Adapter<AddressesAdapter.ViewHolder>() {

    private lateinit var list: List<Address>

    inner class ViewHolder(val binding: AddressListBinding, val onAddressSelectedListener: OnAddressSelectedListener) : RecyclerView.ViewHolder(binding.root),
    View.OnClickListener{
        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            onAddressSelectedListener.onAddressClicked(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AddressListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, onAddressSelectedListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.binding.addressTitleTv.text = list!![position].addressName
       holder.binding.descriptionTv.append(list!![position].houseNum + " ")
       holder.binding.descriptionTv.append(list!![position].building + " ")
       holder.binding.descriptionTv.append(list!![position].landmark + " ")
       holder.binding.descriptionTv.append(list!![position].pinCode + " ")

        Log.d("address", list!![position].addressName)

    }


    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    fun setAddressList(list: List<Address>) {
        this.list = list
    }

    fun  getAddressByPosition(position: Int) : Address {
        return list!![position]
    }
}
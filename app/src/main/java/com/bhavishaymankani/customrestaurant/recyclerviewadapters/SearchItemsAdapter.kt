package com.bhavishaymankani.customrestaurant.recyclerviewadapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhavishaymankani.customrestaurant.CartViewModel
import com.bhavishaymankani.customrestaurant.databinding.OrderListBinding
import com.bhavishaymankani.customrestaurant.databinding.SearchItemsListBinding
import com.bhavishaymankani.customrestaurant.datasource.local.CartItem
import com.bhavishaymankani.customrestaurant.datasource.models.Item
import com.bhavishaymankani.customrestaurant.datasource.utils.Credentials
import com.bhavishaymankani.customrestaurant.ui.dashboard.SearchViewViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class SearchItemsAdapter(var list: MutableList<Item>?, val searchViewViewModel: SearchViewViewModel) : RecyclerView.Adapter<SearchItemsAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: SearchItemsListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SearchItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.searchItemNameTv.text = list!![position].itemName
        holder.binding.seatchCategoryTv.text = list!![position].category

        Glide.with(holder.binding.root.context)
            .load("${Credentials.BASE_URL}${list!![position].image}")
            .transform(CenterCrop(), RoundedCorners(15))
            .into(holder.binding.searchItemIv)

    }


    override fun getItemCount(): Int {
        return list?.let{
            it.size

        } ?: 0
    }
}
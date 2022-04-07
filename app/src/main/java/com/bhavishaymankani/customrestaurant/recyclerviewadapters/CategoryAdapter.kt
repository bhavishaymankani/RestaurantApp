package com.bhavishaymankani.customrestaurant.recyclerviewadapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhavishaymankani.customrestaurant.databinding.CategoryListBinding
import com.bhavishaymankani.customrestaurant.recyclerviewadapters.CategoryAdapter.ViewHolder
import com.bhavishaymankani.customrestaurant.ui.home.HomeFragment
import com.bhavishaymankani.customrestaurant.ui.home.OnCategoryClickListener

class CategoryAdapter(var list: MutableList<String>, val onCategoryClickListener: OnCategoryClickListener) : RecyclerView.Adapter<ViewHolder>() {
    inner class ViewHolder(var binding: CategoryListBinding, val onCategoryClickListener: OnCategoryClickListener) : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            onCategoryClickListener.onCategoryClick(adapterPosition)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding, onCategoryClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.categoryNameTv.text = list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
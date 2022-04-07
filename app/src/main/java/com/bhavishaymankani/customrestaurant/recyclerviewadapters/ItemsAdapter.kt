package com.bhavishaymankani.customrestaurant.recyclerviewadapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bhavishaymankani.customrestaurant.R
import com.bhavishaymankani.customrestaurant.databinding.ItemsListBinding
import com.bhavishaymankani.customrestaurant.datasource.local.CartItem
import com.bhavishaymankani.customrestaurant.datasource.models.Item
import com.bhavishaymankani.customrestaurant.datasource.utils.Credentials
import com.bhavishaymankani.customrestaurant.ui.home.HomeFragment
import com.bhavishaymankani.customrestaurant.ui.home.HomeViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class ItemsAdapter(var list: MutableList<Item>?, val homeViewModel: HomeViewModel) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ItemsListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.binding.root.context
        val typeTv = holder.binding.typeTv
        val addItemBtn = holder.binding.addItemBtn
        var qty = 0
        val qtyBtn =  holder.binding.quantityLayoutBtn
        val quantityTv = qtyBtn.quantityTv
        val addBtn = qtyBtn.add
        val removeBtn = qtyBtn.minus
        val (image, price, description, discount, itemName, id, type, category, stocks) = list!![position]
        holder.binding.itemNameTv.text = itemName
        val drawableId = if (type == "veg") {
            R.drawable.ic_vegetarian
        } else {
            R.drawable.ic_non_vegetarian
        }
        val drawable = ContextCompat.getDrawable(context, drawableId)
        typeTv.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, null, null, null)
        typeTv.text = if (type == "veg") " Pure $type" else " Non-veg"
        holder.binding.priceTv.text = "Rs. $price"
        holder.binding.descTv.text = description
        Glide.with(holder.binding.root.context)
            .load("${Credentials.BASE_URL}${image}")
            .transform(CenterCrop(), RoundedCorners(15))
            .into(holder.binding.itemIv)

        if (stocks.toInt() < 1) {
            addItemBtn.visibility = View.GONE
            holder.binding.itemsLayout.alpha = 0.5F
        }
        var cartItem: CartItem = CartItem(uid = "1", itemId = id,itemName = itemName, cost = price.toDouble(), quantity = qty, discount = discount.toDouble(), available = "yes")
        addItemBtn.setOnClickListener {
            it.visibility = View.GONE
            qtyBtn.qtyBtn.visibility = View.VISIBLE
            qty++
            quantityTv.text = "$qty"
            cartItem = CartItem(uid = "1", itemId = id,itemName = itemName, cost = price.toDouble(), quantity = qty, discount = discount.toDouble(), available = "yes")
            homeViewModel.addCartItem(cartItem)
            homeViewModel.getTotalItems()
            homeViewModel.getTotalCost()

        }

        cartItem.quantity = homeViewModel.getQuantity(id)

        if (cartItem.quantity > 0) {
            addItemBtn.visibility = View.GONE
            qtyBtn.qtyBtn.visibility = View.VISIBLE
            qty = cartItem.quantity
            quantityTv.text = "$qty"
            Log.d("homerv", cartItem.quantity.toString())
        }

        addBtn.setOnClickListener {
            qty++
            quantityTv.text = "${qty}"
            cartItem.quantity = qty
            cartItem.itemId = id
            Log.d("cartitem", cartItem.toString())
            homeViewModel.updateCartItem(cartItem)
            homeViewModel.getTotalItems()
            homeViewModel.getTotalCost()
        }
        removeBtn.setOnClickListener {
            qty--
            quantityTv.text = "${qty}"
            cartItem.quantity = qty
            cartItem.itemId = id
            Log.d("cartitem", cartItem.toString())
            homeViewModel.updateCartItem(cartItem)
            homeViewModel.getTotalItems()
            homeViewModel.getTotalCost()
            if (qty < 1) {
                qtyBtn.qtyBtn.visibility = View.GONE
                addItemBtn.visibility = View.VISIBLE
                qty = 0
                homeViewModel.deleteCartItem(cartItem)
            }

        }

    }


    override fun getItemCount(): Int {
        return list?.let{
            it.size

        } ?: 0
    }
}
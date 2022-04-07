package com.bhavishaymankani.customrestaurant.recyclerviewadapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bhavishaymankani.customrestaurant.CartViewModel
import com.bhavishaymankani.customrestaurant.databinding.OrderListBinding
import com.bhavishaymankani.customrestaurant.datasource.local.CartItem

class OrderListAdapter(var list: MutableList<CartItem>?, val cartViewModel: CartViewModel) : RecyclerView.Adapter<OrderListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: OrderListBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = OrderListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.binding.root.context
        val quantityBtn = holder.binding.quantityLayoutBtn
        val cartItemIv = holder.binding.cartItemIv
        val cartItemNameTv = holder.binding.cartItemNameTv
        val quantityTv = quantityBtn.quantityTv
        val addBtn = holder.binding.quantityLayoutBtn.add
        val removeBtn = holder.binding.quantityLayoutBtn.minus
        quantityBtn.qtyBtn.visibility = View.VISIBLE
        val cartItem = list!![position]
        var qty = cartItem.quantity

        cartItemNameTv.text = cartItem.itemName
        quantityBtn.quantityTv.text = qty.toString()

        addBtn.setOnClickListener {
            qty++
            quantityTv.text = "$qty"
            cartItem.quantity = qty
            Log.d("cartitem", cartItem.toString())
            cartViewModel.updateCartItem(cartItem)
            cartViewModel.getTotalItems()
            cartViewModel.getTotalCost()
        }
        removeBtn.setOnClickListener {
            qty--
            quantityTv.text = "$qty"
            cartItem.quantity = qty
            Log.d("cartitem", cartItem.toString())
            cartViewModel.updateCartItem(cartItem)
            cartViewModel.getTotalItems()
            cartViewModel.getTotalCost()
            if (qty < 1) {
                cartViewModel.deleteCartItem(cartItem)
                list!!.remove(cartItem)
            }
        }

    }


    override fun getItemCount(): Int {
        return list?.let{
            it.size

        } ?: 0
    }
}
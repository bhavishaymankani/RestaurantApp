package com.bhavishaymankani.customrestaurant.ui.home

import androidx.lifecycle.LiveData
import com.bhavishaymankani.customrestaurant.datasource.local.CartItem
import com.bhavishaymankani.customrestaurant.datasource.local.CartItemDatabase

class CartItemRepository(private val db: CartItemDatabase) {
    suspend fun insertCartItem(cartItem: CartItem) = db.cartDao().insertCartItem(cartItem)
    suspend fun deleteCartItem(cartItem: CartItem) = db.cartDao().deleteCartItem(cartItem)
    suspend fun updateCartItem(cartItem: CartItem) = db.cartDao().updateCartItem(cartItem)
    fun getTotalItems(): Int = db.cartDao().getTotalItems()
    fun getCartItems(): MutableList<CartItem> = db.cartDao().getCartItems()
    fun getTotalCost(): Double = db.cartDao().getTotalCost()
    fun getQuantity(itemId: String): Int = db.cartDao().getQuantity(itemId)
    fun deleteAllItems() = db.cartDao().deleteAllItems()
}
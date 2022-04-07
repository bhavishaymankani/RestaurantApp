package com.bhavishaymankani.customrestaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavishaymankani.customrestaurant.datasource.local.CartItem
import com.bhavishaymankani.customrestaurant.ui.home.CartItemRepository
import kotlinx.coroutines.launch

class CartViewModel(private val repository: CartItemRepository) : ViewModel() {

    private val _cartItems = MutableLiveData<MutableList<CartItem>>()
    val cartItems: LiveData<MutableList<CartItem>> = _cartItems

    private val _totalItems = MutableLiveData<Int>()
    val totalItems: LiveData<Int> = _totalItems

    private val _totalCost = MutableLiveData<Double>()
    val totalCost: LiveData<Double> = _totalCost

    fun getCartItems() = viewModelScope.launch {
        _cartItems.postValue(repository.getCartItems())
    }

    fun updateCartItem(cartItem: CartItem) = viewModelScope.launch {
        repository.updateCartItem(cartItem)
    }

    fun deleteCartItem(cartItem: CartItem) = viewModelScope.launch {
        repository.deleteCartItem(cartItem)
    }

    fun getTotalItems() = viewModelScope.launch {
        _totalItems.postValue(repository.getTotalItems())
    }

    fun getTotalCost() = viewModelScope.launch {
        _totalCost.postValue(repository.getTotalCost())
    }

    fun deleteAllItems() = viewModelScope.launch {
        repository.deleteAllItems()
    }
}
package com.bhavishaymankani.customrestaurant.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavishaymankani.customrestaurant.datasource.local.CartItem
import com.bhavishaymankani.customrestaurant.ui.home.CartItemRepository
import kotlinx.coroutines.launch

class NotificationsViewModel(private val repository: CartItemRepository) : ViewModel() {

    private val _cartItems = MutableLiveData<MutableList<CartItem>>()
    val cartItems: LiveData<MutableList<CartItem>> = _cartItems

    fun getCartItems() = viewModelScope.launch {
        repository.getCartItems().let {
            _cartItems.postValue(it)
        }
    }
}
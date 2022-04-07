package com.bhavishaymankani.customrestaurant.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavishaymankani.customrestaurant.datasource.local.CartItem
import com.bhavishaymankani.customrestaurant.datasource.response.ItemsResponse
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: CartItemRepository) : ViewModel() {

    private val _items = MutableLiveData<ItemsResponse>()
    val items: LiveData<ItemsResponse> = _items

    private val _cartItems = MutableLiveData<MutableList<CartItem>>()
    val cartItems: LiveData<MutableList<CartItem>> = _cartItems

    private val _totalItems = MutableLiveData<Int>()
    val totalItems: LiveData<Int> = _totalItems

    private val _totalCost = MutableLiveData<Double>()
    val totalCost: LiveData<Double> = _totalCost



    fun getItems(category: String) = viewModelScope.launch {
        HomeRepository.getItems(category).body().let {
            _items.postValue(it)
        }
    }

    fun addCartItem(cartItem: CartItem) = viewModelScope.launch {
        repository.insertCartItem(cartItem)
    }

    fun getCartItems() = viewModelScope.launch {
        repository.getCartItems().let {
            _cartItems.postValue(it)
        }
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

    fun getQuantity(itemId: String) = repository.getQuantity(itemId)


}
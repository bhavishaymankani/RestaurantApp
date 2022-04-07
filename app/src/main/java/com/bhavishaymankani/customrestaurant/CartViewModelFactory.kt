package com.bhavishaymankani.customrestaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bhavishaymankani.customrestaurant.ui.home.CartItemRepository

class CartViewModelFactory(private val repository: CartItemRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartViewModel(repository) as T
    }
}
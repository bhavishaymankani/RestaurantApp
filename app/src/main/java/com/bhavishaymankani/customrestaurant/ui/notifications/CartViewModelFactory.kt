package com.bhavishaymankani.customrestaurant.ui.notifications

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bhavishaymankani.customrestaurant.ui.home.CartItemRepository

class CartViewModelFactory(private val repository: CartItemRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotificationsViewModel(repository) as T
    }
}
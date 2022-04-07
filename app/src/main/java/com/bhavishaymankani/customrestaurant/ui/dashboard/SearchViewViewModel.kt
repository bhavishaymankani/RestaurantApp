package com.bhavishaymankani.customrestaurant.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavishaymankani.customrestaurant.datasource.response.ItemsResponse
import kotlinx.coroutines.launch

class SearchViewViewModel : ViewModel() {

    private val _items = MutableLiveData<ItemsResponse>()
    val items: LiveData<ItemsResponse> = _items


    fun searchItems(searchQuery: String) = viewModelScope.launch {
        SearchViewRepository.searchItems(searchQuery).body().let {
            _items.postValue(it)
        }
    }
}
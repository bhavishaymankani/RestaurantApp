package com.bhavishaymankani.customrestaurant

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavishaymankani.customrestaurant.datasource.response.AddressesResponse
import kotlinx.coroutines.launch

class AddressesViewModel : ViewModel() {
    private val _addresses = MutableLiveData<AddressesResponse>()
    val addresses: LiveData<AddressesResponse> = _addresses

    fun getAddresses(uid: String) = viewModelScope.launch {
        AddressesRepository.getAddresses(uid).body().let {
            _addresses.postValue(it)
        }
    }
}
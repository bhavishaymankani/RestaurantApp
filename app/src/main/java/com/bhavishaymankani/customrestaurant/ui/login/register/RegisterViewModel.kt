package com.bhavishaymankani.customrestaurant.ui.login.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavishaymankani.customrestaurant.datasource.response.UserResponse
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    val _addUser = MutableLiveData<UserResponse>()
    val addUser: LiveData<UserResponse> = _addUser

    fun addUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        mobNum: String
    ) = viewModelScope.launch {
        UserRegisterationRepository.addUser(firstName, lastName, email, password, mobNum).body().let {
            _addUser.postValue(it)
            if (it != null) {
                Log.d("regvm", it.users[it.users.size - 1].toString())
            }
            Log.d("regvm", "qw")
        } ?: Log.d("regvm", "null")
    }
}
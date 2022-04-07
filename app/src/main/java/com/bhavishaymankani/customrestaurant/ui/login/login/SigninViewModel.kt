package com.bhavishaymankani.customrestaurant.ui.login.login

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bhavishaymankani.customrestaurant.datasource.models.User
import kotlinx.coroutines.launch

class SigninViewModel : ViewModel() {

    private val TAG = "viewmodel"

    private val _validateUser = MutableLiveData<User>()
    val validateUser: LiveData<User> = _validateUser

    fun validateUser(email: String, password: String) = viewModelScope.launch {
        UserLoginRepository.validateUser(email, password)?.body()?.let {
            _validateUser.postValue(it)
        } ?:  Log.d(TAG, "Something went wrong")
    }
}
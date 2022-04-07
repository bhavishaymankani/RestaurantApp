package com.bhavishaymankani.customrestaurant.ui.login.login

import com.bhavishaymankani.customrestaurant.datasource.requests.Services

object UserLoginRepository {
    private val api = Services.restaurantApi

    suspend fun validateUser(email: String, password: String) = api.validateUser(email, password)

}
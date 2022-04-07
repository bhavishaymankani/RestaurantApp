package com.bhavishaymankani.customrestaurant.ui.login.register

import com.bhavishaymankani.customrestaurant.datasource.requests.Services

object UserRegisterationRepository {
    private val api = Services.restaurantApi

    suspend fun addUser(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        mobNum: String
    ) = api.registerUser(firstName, lastName, email, password, mobNum)

}
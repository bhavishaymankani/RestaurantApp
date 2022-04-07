package com.bhavishaymankani.customrestaurant

import com.bhavishaymankani.customrestaurant.utils.Constants.api

object AddressesRepository {

    suspend fun getAddresses(uid: String) = api.getAddresses(uid)
}
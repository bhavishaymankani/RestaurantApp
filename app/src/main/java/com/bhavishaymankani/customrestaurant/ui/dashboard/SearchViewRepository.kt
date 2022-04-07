package com.bhavishaymankani.customrestaurant.ui.dashboard

import com.bhavishaymankani.customrestaurant.datasource.requests.Services

object SearchViewRepository {
    private val api = Services.restaurantApi

    suspend fun searchItems(searchQuery: String) = api.searchItems(searchQuery)
}
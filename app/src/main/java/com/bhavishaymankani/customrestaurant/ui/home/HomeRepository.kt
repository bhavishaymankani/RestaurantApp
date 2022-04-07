package com.bhavishaymankani.customrestaurant.ui.home

import androidx.room.Room
import com.bhavishaymankani.customrestaurant.utils.Constants.api

object HomeRepository {

    suspend fun getItems(category: String) = api.getItems(category)


}
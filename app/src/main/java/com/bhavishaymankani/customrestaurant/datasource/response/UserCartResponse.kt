package com.bhavishaymankani.customrestaurant.datasource.response

import com.bhavishaymankani.customrestaurant.datasource.models.UserCart
import com.google.gson.annotations.SerializedName

data class UserCartResponse(

	@field:SerializedName("user_cart")
	val userCart: List<UserCart>
)


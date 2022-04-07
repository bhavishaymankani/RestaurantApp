package com.bhavishaymankani.customrestaurant.datasource.response

import com.bhavishaymankani.customrestaurant.datasource.models.Item
import com.google.gson.annotations.SerializedName

data class ItemsResponse(

	@field:SerializedName("items")
	val items: MutableList<Item>
)


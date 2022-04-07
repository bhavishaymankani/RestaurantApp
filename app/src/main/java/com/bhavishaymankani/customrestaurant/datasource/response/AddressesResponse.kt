package com.bhavishaymankani.customrestaurant.datasource.response

import com.bhavishaymankani.customrestaurant.datasource.models.Address
import com.google.gson.annotations.SerializedName

data class AddressesResponse(

	@field:SerializedName("address")
	val addresses: List<Address>
)



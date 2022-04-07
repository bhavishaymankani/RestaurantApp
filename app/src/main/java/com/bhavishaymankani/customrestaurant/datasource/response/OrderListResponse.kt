package com.bhavishaymankani.customrestaurant.datasource.response

import android.os.Parcelable
import com.bhavishaymankani.customrestaurant.datasource.models.OrderList
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderListResponse(

	@field:SerializedName("order_list")
	val orderList: List<OrderList>
) : Parcelable



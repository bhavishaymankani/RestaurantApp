package com.bhavishaymankani.customrestaurant.datasource.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OrderList(

    @field:SerializedName("quantity")
    val quantity: String,

    @field:SerializedName("cost")
    val cost: String,

    @field:SerializedName("coupon")
    val coupon: String,

    @field:SerializedName("item_id")
    val itemId: String,

    @field:SerializedName("discount")
    val discount: String,

    @field:SerializedName("item_name")
    val itemName: String,

    @field:SerializedName("uid")
    val uid: String,

    @field:SerializedName("delivery_cost")
    val deliveryCost: String,

    @field:SerializedName("order_period")
    val orderPeriod: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("order_id")
    val orderId: String,

    @field:SerializedName("payment_method")
    val paymentMethod: String,

    @field:SerializedName("status")
    val status: String
) : Parcelable
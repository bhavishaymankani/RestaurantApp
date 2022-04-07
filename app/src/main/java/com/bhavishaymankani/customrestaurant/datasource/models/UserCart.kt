package com.bhavishaymankani.customrestaurant.datasource.models

import com.google.gson.annotations.SerializedName

data class UserCart(

    @field:SerializedName("uid")
    val uid: String,

    @field:SerializedName("quantity")
    val quantity: String,

    @field:SerializedName("cost")
    val cost: String,

    @field:SerializedName("item_id")
    val itemId: String,

    @field:SerializedName("instruction")
    val instruction: String,

    @field:SerializedName("available")
    val available: String,

    @field:SerializedName("discount")
    val discount: String,

    @field:SerializedName("item_name")
    val itemName: String,

    @field:SerializedName("id")
    val id: String
)

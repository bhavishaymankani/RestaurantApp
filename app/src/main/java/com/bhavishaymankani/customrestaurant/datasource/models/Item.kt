package com.bhavishaymankani.customrestaurant.datasource.models

import com.google.gson.annotations.SerializedName

data class Item(

    @field:SerializedName("image")
    val image: String,

    @field:SerializedName("price")
    val price: String,

    @field:SerializedName("description")
    val description: String,

    @field:SerializedName("discount")
    val discount: String,

    @field:SerializedName("item_name")
    val itemName: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("type")
    val type: String,

    @field:SerializedName("category")
    val category: String,

    @field:SerializedName("stocks")
    val stocks: String
)

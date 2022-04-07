package com.bhavishaymankani.customrestaurant.datasource.models

import com.google.gson.annotations.SerializedName

data class Address(

    @field:SerializedName("uid")
    val uid: String,

    @field:SerializedName("house_num")
    val houseNum: String,

    @field:SerializedName("pin_code")
    val pinCode: String,

    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("address_name")
    val addressName: String,

    @field:SerializedName("landmark")
    val landmark: String,

    @field:SerializedName("building")
    val building: String,

    @field:SerializedName("selected")
    val selected: String
)
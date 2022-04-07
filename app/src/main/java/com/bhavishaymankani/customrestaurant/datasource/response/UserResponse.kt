package com.bhavishaymankani.customrestaurant.datasource.response

import com.bhavishaymankani.customrestaurant.datasource.models.User
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @field:SerializedName("users")
    val users: List<User>
)

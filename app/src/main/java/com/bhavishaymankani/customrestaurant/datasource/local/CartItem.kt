package com.bhavishaymankani.customrestaurant.datasource.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItem(
    /*@PrimaryKey(autoGenerate = true)
    var id: Int? = null,*/
    @PrimaryKey(autoGenerate = false)
    var itemId: String,
    val uid: String,
    var quantity: Int,
    val cost: Double,
    val available: String,
    val discount: Double,
    val itemName: String
)
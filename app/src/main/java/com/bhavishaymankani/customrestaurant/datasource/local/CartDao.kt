package com.bhavishaymankani.customrestaurant.datasource.local

import androidx.room.*

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItem)

    @Delete
    suspend fun deleteCartItem(cartItem: CartItem)

    @Update
    suspend fun updateCartItem(cartItem: CartItem)

    @Query("SELECT * FROM cart_item")
    fun getCartItems(): MutableList<CartItem>

    @Query("SELECT SUM(cost * quantity - discount) FROM cart_item")
    fun getTotalCost(): Double

    @Query("SELECT SUM(quantity) FROM cart_item")
    fun getTotalItems(): Int

    @Query("SELECT quantity FROM cart_item WHERE itemId=:itemId")
    fun getQuantity(itemId: String): Int

    @Query("DELETE FROM cart_item")
    fun deleteAllItems()
}
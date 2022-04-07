package com.bhavishaymankani.customrestaurant.datasource.utils

import com.bhavishaymankani.customrestaurant.datasource.models.User
import com.bhavishaymankani.customrestaurant.datasource.response.AddressesResponse
import com.bhavishaymankani.customrestaurant.datasource.response.ItemsResponse
import com.bhavishaymankani.customrestaurant.datasource.response.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RestaurantApi {
    // http://192.168.1.5/custom-restaurant/user-auth.php?email=abc@gmail.com&password=1234

    @GET("user-auth.php")
    suspend fun validateUser(
        @Query("email") email: String,
        @Query("password") password: String
    ): Response<User>?

    @GET("user.php")
    suspend fun getUserById(
        @Query("id") id: String
    ): Response<User>

    @POST("users.php")
    suspend fun registerUser(
        @Query("first_name") firstName: String,
        @Query("last_name") lastName: String,
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("mob_num") mobNum: String
    ): Response<UserResponse>

    @GET("addresses.php")
    suspend fun getAddresses(
        @Query("uid") uid: String
    ): Response<AddressesResponse>

    @POST("addresses.php")
    suspend fun addAddress(
        @Query("uid") uid: String,
        @Query("house_num") houseNum: String,
        @Query("building") building: String,
        @Query("landmark") landmark: String,
        @Query("pin_code") pinCode: String,
        @Query("address_name") addressName: String,
        @Query("selected") selected: String
    ): Response<AddressesResponse>

    @GET("items.php")
    suspend fun getItems(
        @Query("category") category: String
    ): Response<ItemsResponse>

    @GET("item-search.php")
    suspend fun searchItems(
        @Query("search_query") searchQuery: String
    ): Response<ItemsResponse>

}
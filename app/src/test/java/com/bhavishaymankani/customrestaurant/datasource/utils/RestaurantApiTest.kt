package com.bhavishaymankani.customrestaurant.datasource.utils

import com.bhavishaymankani.customrestaurant.datasource.models.User
import com.bhavishaymankani.customrestaurant.datasource.requests.Services
import com.bhavishaymankani.customrestaurant.datasource.response.AddressesResponse
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response
import java.lang.Exception

class RestaurantApiTest {

    private lateinit var api: RestaurantApi

    @Before
    fun setUp() {
        api = Services.Companion.restaurantApi
    }

    @Test
    fun `if true return user's info`() = runBlocking {

        val userData = api.validateUser("abc@123.com", "1234")

        val response = userData

        assertNotNull(response?.body())
    }

    @Test
    fun `return user's addresses`()  = runBlocking {

        val addresses = api.getAddresses("3")

        val response = addresses

        assertNotNull(response.body())
    }

    @Test
    fun `register user return true`() = runBlocking {

        val response = api.registerUser(
            "qedpkojij",
            "asdfghj",
            "abc123.com",
            "1234",
            "9876543321"
        )

        assertNotNull(response.body())
    }

    @Test
    fun `Add address`() = runBlocking {
        val response = api.addAddress(
            "1",
            "B/64",
            "Saptarishi Building Shrishti Sector - 2",
            "Bhaktivedanta Hospital",
            "401107",
            "Home",
            "0"
        )

        assertNotNull(response.body())
    }
    @Test
    fun `return list of items`() = runBlocking {
        val response = api.getItems("Pizza")

        assertNotNull(response.body())
    }

    @Test
    fun `return search list of items`() = runBlocking {
        val response = api.searchItems("P")

        assertNotNull(response.body())
    }
}
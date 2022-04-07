package com.bhavishaymankani.customrestaurant.datasource.requests

import com.bhavishaymankani.customrestaurant.datasource.utils.Credentials.BASE_URL
import com.bhavishaymankani.customrestaurant.datasource.utils.RestaurantApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Services {

    companion object {
        private val gson = GsonBuilder().setLenient().create()!!
        private val httpLoggingInterceptor = HttpLoggingInterceptor()

        private val okHttpClient = OkHttpClient.Builder()
            .addInterceptor {
                val request = it.request().newBuilder().build()
                it.proceed(request)
            }
            .addInterceptor(httpLoggingInterceptor)
            .build()

        private val retrofitBuilder = Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addConverterFactory(GsonConverterFactory.create(gson))
            client(okHttpClient)
        }
        private val retrofit = retrofitBuilder.build()

        // Restaurant Api.
         val restaurantApi = retrofit.create(RestaurantApi::class.java)
            get() = field
    }


}
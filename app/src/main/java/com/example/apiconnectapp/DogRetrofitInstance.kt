package com.example.apiconnectapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object for managing Retrofit instance for Dog CEO API
 * Uses lazy initialization to create the instance only when first accessed
 *
 * Design Pattern: Singleton
 * Ensures only one Retrofit instance exists throughout the app lifecycle
 */
object DogRetrofitInstance {
    // Base URL for the Dog CEO API
    private const val BASE_URL = "https://dog.ceo/api/"

    /**
     * Lazy-initialized API service instance
     * Created only when first accessed via this property
     */
    val api: DogApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DogApiService::class.java)
    }
}

package com.example.apiconnectapp

import retrofit2.http.GET

/**
 * API Service Interface for Dog CEO API
 * Defines endpoints for fetching random dog images
 *
 * Base URL: https://dog.ceo/api/
 */
interface DogApiService {
    /**
     * Fetches a random dog image URL from the Dog CEO API
     * Uses suspend function for coroutine integration
     *
     * @return DogResponse containing the image URL and status
     */
    @GET("breeds/image/random")
    suspend fun getRandomDogImage(): DogResponse
}

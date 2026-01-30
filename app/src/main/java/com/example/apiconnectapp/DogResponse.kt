package com.example.apiconnectapp

import com.google.gson.annotations.SerializedName

/**
 * Data class representing the response from the Dog CEO API
 * Maps JSON fields to Kotlin properties using Gson annotations
 *
 * Example JSON response:
 * {
 *   "message": "https://images.dog.ceo/breeds/...",
 *   "status": "success"
 * }
 */
data class DogResponse(
    @SerializedName("message")
    val message: String,  // URL of the random dog image

    @SerializedName("status")
    val status: String    // Status of the API response ("success" or "error")
)

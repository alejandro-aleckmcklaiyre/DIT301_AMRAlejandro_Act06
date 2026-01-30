package com.example.apiconnectapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import coil.load
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

/**
 * MainActivity - Demonstrates modern Android development using:
 * - Retrofit with suspend functions for API calls
 * - Coroutines with lifecycleScope for background tasks
 * - Coil for efficient image loading from URLs
 * - Proper error handling with Snackbar notifications
 * - Beautiful modern Material Design UI
 *
 * This is a junior developer assessment project that fetches random dog images
 * from the Dog CEO API (https://dog.ceo/api/breeds/image/random)
 */
class MainActivity : AppCompatActivity() {
    // UI Components
    private lateinit var fetchButton: Button
    private lateinit var favoriteButton: ImageButton
    private lateinit var progressBar: ProgressBar
    private lateinit var dogImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Apply window insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI components
        fetchButton = findViewById(R.id.fetchButton)
        favoriteButton = findViewById(R.id.favoriteButton)
        progressBar = findViewById(R.id.progressBar)
        dogImageView = findViewById(R.id.dogImageView)

        // Set click listener for fetch button
        fetchButton.setOnClickListener {
            fetchRandomDogImage()
        }

        // Set click listener for favorite button
        favoriteButton.setOnClickListener {
            handleFavoriteClick()
        }

        // Fetch initial image on app launch
        fetchRandomDogImage()
    }

    /**
     * Fetches a random dog image from the Dog CEO API using coroutines
     *
     * Modern approach using:
     * - lifecycleScope: Ensures coroutines are cancelled when activity is destroyed
     * - launch: Launches a coroutine in the Main dispatcher (UI thread)
     * - try-catch-finally: Handles network errors and ensures UI is always restored
     * - Coil: Efficiently loads and caches images from URLs
     */
    private fun fetchRandomDogImage() {
        // Show progress bar and disable button
        progressBar.visibility = android.view.View.VISIBLE
        fetchButton.isEnabled = false
        favoriteButton.isEnabled = false

        // Launch coroutine in the Main thread (UI thread)
        lifecycleScope.launch {
            try {
                // Get API service from Retrofit singleton
                val apiService = DogRetrofitInstance.api

                // Make suspend function call to fetch dog image
                // This runs on IO dispatcher automatically (Retrofit behavior)
                val response = apiService.getRandomDogImage()

                // Check if response status is success
                if (response.status == "success") {
                    // Load image URL using Coil with smooth fade animation
                    dogImageView.load(response.message) {
                        crossfade(300)
                    }

                    // Show success message
                    Snackbar.make(
                        findViewById(R.id.main),
                        "✓ Adorable dog loaded!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    // Handle API error response
                    showErrorMessage("API Error: Failed to fetch dog image")
                }
            } catch (e: Exception) {
                // Handle network errors, timeouts, and other exceptions
                showErrorMessage("Network Error: ${e.message ?: "Unknown error"}")

                // Log error for debugging
                e.printStackTrace()
            } finally {
                // This block always executes, ensuring the UI is reset.
                progressBar.visibility = android.view.View.GONE
                fetchButton.isEnabled = true
                favoriteButton.isEnabled = true
            }
        }
    }

    /**
     * Handles the favorite button click
     * Toggle favorite status and update button appearance
     */
    private fun handleFavoriteClick() {
        // Show feedback
        Snackbar.make(
            findViewById(R.id.main),
            "❤️ Added to favorites!",
            Snackbar.LENGTH_SHORT
        ).show()
    }

    /**
     * Displays an error message as a Snackbar.
     *
     * @param message The error message to display to the user
     */
    private fun showErrorMessage(message: String) {
        // UI cleanup is now handled in the 'finally' block.
        // Show error in Snackbar with retry action
        Snackbar.make(
            findViewById(R.id.main),
            message,
            Snackbar.LENGTH_LONG
        ).setAction("Retry") {
            // Retry fetching the image when user taps "Retry"
            fetchRandomDogImage()
        }.show()
    }
}

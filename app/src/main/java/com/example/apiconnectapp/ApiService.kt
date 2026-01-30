package com.example.apiconnectapp

import retrofit2.Call
import retrofit2.http.GET

/**
 * API Service Interface for JSONPlaceholder API
 * Defines endpoints for fetching posts
 */
interface ApiService {
    /**
     * Fetches a list of posts from the JSONPlaceholder API
     * @return Call object containing a list of Post objects
     */
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    /**
     * Fetches a single post by ID from the JSONPlaceholder API
     * @param id The ID of the post to fetch
     * @return Call object containing a single Post object
     */
    @GET("posts/{id}")
    fun getPostById(id: Int): Call<Post>
}

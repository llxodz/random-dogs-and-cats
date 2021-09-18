package com.codinginflow.randompicture.api

import retrofit2.http.GET

const val BASE_URL = "https://aws.random.cat"

interface ApiRequest {

    @GET("/meow")
    suspend fun getRandomDog(): ApiData
}
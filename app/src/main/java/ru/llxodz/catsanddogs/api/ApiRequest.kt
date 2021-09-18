package ru.llxodz.catsanddogs.api

import retrofit2.http.GET

const val BASE_URL_CATS = "https://aws.random.cat"
const val BASE_URL_DOGS = "https://random.dog"

interface ApiRequest {

    @GET("/meow")
    suspend fun getRandomCat(): ApiDataCat

    @GET("/woof.json")
    suspend fun getRandomDog(): ApiDataDog
}

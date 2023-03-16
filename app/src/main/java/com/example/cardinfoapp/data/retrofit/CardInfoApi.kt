package com.example.cardinfoapp.data.retrofit

import com.example.cardinfoapp.data.retrofit.models.CardInfoRetrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface CardInfoApi {
    @GET("{bin}")
    suspend fun getCardByBin(@Path("bin") bin: String): CardInfoRetrofit
}
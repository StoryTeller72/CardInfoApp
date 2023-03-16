package com.example.cardinfoapp.retrofit

import com.example.cardinfoapp.retrofit.models.CardInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface CardInfoApi {
    @GET("{bin}")
    suspend fun getCardByBin(@Path("bin") bin: String): CardInfo
}
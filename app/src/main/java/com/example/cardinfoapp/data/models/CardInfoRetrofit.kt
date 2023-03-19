package com.example.cardinfoapp.data.retrofit.models

data class CardInfoRetrofit(
    val bank: Bank,
    val brand: String,
    val country: Country,
    val number: Number,
    val prepaid: Boolean,
    val scheme: String,
    val type: String
)
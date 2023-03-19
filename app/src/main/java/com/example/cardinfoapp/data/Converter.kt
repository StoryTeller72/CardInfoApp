package com.example.cardinfoapp.data

import com.example.cardinfoapp.data.retrofit.models.CardInfoRetrofit
import com.example.cardinfoapp.data.room.CardItemRoom
import com.example.cardinfoapp.utils.Resource
import retrofit2.Response

class Converter {
    fun cardInfoRetrofitToCardItemRoom( cardRetrofit: Response<CardInfoRetrofit>, date: String, bin: String): CardItemRoom =
        CardItemRoom(
            id = null,
            bin = bin,
            length = cardRetrofit.body()?.number?.length,
            luhn = cardRetrofit.body()?.number?.luhn,
            sheme = cardRetrofit.body()?.scheme ?: "?",
            type = cardRetrofit.body()?.type ?: "?",
            brand = cardRetrofit.body()?.brand?: "?",
            prepaid = cardRetrofit.body()?.prepaid,
            numeric = cardRetrofit.body()?.country?.numeric ?: "?",
            alpha2 = cardRetrofit.body()?.country?.alpha2?: "?",
            countryName = cardRetrofit.body()?.country?.name ?: "?",
            emoji = cardRetrofit.body()?.country?.emoji?: "?",
            currency = cardRetrofit.body()?.country?.currency?: "?",
            latitude = cardRetrofit.body()?.country?.latitude,
            longitude = cardRetrofit.body()?.country?.longitude,
            bankName = cardRetrofit.body()?.bank?.name?: "?",
            url = cardRetrofit.body()?.bank?.url?: "?",
            phone = cardRetrofit.body()?.bank?.phone?: "?",
            city = cardRetrofit.body()?.bank?.city?: "?",
            dateRequest = date
        )
}
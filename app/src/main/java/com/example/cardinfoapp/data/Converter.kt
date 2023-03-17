package com.example.cardinfoapp.data

import com.example.cardinfoapp.data.retrofit.models.CardInfoRetrofit
import com.example.cardinfoapp.data.room.CardItemRoom

class Converter {
    fun cardInfoRetrofitToCardItemRoom(cardRetrofit: CardInfoRetrofit, date: String, bin: String): CardItemRoom =
        CardItemRoom(
            id = null,
            bin = bin,
            length = cardRetrofit.number.length,
            luhn = cardRetrofit.number.luhn,
            sheme = cardRetrofit.scheme,
            type = cardRetrofit.type,
            brand = cardRetrofit.brand,
            prepaid = cardRetrofit.prepaid,
            numeric = cardRetrofit.country.numeric,
            alpha2 = cardRetrofit.country.alpha2,
            countryName = cardRetrofit.country.name,
            emoji = cardRetrofit.country.emoji,
            currency = cardRetrofit.country.currency,
            latitude = cardRetrofit.country.latitude,
            longitude = cardRetrofit.country.longitude,
            bankName = cardRetrofit.bank.name,
            url = cardRetrofit.bank.url,
            phone = cardRetrofit.bank.phone,
            city = cardRetrofit.bank.city,
            dateRequest = date
        )
}
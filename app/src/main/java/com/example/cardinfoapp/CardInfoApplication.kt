package com.example.cardinfoapp

import android.app.Application
import com.example.cardinfoapp.data.room.CardDatabase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CardInfoApplication: Application() {
    val database: CardDatabase by lazy { CardDatabase.getDatabase(this) }
    val retrofit: Retrofit by lazy {Retrofit.Builder ()
        .baseUrl(Constance.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }
}
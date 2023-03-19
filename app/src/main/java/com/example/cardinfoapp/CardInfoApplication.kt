package com.example.cardinfoapp

import android.app.Application
import com.example.cardinfoapp.data.repository.CardRepositoryRoomImpl
import com.example.cardinfoapp.data.retrofit.CardInfoApi
import com.example.cardinfoapp.data.room.CardDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CardInfoApplication: Application() {
    private val database: CardDatabase by lazy { CardDatabase.getDatabase(this) }

    private val interceptor = createInterceptor()

    private fun createInterceptor(): HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private val client by lazy {
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    val retrofit: Retrofit by lazy {Retrofit.Builder ()
        .baseUrl(Constance.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }
    val cardApi: CardInfoApi by lazy {   retrofit.create(CardInfoApi::class.java)}

    val repositoryRoomImpl by lazy {   CardRepositoryRoomImpl(database.getDao())}
}
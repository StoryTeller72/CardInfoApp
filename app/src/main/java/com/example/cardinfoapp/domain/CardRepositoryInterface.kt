package com.example.cardinfoapp.domain

import androidx.lifecycle.LiveData
import com.example.cardinfoapp.data.retrofit.models.CardInfoRetrofit
import com.example.cardinfoapp.data.room.CardItemRoom

interface CardRepositoryInterface {
    fun getAllCard(): LiveData<List<CardItemRoom>>

    suspend fun addNewCard(cardRetrofit: CardInfoRetrofit, bin: String)

    suspend fun getCard(id: Int): CardItemRoom
}
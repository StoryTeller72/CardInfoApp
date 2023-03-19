package com.example.cardinfoapp.data.repository

import androidx.lifecycle.asLiveData
import com.example.cardinfoapp.data.Converter
import com.example.cardinfoapp.data.retrofit.models.CardInfoRetrofit
import com.example.cardinfoapp.data.room.CardDao
import com.example.cardinfoapp.data.room.CardItemRoom
import kotlinx.coroutines.coroutineScope
import retrofit2.Response
import java.time.LocalDate

class CardRepositoryRoomImpl(private val roomDao: CardDao){

    fun getAllCard() = roomDao.getAllCards().asLiveData()

    suspend fun addNewCard(cardRetrofit: Response<CardInfoRetrofit>, bin: String) {
        val dateTime = LocalDate.now().toString()

        val card = Converter().cardInfoRetrofitToCardItemRoom(cardRetrofit, dateTime, bin)
        roomDao.insertItem(card)
    }


    suspend fun getCard(bin: String): CardItemRoom {
        return coroutineScope { roomDao.getCard(bin) }
    }
}
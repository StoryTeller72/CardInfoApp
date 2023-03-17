package com.example.cardinfoapp.data.repository

import android.content.Context
import android.icu.text.DateFormat
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.cardinfoapp.data.Converter
import com.example.cardinfoapp.data.retrofit.models.CardInfoRetrofit
import com.example.cardinfoapp.data.room.CardDao
import com.example.cardinfoapp.data.room.CardItemRoom
import com.example.cardinfoapp.domain.CardRepositoryInterface

class CardRepositoryRoomImpl(private val roomDao: CardDao) : CardRepositoryInterface {

    override fun getAllCard() = roomDao.getAllCards().asLiveData()

    override suspend fun addNewCard(cardRetrofit: CardInfoRetrofit, bin: String) {
        val date = DateFormat.getDateInstance().format("dd-MM-yyyy")
        val card = Converter().cardInfoRetrofitToCardItemRoom(cardRetrofit, date, bin)
        roomDao.insertItem(card)
    }

    override fun getCard(bin: String): LiveData<CardItemRoom> {
        return roomDao.getCard(bin).asLiveData()
    }
}
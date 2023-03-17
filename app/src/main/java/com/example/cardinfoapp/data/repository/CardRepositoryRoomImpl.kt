package com.example.cardinfoapp.data.repository

import android.content.Context
import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.cardinfoapp.data.Converter
import com.example.cardinfoapp.data.retrofit.models.CardInfoRetrofit
import com.example.cardinfoapp.data.room.CardDao
import com.example.cardinfoapp.data.room.CardItemRoom
import com.example.cardinfoapp.domain.CardRepositoryInterface
import java.time.LocalDate

class CardRepositoryRoomImpl(private val roomDao: CardDao) : CardRepositoryInterface {

    override fun getAllCard() = roomDao.getAllCards().asLiveData()

    override suspend fun addNewCard(cardRetrofit: CardInfoRetrofit, bin: String) {
        val calendar = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
        val dateTime = simpleDateFormat.format(calendar.time).toString()
        val card = Converter().cardInfoRetrofitToCardItemRoom(cardRetrofit, dateTime, bin)
        roomDao.insertItem(card)
    }


    override fun getCard(bin: String): LiveData<CardItemRoom> {
        return roomDao.getCard(bin).asLiveData()
    }
}
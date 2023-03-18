package com.example.cardinfoapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.cardinfoapp.data.Converter
import com.example.cardinfoapp.data.retrofit.models.CardInfoRetrofit
import com.example.cardinfoapp.data.room.CardDao
import com.example.cardinfoapp.data.room.CardItemRoom
import com.example.cardinfoapp.domain.CardRepositoryInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.time.LocalDate

class CardRepositoryRoomImpl(private val roomDao: CardDao) : CardRepositoryInterface {

    override fun getAllCard() = roomDao.getAllCards().asLiveData()

    override suspend fun addNewCard(cardRetrofit: CardInfoRetrofit, bin: String) {
        val dateTime = LocalDate.now().toString()

        val card = Converter().cardInfoRetrofitToCardItemRoom(cardRetrofit, dateTime, bin)
        roomDao.insertItem(card)
    }


    override suspend fun getCard(id: Int): CardItemRoom {
        val current = coroutineScope { roomDao.getCard(id) }
        Log.d("testDate", "repository ${current.toString()}")
        return current
    }
}
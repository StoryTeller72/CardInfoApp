package com.example.cardinfoapp.domain

import com.example.cardinfoapp.data.repository.CardRepositoryRoomImpl
import com.example.cardinfoapp.data.retrofit.CardInfoApi
import com.example.cardinfoapp.data.retrofit.models.CardInfoRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MakeResponseUseCase(
    private val retrofitApi: CardInfoApi,
    private val repositoryRoomImpl: CardRepositoryRoomImpl
    ) {
    fun execute(bin: String){
        CoroutineScope(Dispatchers.IO).launch {
            val cardFromRetrofit = retrofitApi.getCardByBin(bin)
            repositoryRoomImpl.addNewCard(cardFromRetrofit, bin)
        }
    }
}
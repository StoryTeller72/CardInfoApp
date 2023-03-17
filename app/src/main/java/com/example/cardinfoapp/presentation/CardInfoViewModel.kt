package com.example.cardinfoapp.presentation

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cardinfoapp.data.repository.CardRepositoryRoomImpl
import com.example.cardinfoapp.data.retrofit.CardInfoApi
import com.example.cardinfoapp.data.room.CardItemRoom
import com.example.cardinfoapp.domain.MakeResponseUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardInfoViewModel(
    private val makeResponseUseCase: MakeResponseUseCase,
    private val repositoryRoomImpl: CardRepositoryRoomImpl
    ): ViewModel()
{
    private val _currentBin = MutableLiveData<String>()
    val currentBin: LiveData<String>
    get() = _currentBin

    private val _allCardsLis = repositoryRoomImpl.getAllCard()
    val allCard: LiveData<List<CardItemRoom>>
        get() = _allCardsLis

    fun makeResponse(bin: String){
       makeResponseUseCase.execute(bin)
    }
}

class CardInfoViewModelFactory(
    private val repository: CardRepositoryRoomImpl,
    private val retrofitApi: CardInfoApi
    ): ViewModelProvider.Factory{
        private val makeResponseUseCase = MakeResponseUseCase(retrofitApi, repository)
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CardInfoViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CardInfoViewModel(makeResponseUseCase, repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


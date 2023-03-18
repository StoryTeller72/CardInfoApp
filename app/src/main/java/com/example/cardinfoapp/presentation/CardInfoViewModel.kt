package com.example.cardinfoapp.presentation

import android.util.Log
import androidx.lifecycle.*
import com.example.cardinfoapp.data.repository.CardRepositoryRoomImpl
import com.example.cardinfoapp.data.retrofit.CardInfoApi
import com.example.cardinfoapp.data.room.CardItemRoom
import com.example.cardinfoapp.domain.MakeResponseUseCase
import kotlinx.coroutines.launch

class CardInfoViewModel(
    private val makeResponseUseCase: MakeResponseUseCase,
    private val repositoryRoomImpl: CardRepositoryRoomImpl
    ): ViewModel()
{


    private var _currentCard = MutableLiveData<CardItemRoom>()
    val currentCard: LiveData<CardItemRoom>
        get() = _currentCard


    private val _allCardsLis = repositoryRoomImpl.getAllCard()
    val allCard: LiveData<List<CardItemRoom>>
        get() = _allCardsLis

    fun makeResponse(bin: String){
       makeResponseUseCase.execute(bin)
    }

    fun getCard(id:Int){
//        Log.d("testDate", bin)
        viewModelScope.launch {
            _currentCard.value = repositoryRoomImpl.getCard(id)
            Log.d("testDate", "view model${_currentCard.value.toString()}")
        }
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


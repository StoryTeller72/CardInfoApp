package com.example.cardinfoapp.presentation

import android.util.Log
import androidx.lifecycle.*
import com.example.cardinfoapp.data.repository.CardRepositoryRoomImpl
import com.example.cardinfoapp.data.retrofit.CardInfoApi
import com.example.cardinfoapp.data.retrofit.models.CardInfoRetrofit
import com.example.cardinfoapp.data.room.CardItemRoom
import com.example.cardinfoapp.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class CardInfoViewModel(
    private val repositoryRoomImpl: CardRepositoryRoomImpl,
    private val retrofitApi: CardInfoApi
    ): ViewModel()
{

    private var _currentCardRes : MutableLiveData<Resource<CardInfoRetrofit>> = MutableLiveData()
    val currentCardRes: LiveData<Resource<CardInfoRetrofit>>
        get() = _currentCardRes


    private var _currentCard = MutableLiveData<CardItemRoom>()
    val currentCard: LiveData<CardItemRoom>
        get() = _currentCard


    private val _allCardsLis = repositoryRoomImpl.getAllCard()
    val allCard: LiveData<List<CardItemRoom>>
        get() = _allCardsLis

     fun makeResponse(bin: String) {
         _currentCardRes.postValue(Resource.Loading())
         viewModelScope.launch {
             val response = retrofitApi.getCardByBin(bin)
             _currentCardRes.postValue(handleCardResponse(bin, response))

         }
     }

    private fun handleCardResponse(bin: String, response: Response<CardInfoRetrofit>): Resource<CardInfoRetrofit>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                viewModelScope.launch {
                    repositoryRoomImpl.addNewCard(response, bin)
                }
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun getCard(bin: String){
        viewModelScope.launch {
            _currentCard.value = repositoryRoomImpl.getCard(bin)
        }
    }
}

class CardInfoViewModelFactory(
    private val repository: CardRepositoryRoomImpl,
    private val retrofitApi: CardInfoApi
    ): ViewModelProvider.Factory{

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CardInfoViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CardInfoViewModel( repository, retrofitApi) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


package com.example.cardinfoapp

import android.icu.text.SimpleDateFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cardinfoapp.data.Converter
import com.example.cardinfoapp.databinding.ActivityMainBinding
import com.example.cardinfoapp.data.retrofit.CardInfoApi
import com.example.cardinfoapp.data.retrofit.models.CardInfoRetrofit
import com.example.cardinfoapp.data.room.CardDatabase
import com.example.cardinfoapp.data.room.CardItemRoom
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
//        var card: CardInfoRetrofit
//        var cardRoom: CardItemRoom
//        val sdf = SimpleDateFormat("dd-MM-yyyy")
//
//        val date = sdf.format(Date())
//
//        val db = CardDatabase.getDatabase(this)
//        val cardApi = retrofit.create(CardInfoApi::class.java)
//
//        binding.newRequestButtonRequestFragmentInfo.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                card = cardApi.getCardByBin("45717360")
//                runOnUiThread{
//                    binding.bankInfoFragmentRequestInfo.text = card.bank.name
//                    binding.emojiTextViewFragmentRequestInfo.text = card.country.emoji
//                    binding.bankNumberSiteInfoFragmentRequestInfo.text = date
//                }
//                cardRoom = Converter().cardInfoRetrofitToCardItemRoom(card, date)
//
//                coroutineScope {
//                    db.getDao().insertItem(cardRoom)
//                }
//            }
//        }
    }
}
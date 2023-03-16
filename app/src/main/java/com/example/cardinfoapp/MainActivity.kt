package com.example.cardinfoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cardinfoapp.databinding.ActivityMainBinding
import com.example.cardinfoapp.retrofit.CardInfoApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl(Constance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val cardApi = retrofit.create(CardInfoApi::class.java)

        binding.newRequestButtonRequestFragmentInfo.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val card = cardApi.getCardByBin("45717360")
                runOnUiThread{
                    binding.bankInfoFragmentRequestInfo.text = card.bank.name
                    binding.emojiTextViewFragmentRequestInfo.text = card.country.emoji
                }
            }
        }
    }
}
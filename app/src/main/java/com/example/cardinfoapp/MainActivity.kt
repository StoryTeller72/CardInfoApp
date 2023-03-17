package com.example.cardinfoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.cardinfoapp.databinding.ActivityMainBinding
import com.example.cardinfoapp.presentation.CardInfoViewModel
import com.example.cardinfoapp.presentation.CardInfoViewModelFactory


class MainActivity : AppCompatActivity() {

    private val viewModel: CardInfoViewModel by viewModels{
        CardInfoViewModelFactory(
            (application as CardInfoApplication).repositoryRoomImpl,
            (application as CardInfoApplication).cardApi
        )
    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.newRequestButtonRequestFragmentInfo.setOnClickListener {
            viewModel.makeResponse("45717360")
        }
    }
}

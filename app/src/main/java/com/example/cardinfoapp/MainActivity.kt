package com.example.cardinfoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.cardinfoapp.databinding.ActivityMainBinding
import com.example.cardinfoapp.presentation.CardInfoViewModel
import com.example.cardinfoapp.presentation.CardInfoViewModelFactory


class MainActivity : AppCompatActivity() {

//    private val viewModel: CardInfoViewModel by viewModels{
//        CardInfoViewModelFactory(
//            (application as CardInfoApplication).repositoryRoomImpl,
//            (application as CardInfoApplication).cardApi
//        )
//    }

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container_mainActivity) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationMainActivity.setupWithNavController(navController)
    }
}

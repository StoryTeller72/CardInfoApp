package com.example.cardinfoapp.presentation.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.cardinfoapp.CardInfoApplication
import com.example.cardinfoapp.R
import com.example.cardinfoapp.databinding.FragmentCardInfoBinding
import com.example.cardinfoapp.databinding.FragmentHistoryBinding
import com.example.cardinfoapp.presentation.CardInfoViewModel
import com.example.cardinfoapp.presentation.CardInfoViewModelFactory


class CardInfoFragment : Fragment() {

    private val viewModel: CardInfoViewModel by activityViewModels {
        CardInfoViewModelFactory(
            (activity?.application as CardInfoApplication).repositoryRoomImpl,
            (activity?.application as CardInfoApplication).cardApi
        )
    }

    private val navigationArgs: CardInfoFragmentArgs by navArgs()

    private var _binding: FragmentCardInfoBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardInfoBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.id
        viewModel.getCard(id)
        updateInfo()
        binding.countryInfoTextViewFragmentRequestInfo.text = viewModel.currentCard.value?.bankName ?: "?"
    }

    private fun updateInfo(){
        viewModel.currentCard.observe(viewLifecycleOwner, Observer{
            binding.shemeNetworkInfoFragmentCardInfo.text = it.sheme
            binding.typeInfoFragmentCardInfo.text = it.type
            binding.brandInfoFragmentCardInfo.text = it.brand
            binding.prepaidInfoFragmentCardInfo.text = if(it.prepaid == true) "YES" else "NO"
            binding.lengthInfoCardInfoFragment.text = if(it.length != 0) it.length.toString() else "?"
            binding.luhnInfoCardInfoFragment.text = it.luhn.toString()
            binding.bankInfoFragmentCardInfo.text = it.bankName
            binding.bankWebSiteInfoFragmentCardInfo.text = it.url
            binding.bankNumbeInfoFragmentCardInfo.text = it.phone
            binding.BINTextViewFragmentCardInfo.text = it.bin
            binding.emojiTextViewFragmentCardInfo.text = it.emoji
            binding.countryInfoTextViewFragmentRequestInfo.text = it.countryName
            binding.latitudeInfoTextViewFragmentCardInfo.text =
                getString(R.string.latitude, it.latitude, it.longitude )
        }
        )
    }


}
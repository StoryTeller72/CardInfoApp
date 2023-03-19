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
import com.example.cardinfoapp.data.retrofit.models.CardInfoRetrofit
import com.example.cardinfoapp.databinding.FragmentCardInfoBinding
import com.example.cardinfoapp.presentation.CardInfoViewModel
import com.example.cardinfoapp.presentation.CardInfoViewModelFactory
import com.example.cardinfoapp.utils.Resource


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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bin = navigationArgs.bin
        val fromDatabase = navigationArgs.fromDataBase
        if (fromDatabase) {
            updateFromDb(bin)
        } else {
            updateFromNetwork(bin)
        }
    }

    private fun updateFromDb(bin: String) {
        viewModel.getCard(bin)
        viewModel.currentCard.observe(viewLifecycleOwner, Observer {
            binding.prepaidInfoFragmentCardInfo.text = if (it.prepaid == true) "YES" else "NO"
            binding.lengthInfoCardInfoFragment.text =
                if (it.length != 0) it.length.toString() else "?"
            binding.luhnInfoCardInfoFragment.text = it.luhn.toString()
            binding.bankInfoFragmentCardInfo.text = it.bankName
            binding.bankWebSiteInfoFragmentCardInfo.text = it.url
            binding.bankNumbeInfoFragmentCardInfo.text = it.phone
            binding.BINTextViewFragmentCardInfo.text = it.bin
            binding.emojiTextViewFragmentCardInfo.text = it.emoji
            binding.countryInfoTextViewFragmentRequestInfo.text = it.countryName
            binding.latitudeInfoTextViewFragmentCardInfo.text =
                getString(R.string.latitude, it.latitude, it.longitude)
            binding.brandInfoFragmentCardInfo.text = it.brand
            binding.shemeNetworkInfoFragmentCardInfo.text = it.sheme
            binding.typeInfoFragmentCardInfo.text = it.type
        }
        )
    }

    private fun updateFromNetwork(bin: String) {

        viewModel.currentCardRes.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    binding.BINTextViewFragmentCardInfo.text = bin
                    binding.prepaidInfoFragmentCardInfo.text =
                        if (response.data?.prepaid == true) "YES" else "NO"
                    binding.lengthInfoCardInfoFragment.text =
                        if (response.data?.number?.length != 0) response.data?.number?.length.toString() else "?"
                    binding.luhnInfoCardInfoFragment.text = response.data?.number?.luhn.toString()
                    binding.bankInfoFragmentCardInfo.text = response.data?.bank?.name
                    binding.bankWebSiteInfoFragmentCardInfo.text = response.data?.bank?.url
                    binding.bankNumbeInfoFragmentCardInfo.text = response.data?.bank?.phone
                    binding.emojiTextViewFragmentCardInfo.text = response.data?.country?.emoji
                    binding.countryInfoTextViewFragmentRequestInfo.text =
                        response.data?.country?.name
                    binding.latitudeInfoTextViewFragmentCardInfo.text =
                        getString(
                            R.string.latitude,
                            response.data?.country?.latitude,
                            response.data?.country?.longitude
                        )
                    binding.brandInfoFragmentCardInfo.text = response.data?.brand
                    binding.shemeNetworkInfoFragmentCardInfo.text = response.data?.scheme
                    binding.typeInfoFragmentCardInfo.text = response.data?.type

                }
                is Resource.Loading -> {
                    binding.BINTextViewFragmentCardInfo.text = "Информация загружается"
                }
                else -> {
                    binding.BINTextViewFragmentCardInfo.text = "Карта не найдена"
                    showUnknownCard()
                }
            }

        }
        )
    }

    private fun showUnknownCard() {
            binding.prepaidInfoFragmentCardInfo.text = "?"
            binding.lengthInfoCardInfoFragment.text = "?"
            binding.luhnInfoCardInfoFragment.text = "?"
            binding.bankInfoFragmentCardInfo.text = "?"
            binding.bankWebSiteInfoFragmentCardInfo.text = "?"
            binding.bankNumbeInfoFragmentCardInfo.text = "?"
            binding.emojiTextViewFragmentCardInfo.text = "?"
            binding.countryInfoTextViewFragmentRequestInfo.text = "?"
            binding.latitudeInfoTextViewFragmentCardInfo.text = "?"
            binding.brandInfoFragmentCardInfo.text = "?"
            binding.shemeNetworkInfoFragmentCardInfo.text = "?"
            binding.typeInfoFragmentCardInfo.text = "?"

    }


}


package com.example.cardinfoapp.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.cardinfoapp.CardInfoApplication
import com.example.cardinfoapp.R
import com.example.cardinfoapp.databinding.FragmentHistoryBinding
import com.example.cardinfoapp.presentation.CardAdapter
import com.example.cardinfoapp.presentation.CardInfoViewModel
import com.example.cardinfoapp.presentation.CardInfoViewModelFactory


class HistoryFragment : Fragment() {

    private val viewModel: CardInfoViewModel by activityViewModels {
        CardInfoViewModelFactory(
            (activity?.application as CardInfoApplication).repositoryRoomImpl,
            (activity?.application as CardInfoApplication).cardApi
        )
    }

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentHistoryBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CardAdapter(this, viewModel.allCard){
            val action = HistoryFragmentDirections.actionHistoryFragmentToCardInfoFragment(it.id?: 0)
            this.findNavController().navigate(action)
        }
        binding.recyclerViewHistoryFragment.adapter = adapter
    }

}
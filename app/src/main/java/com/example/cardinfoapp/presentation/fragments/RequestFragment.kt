package com.example.cardinfoapp.presentation.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.cardinfoapp.CardInfoApplication
import com.example.cardinfoapp.Constance
import com.example.cardinfoapp.R
import com.example.cardinfoapp.databinding.FragmentHistoryBinding
import com.example.cardinfoapp.databinding.FragmentRequestBinding
import com.example.cardinfoapp.presentation.CardInfoViewModel
import com.example.cardinfoapp.presentation.CardInfoViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RequestFragment : Fragment() {

    private val viewModel: CardInfoViewModel by activityViewModels {
        CardInfoViewModelFactory(
            (activity?.application as CardInfoApplication).repositoryRoomImpl,
            (activity?.application as CardInfoApplication).cardApi
        )
    }

    private var _binding: FragmentRequestBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRequestBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.requestButtonRequestFragment.setOnClickListener {
            val bin = binding.inputBindEditTextRequestFragment.text.toString()
            val navController = findNavController()
            if (bin.length in 6..8) {
                viewModel.makeResponse(bin)
                val action = RequestFragmentDirections.actionRequestFragmentToCardInfoFragment(
                    bin,
                    false
                )
                navController.navigate(action)
            }else{
                val dialog = AlertDialog.Builder(this.context)
                    .setMessage("Неверно введены данные")
                    .setNeutralButton("OK", null)
                    .create()
                dialog.show()
                val button = dialog.getButton(AlertDialog.BUTTON_NEUTRAL)
                button.setOnClickListener {
                    dialog.dismiss()
                }
            }
        }
    }

}
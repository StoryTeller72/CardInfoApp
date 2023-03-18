package com.example.cardinfoapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.cardinfoapp.R
import com.example.cardinfoapp.data.room.CardItemRoom
import com.example.cardinfoapp.databinding.CardItemRecyclerViewBinding

class CardAdapter(
    lifecycle: LifecycleOwner,
    private val data: LiveData< List<CardItemRoom>>,
    private val onItemClicked: (CardItemRoom) -> Unit,
): RecyclerView.Adapter<CardAdapter.CardHolder>() {

    init {
        data.observe(lifecycle, Observer{
            notifyDataSetChanged()
        })
    }

    class CardHolder(item: View): RecyclerView.ViewHolder(item){
        private val binding = CardItemRecyclerViewBinding.bind(item)
        fun bind (card: CardItemRoom) = with(binding){
            binItemRecyclerView.text = card.bin
            dateItemRecyclerView.text = card.dateRequest
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item_recycler_view, parent, false)
        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val current = data.value?.get(position)
        holder.itemView.setOnClickListener {
            if (current != null) {
                onItemClicked(current)
            }
        }
        if (current != null) {
            holder.bind(current)
        }
    }

    override fun getItemCount(): Int {
        return data.value?.size ?: 0
    }
}
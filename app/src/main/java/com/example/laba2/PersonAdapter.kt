package com.example.laba2

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.transform.RoundedCornersTransformation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.laba2.databinding.ItemTestBinding


class PersonAdapter(
    var listperson: List<String> = listOf()
): RecyclerView.Adapter<PersonAdapter.MyHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        val binding = ItemTestBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyHolder(binding)
    }

    override fun onBindViewHolder(
        holder: MyHolder,
        position: Int
    ) {
        val text = listperson[position]
        holder.bind(text)
    }
    override fun getItemCount(): Int {
        return listperson.size
    }
    inner class MyHolder internal constructor(
        private val binding: ItemTestBinding,
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(text: String) = binding.run{
            textView.text = text
        }
    }
}
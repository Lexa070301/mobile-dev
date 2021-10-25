package com.example.laba2

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.transform.RoundedCornersTransformation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.laba2.databinding.ItemTestBinding


class PersonAdapter(
    var listperson: List<Person>,
    private val clickCard: (Person) -> Unit,
    private val clickCardLike: (Person) -> Unit
): RecyclerView.Adapter<PersonAdapter.MyHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyHolder {
        val binding = ItemTestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding, clickCard, clickCardLike)
    }

    override fun onBindViewHolder(
        holder: MyHolder,
        position: Int
    ) {
        val person = listperson[position]
        holder.bind(person)
    }
    override fun getItemCount(): Int {
        return listperson.size
    }
    inner class MyHolder internal constructor(
        private val binding: ItemTestBinding,
        private val clickCard: (Person) -> Unit,
        private val clickCardLike: (Person) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) = binding.run{
            name.text = person.name
            sex.text = person.sex
            date.text = person.date
            text.text = person.text
            image.load(person.image){
                transformations(RoundedCornersTransformation(50f))
            }
            binding.card.setOnClickListener {
                clickCard.invoke(person)
            }
            binding.like.setOnClickListener {
                clickCardLike.invoke(person)
            }
        }
    }
}
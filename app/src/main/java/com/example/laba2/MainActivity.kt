package com.example.laba2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laba2.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {


    private val verticalLinearLayoutManager: LinearLayoutManager =
        LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = verticalLinearLayoutManager
        binding.recyclerView.adapter =
            PersonAdapter(PersonHolder.createCollectionItems(), ::showCardMessage, ::showLikeMessage)
    }

    private fun showLikeMessage(person: Person) {
        Snackbar.make(binding.root, "Нажат лайк:  " + person.name, 2000).show()
    }

    private fun showCardMessage(person: Person) {
        Snackbar.make(binding.root, "Нажата карточка: " + person.name, 2000).show()
    }
}
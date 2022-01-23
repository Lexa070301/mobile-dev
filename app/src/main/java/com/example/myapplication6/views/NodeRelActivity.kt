package com.example.myapplication6.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication6.R
import com.example.myapplication6.databinding.ActivityNodeRelationshipsBinding

class NodeRelActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_UPDATE_RELATIONSHIPS = 101
        const val NODE_ID = "ID"
    }

    private lateinit var binding: ActivityNodeRelationshipsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNodeRelationshipsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nodeId = intent.extras?.get(NODE_ID).toString().toInt()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    NodeRelFragment.newInstance(nodeId)
                )
                .commit()
        }
    }
}
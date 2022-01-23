package com.example.myapplication6.views

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication6.databinding.ActivityCreateNodeBinding

class AddNodeActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CREATE_NODE = 102
        const val VALUE = "value"
    }

    private lateinit var binding: ActivityCreateNodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateNodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addButton.setOnClickListener {
            if (!binding.nodeValueEditText.text.isBlank()) {
                Intent().apply {
                    putExtra(
                        VALUE,
                        binding.nodeValueEditText.text.toString()
                    )
                    setResult(
                        RESULT_OK,
                        this
                    )
                }
            }
            finish()
        }
    }

}
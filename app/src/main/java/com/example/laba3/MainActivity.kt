package com.example.laba3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.laba3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val dataModel: DataModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_place, fragment1.newInstance())
            .commit()

        binding.button1.setOnClickListener {
            openFragment(fragment1.newInstance(),"1")
        }
        binding.button2.setOnClickListener {
            openFragment(fragment2.newInstance(), "2")
        }
        binding.button3.setOnClickListener {
            openFragment(fragment3.newInstance(),"3")
        }
        binding.button4.setOnClickListener {
            openFragment(fragment4.newInstance(),"4")
        }
        binding.prev.setOnClickListener {
            supportFragmentManager.popBackStack()
        }
        binding.next.setOnClickListener {
            if (supportFragmentManager.fragments.last() is fragment1) {
                openFragment(fragment2.newInstance(), "2")
            }
            if (supportFragmentManager.fragments.last() is fragment2) {
                openFragment(fragment3.newInstance(), "3")
            }
            if (supportFragmentManager.fragments.last() is fragment3) {
                openFragment(fragment4.newInstance(), "4")
            }
        }
    }

    private fun openFragment(f: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_place, f)
            .addToBackStack(tag)
            .commit()
    }
}
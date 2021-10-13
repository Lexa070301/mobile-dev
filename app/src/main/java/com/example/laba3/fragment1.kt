package com.example.laba3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.laba3.databinding.FragmentFragment1Binding

class fragment1 : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentFragment1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragment1Binding.inflate(inflater)
        if(dataModel.firstNumber.value != null) {
            binding.input1.setText(dataModel.firstNumber.value.toString())
        }
        val mainActivity = (activity as MainActivity?)!!
        mainActivity.binding.button2.visibility = View.GONE
        mainActivity.binding.button3.visibility = View.GONE
        mainActivity.binding.button4.visibility = View.GONE
        mainActivity.binding.next.visibility = View.VISIBLE
        mainActivity.binding.prev.visibility = View.GONE
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dataModel.firstNumber.value = binding.input1.text.toString()
    }

    companion object {
        @JvmStatic
        fun newInstance() = fragment1()
    }
}
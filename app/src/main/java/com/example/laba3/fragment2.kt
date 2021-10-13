package com.example.laba3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.laba3.databinding.FragmentFragment2Binding

class fragment2 : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentFragment2Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragment2Binding.inflate(inflater)
        if(dataModel.secondNumber.value != null) {
            binding.input2.setText(dataModel.secondNumber.value.toString())
        }
        val mainActivity = (activity as MainActivity?)!!
        mainActivity.binding.button2.visibility = View.VISIBLE
        mainActivity.binding.button3.visibility = View.GONE
        mainActivity.binding.button4.visibility = View.GONE
        mainActivity.binding.next.visibility = View.VISIBLE
        mainActivity.binding.prev.visibility = View.VISIBLE
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dataModel.secondNumber.value = binding.input2.text.toString()
    }

    companion object {
        @JvmStatic
        fun newInstance() = fragment2()
    }
}
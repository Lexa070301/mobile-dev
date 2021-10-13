package com.example.laba3

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.laba3.databinding.FragmentFragment4Binding

class fragment4 : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    lateinit var binding: FragmentFragment4Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFragment4Binding.inflate(inflater)
        val mainActivity = (activity as MainActivity?)!!
        mainActivity.binding.button2.visibility = View.VISIBLE
        mainActivity.binding.button3.visibility = View.VISIBLE
        mainActivity.binding.button4.visibility = View.VISIBLE
        mainActivity.binding.next.visibility = View.GONE
        mainActivity.binding.prev.visibility = View.VISIBLE
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var result: Double = 0.0
        super.onViewCreated(view, savedInstanceState)
        if(dataModel.action.value.toString() == "+" && dataModel.firstNumber.value.toString() != "" && dataModel.secondNumber.value.toString() != "") {
            result = dataModel.firstNumber.value!!.toDouble() + dataModel.secondNumber.value!!.toDouble()
        }
        if(dataModel.action.value.toString() == "-" && dataModel.firstNumber.value.toString() != "" && dataModel.secondNumber.value.toString() != "") {
            result = dataModel.firstNumber.value!!.toDouble() - dataModel.secondNumber.value!!.toDouble()
        }
        if(dataModel.action.value.toString() == "*" && dataModel.firstNumber.value.toString() != "" && dataModel.secondNumber.value.toString() != "") {
            result = dataModel.firstNumber.value!!.toDouble() * dataModel.secondNumber.value!!.toDouble()
        }
        if(dataModel.action.value.toString() == "/" && dataModel.firstNumber.value.toString() != "" && dataModel.secondNumber.value.toString() != "") {
            result = dataModel.firstNumber.value!!.toDouble() / dataModel.secondNumber.value!!.toDouble()
        }
        binding.result.text = result.toString()
    }

    companion object {
        @JvmStatic
        fun newInstance() = fragment4()
    }
}
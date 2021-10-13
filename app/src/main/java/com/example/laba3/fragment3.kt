package com.example.laba3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.laba3.databinding.FragmentFragment3Binding

class fragment3 : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    private  lateinit var binding: FragmentFragment3Binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFragment3Binding.inflate(inflater)
        binding.plus.setOnClickListener {
            dataModel.action.value = "+"
            openFragment4()
        }
        binding.minus.setOnClickListener {
            dataModel.action.value = "-"
            openFragment4()
        }
        binding.multiplication.setOnClickListener {
            dataModel.action.value = "*"
            openFragment4()
        }
        binding.division.setOnClickListener {
            dataModel.action.value = "/"
            openFragment4()
        }
        val mainActivity = (activity as MainActivity?)!!
        mainActivity.binding.button2.visibility = View.VISIBLE
        mainActivity.binding.button3.visibility = View.VISIBLE
        mainActivity.binding.button4.visibility = View.GONE
        mainActivity.binding.next.visibility = View.VISIBLE
        mainActivity.binding.prev.visibility = View.VISIBLE
        return binding.root
    }

    private fun openFragment4() {
        var fr = fragmentManager?.beginTransaction()
        fr?.replace(R.id.fragment_place, fragment4())
        fr?.addToBackStack("4")
        fr?.commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = fragment3()
    }
}
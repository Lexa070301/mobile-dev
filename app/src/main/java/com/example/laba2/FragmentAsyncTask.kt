package com.example.laba2

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.laba2.databinding.FrameAsyncTaskBinding
import java.io.InterruptedIOException
import java.util.concurrent.TimeUnit


class FragmentAsyncTask : Fragment(), TaskCallbacks {
    private lateinit var binding: FrameAsyncTaskBinding

    // Добавленные переменные
    private var adapter: PersonAdapter = PersonAdapter()

    private var handler: Handler? = null
    private var callbacks: TaskCallbacks? = null
    private var myTask: MyAsyncTask? = null

    private var listItem: MutableList<String> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FrameAsyncTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    // Сохраняем стейт приложения при повороте
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        callbacks = this
        startTask()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // иницилизируем RecycleView
        setupRecycleView()
    }

    // Подключем recycleView и настраиваем его
    private fun setupRecycleView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }


    // Объявляем AsyncTask
    @SuppressLint("StaticFieldLeak")
    inner class MyAsyncTask : AsyncTask<Unit, Int, Unit>() {
        override fun onPreExecute() {
            callbacks?.onPreExecuted()
        }

        override fun doInBackground(vararg params: Unit?) {
            Log.d("Started", "I'm Started")
            try {
                for (i in 0..2) {
                    TimeUnit.SECONDS.sleep(1)
                    if (isCancelled) break
                }
            } catch (e: InterruptedIOException) {
                e.printStackTrace()
            }
        }

        override fun onPostExecute(result: Unit?) {
            callbacks?.let {
                for (i in 1..100) {
                    handler?.sendEmptyMessageDelayed(i, ((i - 1) * 2000).toLong())
                }
            }
        }
    }

    // Запускаем AsyncTask
    private fun startTask() {
        myTask = MyAsyncTask()
        val callback = Handler.Callback { msg ->
            callbacks?.onPostExecute("Message: " + msg.what.toString())
            false
        }

        handler = Handler(callback)
        myTask!!.execute()
    }

    override fun onPreExecuted() {
        Log.d("cancel", "executed")
    }

    override fun onCancelled() {
        Log.d("cancel", "cancel")
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onPostExecute(i: String) {
        listItem.add(i)
        adapter.listperson = listItem
        adapter.notifyDataSetChanged()

        Log.d("MESSAGE", i)
    }

    companion object {
        const val MyTag = "FRAGMENT_ASYNC"
    }
}
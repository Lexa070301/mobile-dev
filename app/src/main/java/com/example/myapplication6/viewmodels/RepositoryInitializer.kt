package com.example.myapplication6.viewmodels

import android.content.Context
import com.example.myapplication6.data.NodeDao
import com.example.myapplication6.data.NodeRepository
import com.example.myapplication6.data.NodeRoomDatabase

object RepositoryInitializer{

    private var nodeDao: NodeDao? = null
    private lateinit var nodeRepository: NodeRepository

    fun getRepository(context: Context): NodeRepository {
        if (nodeDao == null) {
            nodeDao = NodeRoomDatabase.getInstance(context)?.nodeDao()
            nodeRepository = NodeRepository(nodeDao!!)
        }
        return nodeRepository
    }
}
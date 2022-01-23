package com.example.myapplication6.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NodeDao {
    @Query("SELECT * FROM node")
    suspend fun getAllNodes(): List<Node>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNode(node: Node)

    @Query("UPDATE node SET nodes = :nodes WHERE id = :id")
    suspend fun updateNode(id: Int, nodes: List<Node>)
}
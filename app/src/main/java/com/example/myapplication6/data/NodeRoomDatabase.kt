package com.example.myapplication6.data

import android.content.Context
import androidx.room.*

@Database(entities = [Node::class], version = 1, exportSchema = false)
@TypeConverters(ListConverter::class)
abstract class NodeRoomDatabase : RoomDatabase() {
    abstract fun nodeDao(): NodeDao

    companion object {
        @Volatile
        var database: NodeRoomDatabase? = null

        fun getInstance(context: Context): NodeRoomDatabase? {
            if (database == null) {
                synchronized(this) {
                        val db = Room.databaseBuilder(
                            context.applicationContext,
                            NodeRoomDatabase::class.java,
                            "node_database"
                        ).fallbackToDestructiveMigration()
                            .build()
                    database = db
                    return db
                }
            }
            return database
        }
    }
}

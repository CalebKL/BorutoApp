package com.example.burutoapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.burutoapp.data.local.dao.HeroDao
import com.example.burutoapp.data.local.dao.HeroRemoteKeysDao
import com.example.burutoapp.data.remote.BorutoApi
import com.example.burutoapp.domain.models.Hero
import com.example.burutoapp.domain.models.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class HeroDatabase: RoomDatabase() {

    companion object{
        fun create(context: Context, useInMemory:Boolean):HeroDatabase{
            val databaseBuilder = if (useInMemory){
                Room.inMemoryDatabaseBuilder(context,HeroDatabase::class.java)
            }else{
                Room.databaseBuilder(context, HeroDatabase::class.java, "test_database.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeysDao(): HeroRemoteKeysDao
}
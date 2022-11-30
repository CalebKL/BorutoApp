package com.example.burutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.burutoapp.data.local.dao.HeroDao
import com.example.burutoapp.data.local.dao.HeroRemoteKeysDao
import com.example.burutoapp.domain.models.Hero
import com.example.burutoapp.domain.models.HeroRemoteKeys

@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverter::class)
abstract class HeroDatabase: RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeysDao(): HeroRemoteKeysDao
}
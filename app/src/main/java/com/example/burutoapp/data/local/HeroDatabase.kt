package com.example.burutoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.burutoapp.data.local.dao.HeroDao
import com.example.burutoapp.domain.models.Hero

@Database(entities = [Hero::class], version = 1)
abstract class HeroDatabase: RoomDatabase() {
    abstract fun heroDao(): HeroDao
}
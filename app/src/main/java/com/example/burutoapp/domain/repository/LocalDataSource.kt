package com.example.burutoapp.domain.repository

import com.example.burutoapp.domain.models.Hero

interface LocalDataSource {
    suspend fun getSelectedHero(heroId: Int): Hero
}
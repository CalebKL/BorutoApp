package com.example.burutoapp.domain.repository

import androidx.paging.PagingData
import com.example.burutoapp.domain.models.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllHeroes(): Flow<PagingData<Hero>>
    fun searchHeroes(): Flow<PagingData<Hero>>
}
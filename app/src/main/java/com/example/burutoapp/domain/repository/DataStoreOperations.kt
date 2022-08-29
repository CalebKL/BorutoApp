package com.example.burutoapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveOnBoardingState(completed:Boolean)
    suspend fun readOnBoardingState(): Flow<Boolean>
}
package com.example.burutoapp.data.repository

import com.example.burutoapp.domain.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val dataStoreOperations: DataStoreOperations
) {
    suspend fun saveOnBoardingState(completed:Boolean){
        dataStoreOperations.saveOnBoardingState(completed = completed)
    }
    suspend fun readOnBoardingState(): Flow<Boolean>{
        return dataStoreOperations.readOnBoardingState()
    }
}
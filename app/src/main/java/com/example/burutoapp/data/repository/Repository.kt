package com.example.burutoapp.data.repository

import androidx.paging.PagingData
import com.example.burutoapp.domain.models.Hero
import com.example.burutoapp.domain.repository.DataStoreOperations
import com.example.burutoapp.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
    private val dataStoreOperations: DataStoreOperations
) {
    fun  getAllHeroes(): Flow<PagingData<Hero>>{
        return remote.getAllHeroes()
    }
    suspend fun saveOnBoardingState(completed:Boolean){
        dataStoreOperations.saveOnBoardingState(completed = completed)
    }
    fun readOnBoardingState(): Flow<Boolean>{
        return dataStoreOperations.readOnBoardingState()
    }
}
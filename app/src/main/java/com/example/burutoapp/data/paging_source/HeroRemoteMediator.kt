package com.example.burutoapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.burutoapp.data.local.HeroDatabase
import com.example.burutoapp.data.remote.BorutoApi
import com.example.burutoapp.domain.models.Hero
import javax.inject.Inject

@ExperimentalPagingApi
class HeroRemoteMediator @Inject constructor(
    private val borutoApi: BorutoApi,
    private val heroDatabase: HeroDatabase
): RemoteMediator<Int, Hero>(){
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        TODO("Not yet implemented")
    }
}
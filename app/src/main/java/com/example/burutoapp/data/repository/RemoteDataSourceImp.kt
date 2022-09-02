package com.example.burutoapp.data.repository

import androidx.paging.*
import com.example.burutoapp.data.local.HeroDatabase
import com.example.burutoapp.data.paging_source.HeroRemoteMediator
import com.example.burutoapp.data.remote.BorutoApi
import com.example.burutoapp.domain.models.Hero
import com.example.burutoapp.domain.repository.RemoteDataSource
import com.example.burutoapp.util.Constants.ITEM_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class RemoteDataSourceImp(
    private val heroDatabase: HeroDatabase,
    private val borutoApi: BorutoApi
): RemoteDataSource {
    private val heroDao = heroDatabase.heroDao()

    override fun getAllHeroes(): Flow<PagingData<Hero>> {
        val pagingSourceFactory = { heroDao.getAllHero() }
        return Pager(
            config = PagingConfig(pageSize = ITEM_PER_PAGE),
            remoteMediator = HeroRemoteMediator(
                borutoApi = borutoApi,
                heroDatabase = heroDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    override fun searchHeroes(): Flow<PagingData<Hero>> {
        TODO("Not yet implemented")
    }
}
package com.example.burutoapp.data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.burutoapp.data.local.HeroDatabase
import com.example.burutoapp.data.remote.BorutoApi
import com.example.burutoapp.domain.models.Hero
import com.example.burutoapp.domain.models.HeroRemoteKeys
import javax.inject.Inject

@ExperimentalPagingApi
class HeroRemoteMediator @Inject constructor(
    private val borutoApi: BorutoApi,
    private val heroDatabase: HeroDatabase
): RemoteMediator<Int, Hero>(){

    private val heroDao = heroDatabase.heroDao()
    private val heroRemoteKeysDao = heroDatabase.heroRemoteKeysDao()

    override suspend fun initialize(): InitializeAction {
        val currentTime = System.currentTimeMillis()
        val lastUpdated = heroRemoteKeysDao.getRemoteKeys(heroId = 1)?.lastUpdated?:0L
        val cacheTimeout = 1440

        val diffInMinutes = (currentTime -lastUpdated)/ 1000 / 60
        return if (diffInMinutes.toInt() <= cacheTimeout){
            InitializeAction.SKIP_INITIAL_REFRESH
        }else{
            InitializeAction.LAUNCH_INITIAL_REFRESH
        }

    }

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>)
    : MediatorResult {
        val page = when(loadType){
            LoadType.REFRESH ->{
                val remoteKeys = getRemoteKeysClosestToCurrentPosition(state)
                remoteKeys?.nextPage?.minus(1)?: 1
            }
            LoadType.PREPEND ->{
                val remoteKeys = getRemoteKeysForFirstItem(state)
                val prevPage = remoteKeys?.prevPage
                    ?:return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                prevPage
            }
            LoadType.APPEND ->{
                val remoteKeys = getRemoteKeysForLastTime(state)
                val nextPage = remoteKeys?.nextPage
                    ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                nextPage
            }
        }
        return try {
            val response = borutoApi.getAllHeroes(page = page)
            if (response.heroes.isNotEmpty()){
                heroDatabase.withTransaction {
                    if ( loadType== LoadType.REFRESH){
                        heroDao.deleteAllHero()
                        heroRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage
                    val keys = response.heroes.map {hero->
                        HeroRemoteKeys(
                            id = hero.id,
                            prevPage = prevPage,
                            nextPage = nextPage,
                            lastUpdated = response.lastUpdated
                        )
                    }
                    heroRemoteKeysDao.addAllRemoteKeys(heroRemoteKey = keys)
                    heroDao.addHeroes(heroes = response.heroes)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        }catch (e: Exception){
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeysForLastTime(state: PagingState<Int, Hero>
    ): HeroRemoteKeys?{
        return state.pages.lastOrNull{it.data.isNotEmpty()}?.data?.lastOrNull()?.let {hero ->
            heroRemoteKeysDao.getRemoteKeys(heroId = hero.id)
        }
    }

//    private fun parseMillis(millis:Long): String{
//        val date = Date(millis)
//        val format = SimpleDateFormat("yyyy.MM.dd.HH.mm", Locale.ROOT)
//        return format.format(date)
//    }

    private suspend fun getRemoteKeysForFirstItem(state: PagingState<Int, Hero>
    ): HeroRemoteKeys?{
        return state.pages.firstOrNull{ it.data.isNotEmpty() }?.data?.firstOrNull()?.let {hero ->
        heroRemoteKeysDao.getRemoteKeys(heroId = hero.id)
        }
    }

    private suspend fun getRemoteKeysClosestToCurrentPosition(state: PagingState<Int, Hero>
    ): HeroRemoteKeys?{
        return state.anchorPosition?.let { position->
            state.closestItemToPosition(anchorPosition = position)?.id?.let {id->
                heroRemoteKeysDao.getRemoteKeys(heroId = id)
            }
        }
    }
}
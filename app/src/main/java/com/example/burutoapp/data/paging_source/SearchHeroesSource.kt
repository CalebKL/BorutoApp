package com.example.burutoapp.data.paging_source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.burutoapp.domain.models.Hero

class SearchHeroesSource: PagingSource<Int, Hero>(){
    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? {

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        TODO("Not yet implemented")
    }
}
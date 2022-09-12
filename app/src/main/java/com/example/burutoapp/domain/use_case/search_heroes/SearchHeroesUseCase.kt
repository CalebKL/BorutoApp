package com.example.burutoapp.domain.use_case.search_heroes

import androidx.paging.PagingData
import com.example.burutoapp.data.repository.Repository
import com.example.burutoapp.domain.models.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(query:String): Flow<PagingData<Hero>>{
        return repository.searchHeroes(query = query)
    }
}
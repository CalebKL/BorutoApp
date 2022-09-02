package com.example.burutoapp.domain.use_case.get_all_heroes

import androidx.paging.PagingData
import com.example.burutoapp.data.repository.Repository
import com.example.burutoapp.domain.models.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<Hero>>{
        return repository.getAllHeroes()
    }
}
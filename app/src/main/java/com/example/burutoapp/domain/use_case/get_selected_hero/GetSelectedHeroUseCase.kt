package com.example.burutoapp.domain.use_case.get_selected_hero

import com.example.burutoapp.data.repository.Repository
import com.example.burutoapp.domain.models.Hero

class GetSelectedHeroUseCase(
    private  val repository: Repository
){
    suspend operator fun invoke(heroId:Int): Hero {
        return repository.getSelectedHero(heroId = heroId)
    }
}
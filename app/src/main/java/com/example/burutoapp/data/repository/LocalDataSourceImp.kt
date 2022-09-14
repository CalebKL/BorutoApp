package com.example.burutoapp.data.repository

import com.example.burutoapp.data.local.HeroDatabase
import com.example.burutoapp.domain.models.Hero
import com.example.burutoapp.domain.repository.LocalDataSource

class LocalDataSourceImp(
    heroDatabase: HeroDatabase
):LocalDataSource {

    private val heroDao= heroDatabase.heroDao()
    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId= heroId)
    }
}
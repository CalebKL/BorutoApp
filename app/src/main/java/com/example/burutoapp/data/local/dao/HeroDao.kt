package com.example.burutoapp.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.burutoapp.domain.models.Hero
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {
    @Query("SELECT * FROM HERO_TABLE ORDER BY id ASC")
    fun getAllHero(): PagingSource<Int, Hero>

    @Query("SELECT * FROM HERO_TABLE  WHERE id =:heroId")
    fun getSelectedHero(heroId: Int):Hero

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addHeroes(heroes: List<Hero>)

    @Query("DELETE FROM HERO_TABLE")
    suspend fun deleteAllHero()
}
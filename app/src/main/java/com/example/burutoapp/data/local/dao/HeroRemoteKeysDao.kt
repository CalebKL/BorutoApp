package com.example.burutoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.burutoapp.domain.models.HeroRemoteKeys

@Dao
interface HeroRemoteKeysDao {

    @Query("SELECT * FROM HERO_REMOTE_KEYS_TABLE WHERE id =:heroId")
    suspend fun getRemoteKeys(heroId:Int): HeroRemoteKeys?

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(heroRemoteKey: List<HeroRemoteKeys>)

    @Query("DELETE FROM HERO_REMOTE_KEYS_TABLE")
    suspend fun deleteAllRemoteKeys()
}
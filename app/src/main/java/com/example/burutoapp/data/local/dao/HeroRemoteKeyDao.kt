package com.example.burutoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.burutoapp.domain.models.HeroRemoteKey

@Dao
interface HeroRemoteKeyDao {

    @Query("SELECT * FROM HERO_REMOTE_KEY_TABLE WHERE id =:id ")
    suspend fun getRemoteKey(id:Int): HeroRemoteKey?

    @Insert(onConflict= OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(heroRemoteKey: List<HeroRemoteKey>)

    @Query("DELETE FROM HERO_REMOTE_KEY_TABLE")
    suspend fun deleteAllRemoteKeys()
}
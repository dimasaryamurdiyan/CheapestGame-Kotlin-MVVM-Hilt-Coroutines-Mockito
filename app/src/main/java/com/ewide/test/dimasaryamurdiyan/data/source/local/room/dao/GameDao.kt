package com.ewide.test.dimasaryamurdiyan.data.source.local.room.dao

import androidx.room.*
import com.ewide.test.dimasaryamurdiyan.data.source.local.entity.GameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Query("SELECT * FROM game")
    fun getAllGame(): Flow<List<GameEntity>>

    @Query("SELECT * FROM game WHERE isFavorite = 1")
    fun getFavoriteGame(): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGame(game: List<GameEntity>)

    @Update
    fun updateFavoriteGame(game: GameEntity)

    @Query("SELECT * FROM game ORDER BY external ASC")
    fun sortedASC(): Flow<MutableList<GameEntity>>

    @Query("SELECT * FROM game ORDER BY external DESC")
    fun sortedDESC(): Flow<MutableList<GameEntity>>

    @Query("SELECT * FROM game WHERE external LIKE '%' || :name || '%' ")
    fun searchGame(name: String): Flow<MutableList<GameEntity>>
}
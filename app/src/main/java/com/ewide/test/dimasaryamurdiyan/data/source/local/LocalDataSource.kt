package com.ewide.test.dimasaryamurdiyan.data.source.local

import com.ewide.test.dimasaryamurdiyan.data.source.local.entity.GameEntity
import com.ewide.test.dimasaryamurdiyan.data.source.local.room.dao.GameDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val gameDao: GameDao) {

    fun getAllGame(): Flow<List<GameEntity>> = gameDao.getAllGame()

    suspend fun insertGame(gameList: List<GameEntity>) = gameDao.insertGame(gameList)

    fun setFavoriteGame(game: GameEntity, newState: Boolean) {
        game.isFavorite = newState
        gameDao.updateFavoriteGame(game)
    }

    fun getFavoriteGame(): Flow<List<GameEntity>> = gameDao.getFavoriteGame()
}
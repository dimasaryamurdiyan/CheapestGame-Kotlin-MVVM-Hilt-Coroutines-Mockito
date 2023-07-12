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

}
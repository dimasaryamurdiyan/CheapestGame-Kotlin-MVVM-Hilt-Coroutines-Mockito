package com.ewide.test.dimasaryamurdiyan.domain.repository

import com.ewide.test.dimasaryamurdiyan.data.Resource
import com.ewide.test.dimasaryamurdiyan.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface IGameRepository {
    fun getAllGame(title: String): Flow<Resource<List<Game>>>
}
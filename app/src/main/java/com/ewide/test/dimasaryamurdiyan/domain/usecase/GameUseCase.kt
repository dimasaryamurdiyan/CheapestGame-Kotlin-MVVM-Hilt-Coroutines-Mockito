package com.ewide.test.dimasaryamurdiyan.domain.usecase

import com.ewide.test.dimasaryamurdiyan.data.Resource
import com.ewide.test.dimasaryamurdiyan.domain.model.Game
import kotlinx.coroutines.flow.Flow

interface GameUseCase {
    fun getAllGame(title: String): Flow<Resource<List<Game>>>
}
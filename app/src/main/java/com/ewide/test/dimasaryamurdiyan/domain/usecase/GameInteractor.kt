package com.ewide.test.dimasaryamurdiyan.domain.usecase

import com.ewide.test.dimasaryamurdiyan.data.GameRepository
import com.ewide.test.dimasaryamurdiyan.data.Resource
import com.ewide.test.dimasaryamurdiyan.domain.model.Game
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameInteractor @Inject constructor(private val gameRepository: GameRepository): GameUseCase {
    override fun getAllGame(title: String): Flow<Resource<List<Game>>> {
        return gameRepository.getAllGame(title)
    }
}
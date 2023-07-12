package com.ewide.test.dimasaryamurdiyan.domain.usecase

import com.ewide.test.dimasaryamurdiyan.data.GameRepository
import com.ewide.test.dimasaryamurdiyan.data.Resource
import com.ewide.test.dimasaryamurdiyan.domain.model.DetailGame
import com.ewide.test.dimasaryamurdiyan.domain.model.Game
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GameInteractor @Inject constructor(private val gameRepository: GameRepository): GameUseCase {
    override fun getAllGame(title: String): Flow<Resource<List<Game>>> {
        return gameRepository.getAllGame(title)
    }

    override fun getDetailGame(gameId: Int): Flow<Resource<DetailGame>> {
        return gameRepository.getDetailGame(gameId)
    }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        return gameRepository.setFavoriteGame(game, state)
    }
}
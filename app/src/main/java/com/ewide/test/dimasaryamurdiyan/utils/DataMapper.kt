package com.ewide.test.dimasaryamurdiyan.utils

import com.ewide.test.dimasaryamurdiyan.data.source.local.entity.GameEntity
import com.ewide.test.dimasaryamurdiyan.data.source.remote.response.GetListGamesResponse
import com.ewide.test.dimasaryamurdiyan.domain.model.Game

object DataMapper{
    fun mapResponsesToEntities(input: List<GetListGamesResponse.GetListGamesResponseItem>): List<GameEntity> {
        val gameList = ArrayList<GameEntity>()
        input.map {
            val game = GameEntity(
                gameID = it.gameID ?: "",
                steamAppID = it.steamAppID,
                cheapest = it.cheapest,
                cheapestDealID = it.cheapestDealID,
                external = it.external,
                internalName = it.internalName,
                thumb = it.thumb,
                isFavorite = false
            )
            gameList.add(game)
        }
        return gameList
    }

    fun mapEntitiesToDomain(input: List<GameEntity>): List<Game> =
        input.map {
            Game(
                gameID = it.gameID,
                steamAppID = it.steamAppID,
                cheapest = it.cheapest,
                cheapestDealID = it.cheapestDealID,
                external = it.external,
                internalName = it.internalName,
                thumb = it.thumb,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntyty(input: Game): GameEntity =
        GameEntity(
            gameID = input.gameID ?: "",
            steamAppID = input.steamAppID,
            cheapest = input.cheapest,
            cheapestDealID = input.cheapestDealID,
            external = input.external,
            internalName = input.internalName,
            thumb = input.thumb,
            isFavorite = input.isFavorite
        )
}
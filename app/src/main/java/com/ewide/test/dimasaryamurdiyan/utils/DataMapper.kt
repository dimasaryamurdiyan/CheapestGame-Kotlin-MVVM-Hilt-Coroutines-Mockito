package com.ewide.test.dimasaryamurdiyan.utils

import com.ewide.test.dimasaryamurdiyan.data.source.local.entity.GameEntity
import com.ewide.test.dimasaryamurdiyan.data.source.remote.response.GetDetailGameResponse
import com.ewide.test.dimasaryamurdiyan.data.source.remote.response.GetListGamesResponse
import com.ewide.test.dimasaryamurdiyan.domain.model.DetailGame
import com.ewide.test.dimasaryamurdiyan.domain.model.Game

object DataMapper{

    /*region getListGames*/
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

    fun mapDomainToEntity(input: Game): GameEntity =
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

    /*region get detail game*/
    fun mapResponsesToDomain(input: GetDetailGameResponse): DetailGame =
        DetailGame(
            info = DetailGame.Info(
                title = input.info?.title,
                steamAppID = input.info?.steamAppID,
                thumb = input.info?.thumb
            ),
            cheapestPriceEver = DetailGame.CheapestPriceEver(
                price = input.cheapestPriceEver?.price,
                date = input.cheapestPriceEver?.date
            ),
            deals = input.deals?.map {
                DetailGame.Deal(
                    storeID = it?.storeID,
                    dealID = it?.dealID,
                    price = it?.price,
                    retailPrice = it?.retailPrice,
                    savings = it?.savings
                )
            }
        )
}
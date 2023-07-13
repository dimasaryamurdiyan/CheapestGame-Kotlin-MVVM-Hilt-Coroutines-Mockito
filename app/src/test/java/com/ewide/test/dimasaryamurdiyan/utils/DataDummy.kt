package com.ewide.test.dimasaryamurdiyan.utils

import com.ewide.test.dimasaryamurdiyan.data.source.local.entity.GameEntity
import com.ewide.test.dimasaryamurdiyan.data.source.remote.response.GetDetailGameResponse
import com.ewide.test.dimasaryamurdiyan.data.source.remote.response.GetListGamesResponse
import com.ewide.test.dimasaryamurdiyan.domain.model.DetailGame
import com.ewide.test.dimasaryamurdiyan.domain.model.Game

object DataDummy {

    val gameModel = Game(
        gameID = "1",
        steamAppID = "a1213",
        cheapest = "4545",
        cheapestDealID = "asadad",
        internalName = "Game1",
        external = "Game 1",
        thumb = "afkagkakag",
        isFavorite = false
    )

    val gameEntity = GameEntity(
        gameID = "1",
        steamAppID = "a1213",
        cheapest = "4545",
        cheapestDealID = "asadad",
        internalName = "Game1",
        external = "Game 1",
        thumb = "afkagkakag",
        isFavorite = false
    )

    val localGames = listOf(
        Game(
            gameID = "1",
            steamAppID = "a1213",
            cheapest = "4545",
            cheapestDealID = "asadad",
            internalName = "Game1",
            external = "Game 1",
            thumb = "afkagkakag",
            isFavorite = false
        ), Game(
            gameID = "2",
            steamAppID = "a1213",
            cheapest = "4545",
            cheapestDealID = "asadad",
            internalName = "Game1",
            external = "Game 1",
            thumb = "afkagkakag",
            isFavorite = false
        )
    )

    val favoriteGamesEntity = listOf(
        GameEntity(
            gameID = "1",
            steamAppID = "a1213",
            cheapest = "4545",
            cheapestDealID = "asadad",
            internalName = "Game1",
            external = "Game 1",
            thumb = "afkagkakag",
            isFavorite = true
        ), GameEntity(
            gameID = "2",
            steamAppID = "a1213",
            cheapest = "4545",
            cheapestDealID = "asadad",
            internalName = "Game1",
            external = "Game 1",
            thumb = "afkagkakag",
            isFavorite = true
        )
    )

    val gamesEntity = mutableListOf(
        GameEntity(
            gameID = "1",
            steamAppID = "a1213",
            cheapest = "4545",
            cheapestDealID = "asadad",
            internalName = "Game1",
            external = "Game 1",
            thumb = "afkagkakag",
            isFavorite = false
        ), GameEntity(
            gameID = "2",
            steamAppID = "a1213",
            cheapest = "4545",
            cheapestDealID = "asadad",
            internalName = "Game1",
            external = "Game 1",
            thumb = "afkagkakag",
            isFavorite = true
        )
    )

    val gameResponse = mutableListOf(
        GetListGamesResponse.GetListGamesResponseItem(
            gameID = "1",
            steamAppID = "a1213",
            cheapest = "4545",
            cheapestDealID = "asadad",
            internalName = "Game1",
            external = "Game 1",
            thumb = "afkagkakag",
        ),
        GetListGamesResponse.GetListGamesResponseItem(
            gameID = "2",
            steamAppID = "a1213",
            cheapest = "4545",
            cheapestDealID = "asadad",
            internalName = "Game1",
            external = "Game 1",
            thumb = "afkagkakag",
        )
    )

    val detailGameResponse = GetDetailGameResponse(
        info = GetDetailGameResponse.Info(
            title = "BATMAN",
            steamAppID = "asdsd",
            thumb = "asdasdasd"
        ),
        cheapestPriceEver = GetDetailGameResponse.CheapestPriceEver(
            price = "456",
            date = 12312414
        ),
        deals = listOf(
            GetDetailGameResponse.Deal(
                storeID = "asasdada",
                dealID = "asadada",
                price = "45",
                retailPrice = "52",
                savings = "10.0"
            )
        )
    )

    val detailGameModel = DetailGame(
        info = DetailGame.Info(
            title = "BATMAN",
            steamAppID = "asdsd",
            thumb = "asdasdasd"
        ),
        cheapestPriceEver = DetailGame.CheapestPriceEver(
            price = "456",
            date = 12312414
        ),
        deals = listOf(
            DetailGame.Deal(
                storeID = "asasdada",
                dealID = "asadada",
                price = "45",
                retailPrice = "52",
                savings = "10.0"
            )
        )
    )


}
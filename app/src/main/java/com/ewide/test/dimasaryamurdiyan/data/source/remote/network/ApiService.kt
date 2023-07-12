package com.ewide.test.dimasaryamurdiyan.data.source.remote.network

import com.ewide.test.dimasaryamurdiyan.data.source.remote.response.GetDetailGameResponse
import com.ewide.test.dimasaryamurdiyan.data.source.remote.response.GetListGamesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("games")
    suspend fun getListGames(
        @Query("title") title: String
    ): GetListGamesResponse

    @GET("games")
    suspend fun getDetailGame(
        @Query("id") id: Int
    ): GetDetailGameResponse
}
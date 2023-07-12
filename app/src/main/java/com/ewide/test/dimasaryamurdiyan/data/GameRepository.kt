package com.ewide.test.dimasaryamurdiyan.data

import com.ewide.test.dimasaryamurdiyan.data.source.local.LocalDataSource
import com.ewide.test.dimasaryamurdiyan.data.source.remote.RemoteDataSource
import com.ewide.test.dimasaryamurdiyan.data.source.remote.network.ApiResponse
import com.ewide.test.dimasaryamurdiyan.data.source.remote.response.GetListGamesResponse
import com.ewide.test.dimasaryamurdiyan.domain.model.Game
import com.ewide.test.dimasaryamurdiyan.domain.repository.IGameRepository
import com.ewide.test.dimasaryamurdiyan.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
): IGameRepository {
    override fun getAllGame(title: String): Flow<Resource<List<Game>>> =
        object : NetworkBoundResource<List<Game>, List<GetListGamesResponse.GetListGamesResponseItem>>() {
            override fun loadFromDB(): Flow<List<Game>> {
                return localDataSource.getAllGame().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Game>?): Boolean {
                return true
            }

            override suspend fun createCall(): Flow<ApiResponse<List<GetListGamesResponse.GetListGamesResponseItem>>> {
                return remoteDataSource.getListGames(title)
            }

            override suspend fun saveCallResult(data: List<GetListGamesResponse.GetListGamesResponseItem>) {
                val gameList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertGame(gameList)
            }
        }.asFlow()
}
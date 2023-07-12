package com.ewide.test.dimasaryamurdiyan.data

import com.ewide.test.dimasaryamurdiyan.data.source.local.LocalDataSource
import com.ewide.test.dimasaryamurdiyan.data.source.remote.RemoteDataSource
import com.ewide.test.dimasaryamurdiyan.data.source.remote.network.ApiResponse
import com.ewide.test.dimasaryamurdiyan.data.source.remote.response.GetListGamesResponse
import com.ewide.test.dimasaryamurdiyan.domain.model.DetailGame
import com.ewide.test.dimasaryamurdiyan.domain.model.Game
import com.ewide.test.dimasaryamurdiyan.domain.repository.IGameRepository
import com.ewide.test.dimasaryamurdiyan.utils.AppExecutors
import com.ewide.test.dimasaryamurdiyan.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
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

    override fun getDetailGame(gameId: Int): Flow<Resource<DetailGame>> =
        flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getDetailGame(gameId).first()) {
                is ApiResponse.Success -> {
                    emit(Resource.Success(DataMapper.mapResponsesToDomain(apiResponse.data)))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.errorMessage))
                }
                is ApiResponse.Empty -> {

                }
            }
        }

    override fun setFavoriteGame(game: Game, state: Boolean) {
        val gameEntity = DataMapper.mapDomainToEntity(game)
        appExecutors.diskIO().execute { localDataSource.setFavoriteGame(gameEntity, state) }
    }

    override fun getFavoriteGame(): Flow<List<Game>> {
        return localDataSource.getFavoriteGame().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getAllGameASC(): Flow<List<Game>> {
        return localDataSource.getAllGameSortedASC().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getAllGameDESC(): Flow<List<Game>> {
        return localDataSource.getAllGameSortedDESC().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun searchGame(title: String): Flow<List<Game>> {
        return localDataSource.searchGame(title).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

}
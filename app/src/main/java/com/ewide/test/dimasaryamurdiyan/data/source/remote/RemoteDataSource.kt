package com.ewide.test.dimasaryamurdiyan.data.source.remote

import android.util.Log
import com.ewide.test.dimasaryamurdiyan.data.source.remote.network.ApiResponse
import com.ewide.test.dimasaryamurdiyan.data.source.remote.network.ApiService
import com.ewide.test.dimasaryamurdiyan.data.source.remote.response.GetDetailGameResponse
import com.ewide.test.dimasaryamurdiyan.data.source.remote.response.GetListGamesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getListGames(title: String): Flow<ApiResponse<GetListGamesResponse>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.getListGames(title = title)
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailGame(gameId: Int): Flow<ApiResponse<GetDetailGameResponse>> {
        return flow {
            try {
                val response = apiService.getDetailGame(id = gameId)
                if (response.info != null) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            }catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
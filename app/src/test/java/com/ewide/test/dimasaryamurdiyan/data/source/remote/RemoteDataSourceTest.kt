package com.ewide.test.dimasaryamurdiyan.data.source.remote

import com.ewide.test.dimasaryamurdiyan.data.source.remote.network.ApiResponse
import com.ewide.test.dimasaryamurdiyan.data.source.remote.network.ApiService
import com.ewide.test.dimasaryamurdiyan.data.source.remote.response.GetListGamesResponse
import com.ewide.test.dimasaryamurdiyan.utils.Constants
import com.ewide.test.dimasaryamurdiyan.utils.DataDummy
import com.ewide.test.dimasaryamurdiyan.utils.enqueueResponse
import com.google.gson.GsonBuilder
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
@ExperimentalSerializationApi
class RemoteDataSourceTest {
    private lateinit var mockWebServer: MockWebServer

    lateinit var apiService: ApiService

    private lateinit var okHttpClient: OkHttpClient

    private lateinit var loggingInterceptor: HttpLoggingInterceptor

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup(){
        mockWebServer = MockWebServer()

        mockWebServer.start()

        loggingInterceptor = HttpLoggingInterceptor().apply{
            level = HttpLoggingInterceptor.Level.BODY
        }
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(Constants.BASE_URL))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(ApiService::class.java)

        remoteDataSource = RemoteDataSource(apiService)
    }


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getListGames() correctly given 200 response with non empty list`() {

        runBlocking {
            mockWebServer.enqueueResponse("get_games.json", 200, 3000)

            val title = "game"

            val actualResponse = apiService.getListGames(title)

            // Act
            val result = remoteDataSource.getListGames(title).toList().single()

            // Assert
            assertTrue(result is ApiResponse.Success)
            assertEquals(actualResponse, (result as ApiResponse.Success).data)
        }
    }

    @Test
    fun `getDetailGame() correctly given 200 response with non empty data`() {

        runBlocking {
            mockWebServer.enqueueResponse("get_detail_game.json", 200, 3000)

            val id = 612

            val actualResponse = apiService.getDetailGame(id)

            // Act
            val result = remoteDataSource.getDetailGame(id).toList().single()

            // Assert
            assertTrue(result is ApiResponse.Success)
            assertEquals(actualResponse, (result as ApiResponse.Success).data)
        }
    }

}
package com.ewide.test.dimasaryamurdiyan.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ewide.test.dimasaryamurdiyan.data.source.local.LocalDataSource
import com.ewide.test.dimasaryamurdiyan.data.source.remote.RemoteDataSource
import com.ewide.test.dimasaryamurdiyan.data.source.remote.network.ApiResponse
import com.ewide.test.dimasaryamurdiyan.utils.AppExecutors
import com.ewide.test.dimasaryamurdiyan.utils.DataDummy
import com.ewide.test.dimasaryamurdiyan.utils.DataMapper
import com.ewide.test.dimasaryamurdiyan.utils.rule.TestCoroutineRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executor

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GameRepositoryTest: TestCase() {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    @Mock
    private lateinit var appExecutors: AppExecutors

    private lateinit var gameRepository: GameRepository

    @Before
    public override fun setUp() {
        gameRepository = GameRepository(remoteDataSource, localDataSource, appExecutors)
    }

    @Test
    fun `getAllGame() should return data from localDataSource if available`() = runBlocking {
        //arrange
        val title = "bat"
        val gameList = DataDummy.localGames
        val expectedData = Resource.Success(gameList)


       `when`(localDataSource.getAllGame())
           .thenReturn(
               flowOf(gameList.map {
                   DataMapper.mapDomainToEntity(it)
               })
           )

        // act
        val result = gameRepository.getAllGame(title).toList().last()

        // result
        assertEquals(expectedData.data, result.data)
    }

    @Test
    fun `getDetailGame() should return success resource when remoteDataSource returns success response`() =
        runBlocking {
            // Arrange
            val gameId = 1
            val detailGameResponse = DataDummy.detailGameResponse
            val expectedData = Resource.Success(DataMapper.mapResponsesToDomain(detailGameResponse))

            `when`(remoteDataSource.getDetailGame(gameId)).thenReturn(
                flowOf(ApiResponse.Success(detailGameResponse))
            )

            // Act
            val result = gameRepository.getDetailGame(gameId).toList().last()

            // Assert
            assertEquals(expectedData.data, result.data)
        }

    @Test
    fun `getDetailGame() should return error resource when remoteDataSource returns error response`() =
        runBlocking {
            // Arrange
            val gameId = 1
            val errorMessage = "Error fetching game detail"
            val expectedData = Resource.Error<Nothing>(errorMessage)

            // Act
            val result = gameRepository.getDetailGame(gameId).first()

            // Assert
            assertEquals(expectedData.data, result.data)
        }

    @Test
    fun `setFavoriteGame() should update favorite state of the game in localDataSource`() = runBlocking {
        // Arrange
        val game = DataDummy.gameModel
        val gameEntity = DataMapper.mapDomainToEntity(game)
        val newState = true

        `when`(appExecutors.diskIO()).thenReturn(Executor { it.run() })

        // Act
        gameRepository.setFavoriteGame(game, newState)

        // Assert
        verify(localDataSource).setFavoriteGame(gameEntity, newState)
    }

    @Test
    fun `getFavoriteGame() should return favorite games from localDataSource`() = runBlocking {
        // Arrange
        val favoriteGameList = DataDummy.favoriteGamesEntity
        val expectedData = DataMapper.mapEntitiesToDomain(favoriteGameList)


        `when`(localDataSource.getFavoriteGame()).thenReturn(
            flowOf(favoriteGameList)
        )

        // Act
        val result = gameRepository.getFavoriteGame().first()

        // Assert
        assertEquals(expectedData, result)
    }

    @Test
    fun `getAllGameASC() should return games sorted in ascending order from localDataSource`() =
        runBlocking {
            // Arrange
            val gameList = DataDummy.favoriteGamesEntity
            val sortedGameList = gameList.sortedBy { it.external }
            val expectedData = DataMapper.mapEntitiesToDomain(sortedGameList)

            `when`(localDataSource.getAllGameSortedASC()).thenReturn(
                flowOf(gameList)
            )

            // Act
            val result = gameRepository.getAllGameASC().first()

            // Assert
            assertEquals(expectedData, result)
        }

    @Test
    fun `getAllGameDESC() should return games sorted in descending order from localDataSource`() =
        runBlocking {
            // Arrange
            val gameList = DataDummy.favoriteGamesEntity
            val sortedGameList = gameList.sortedByDescending { it.external }
            val expectedData = DataMapper.mapEntitiesToDomain(sortedGameList)

            `when`(localDataSource.getAllGameSortedDESC()).thenReturn(
                flowOf(gameList)
            )

            // Act
            val result = gameRepository.getAllGameDESC().first()

            // Assert
            assertEquals(expectedData, result)
        }

    @Test
    fun `searchGame() should return filtered games based on title from localDataSource`() = runBlocking {
        // Arrange
        val title = "game"
        val gameList = DataDummy.favoriteGamesEntity
        val filteredGameList = gameList.filter { it.external?.contains(title, ignoreCase = true) == true }
        val expectedData =DataMapper.mapEntitiesToDomain(filteredGameList)

        `when`(localDataSource.searchGame(title)).thenReturn(
            flowOf(filteredGameList)
        )

        // Act
        val result = gameRepository.searchGame(title).first()

        // Assert
        assertEquals(expectedData, result)
    }

}
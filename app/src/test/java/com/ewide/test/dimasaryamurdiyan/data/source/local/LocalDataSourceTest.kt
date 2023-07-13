package com.ewide.test.dimasaryamurdiyan.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ewide.test.dimasaryamurdiyan.data.source.local.entity.GameEntity
import com.ewide.test.dimasaryamurdiyan.data.source.local.room.dao.GameDao
import com.ewide.test.dimasaryamurdiyan.utils.DataDummy
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class LocalDataSourceTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var gameDao: GameDao

    @Before
    fun setUp() {
        localDataSource = LocalDataSource(gameDao)
    }

    @Test
    fun `getAllGame() should return data from gameDao`() = runBlocking {
        // Arrange
        val gameList = DataDummy.favoriteGamesEntity

        `when`(gameDao.getAllGame()).thenReturn(flowOf(gameList))

        // Act
        val result = localDataSource.getAllGame().toList().first()

        // Assert
        assertEquals(gameList, result)
    }

    @Test
    fun `insertGame() should call gameDao to insert gameList`() = runBlocking {
        // Arrange
        val gameList = DataDummy.favoriteGamesEntity

        // Act
        localDataSource.insertGame(gameList)

        // Assert
        verify(gameDao).insertGame(gameList)
    }

    @Test
    fun `setFavoriteGame() should update favorite state of the game in gameDao`() = runBlocking {
        // Arrange
        val game = DataDummy.gameEntity
        val newState = true

        // Act
        localDataSource.setFavoriteGame(game, newState)

        // Assert
        verify(gameDao).updateFavoriteGame(game.apply { isFavorite = newState })
    }

    @Test
    fun `getFavoriteGame() should return favorite games from gameDao`() = runBlocking {
        // Arrange
        val favoriteGameList = DataDummy.favoriteGamesEntity

        `when`(gameDao.getFavoriteGame()).thenReturn(flowOf(favoriteGameList))

        // Act
        val result = localDataSource.getFavoriteGame().toList().first()

        // Assert
        assertEquals(favoriteGameList, result)
    }

    @Test
    fun `getAllGameSortedASC() should return games sorted in ascending order from gameDao`() =
        runBlocking {
            // Arrange
            val gameList = DataDummy.gamesEntity
            val sortedGameList = gameList.sortedBy { it.external }

            `when`(gameDao.sortedASC()).thenReturn(flowOf(sortedGameList) as Flow<MutableList<GameEntity>>?)

            // Act
            val result = localDataSource.getAllGameSortedASC().toList().first()

            // Assert
            assertEquals(sortedGameList, result)
        }

    @Test
    fun `getAllGameSortedDESC() should return games sorted in descending order from gameDao`() =
        runBlocking {
            // Arrange
            val gameList = DataDummy.gamesEntity
            val sortedGameList = gameList.sortedByDescending { it.external }

            `when`(gameDao.sortedDESC()).thenReturn(flowOf(sortedGameList) as Flow<MutableList<GameEntity>>?)

            // Act
            val result = localDataSource.getAllGameSortedDESC().toList().first()

            // Assert
            assertEquals(sortedGameList, result)
        }

    @Test
    fun `searchGame() should return filtered games based on title from gameDao`() = runBlocking {
        // Arrange
        val title = "Game"
        val gameList = DataDummy.gamesEntity
        val filteredGameList = gameList.filter { it.external?.contains(title, ignoreCase = true) ?: true }

        `when`(gameDao.searchGame(title)).thenReturn(flowOf(filteredGameList) as Flow<MutableList<GameEntity>>?)

        // Act
        val result = localDataSource.searchGame(title).toList().first()

        // Assert
        assertEquals(filteredGameList, result)
    }
}

package com.ewide.test.dimasaryamurdiyan.ui.games

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ewide.test.dimasaryamurdiyan.data.Resource
import com.ewide.test.dimasaryamurdiyan.domain.model.Game
import com.ewide.test.dimasaryamurdiyan.domain.usecase.GameUseCase
import com.ewide.test.dimasaryamurdiyan.utils.DataDummy
import com.ewide.test.dimasaryamurdiyan.utils.rule.TestCoroutineRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GameViewModelTest: TestCase() {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var gameViewModel: GameViewModel

    @Mock
    private lateinit var gameUseCase: GameUseCase

    @Before
    public override fun setUp() {
        gameViewModel = GameViewModel(gameUseCase)
    }

    @Test
    fun `getAllGame() should populate gameList with data from gameUseCase`() =
        testCoroutineRule.runBlockingTest {
            // Arrange
            val title = "bat"
            val gameList = DataDummy.localGames
            val expectedResource = Resource.Success(gameList)

            `when`(gameUseCase.getAllGame(title)).thenReturn(flowOf(expectedResource))

            // Act
            gameViewModel.getAllGame(title)

            // Assert
            assertEquals(expectedResource, gameViewModel.gameList.value)
        }

    @Test
    fun `searchGame() should populate gameList with data from gameUseCase`() =
        testCoroutineRule.runBlockingTest {
            // Arrange
            val title = "bat"
            val gameList = DataDummy.localGames

            `when`(gameUseCase.searchGame(title)).thenReturn(flowOf(gameList))

            // Act
            gameViewModel.searchGame(title)

            // Assert
            gameViewModel.gameList.observeForever { }
            assertEquals(gameList, gameViewModel.gameList.value?.data)
        }

    @Test
    fun `sortedASC() should populate gameList with data from gameUseCase`() =
        testCoroutineRule.runBlockingTest {
            // Arrange
            val gameList = DataDummy.localGames

            `when`(gameUseCase.getAllGameASC()).thenReturn(flowOf(gameList))

            // Act
            gameViewModel.sortedASC()

            // Assert
            gameViewModel.gameList.observeForever { }
            assertEquals(gameList, gameViewModel.gameList.value?.data)
        }

    @Test
    fun `sortedDESC() should populate gameList with data from gameUseCase`() =
        testCoroutineRule.runBlockingTest {
            val gameList = DataDummy.localGames

            `when`(gameUseCase.getAllGameDESC()).thenReturn(flowOf(gameList))

            // Act
            gameViewModel.sortedDESC()

            // Assert
            gameViewModel.gameList.observeForever { }
            assertEquals(gameList, gameViewModel.gameList.value?.data)
        }
}
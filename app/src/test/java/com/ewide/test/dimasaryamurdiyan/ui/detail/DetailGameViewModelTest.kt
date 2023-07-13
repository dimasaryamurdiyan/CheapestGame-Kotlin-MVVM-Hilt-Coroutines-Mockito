package com.ewide.test.dimasaryamurdiyan.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ewide.test.dimasaryamurdiyan.data.Resource
import com.ewide.test.dimasaryamurdiyan.domain.usecase.GameUseCase
import com.ewide.test.dimasaryamurdiyan.utils.DataDummy
import com.ewide.test.dimasaryamurdiyan.utils.DataMapper
import com.ewide.test.dimasaryamurdiyan.utils.rule.TestCoroutineRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executor

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailGameViewModelTest: TestCase() {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var detailGameViewModel: DetailGameViewModel

    @Mock
    private lateinit var gameUseCase: GameUseCase

    @Before
    public override fun setUp() {
        detailGameViewModel = DetailGameViewModel(gameUseCase)
    }

    @Test
    fun `getDetailGame() should populate detailGame with data from gameUseCase`() =
        testCoroutineRule.runBlockingTest {
            // Arrange
            val id = 121
            val gameList = DataDummy.detailGameModel
            val expectedResource = Resource.Success(gameList)

            Mockito.`when`(gameUseCase.getDetailGame(id)).thenReturn(flowOf(expectedResource))

            // Act
            detailGameViewModel.getDetailGame(id)

            // Assert
            assertEquals(expectedResource, detailGameViewModel.detailGame.value)
        }

    @Test
    fun `setFavoriteGame() should update favorite state of the game from gameUseCase`() =
        testCoroutineRule.runBlockingTest {
            // Arrange
            val game = DataDummy.gameModel
            val gameEntity = DataMapper.mapDomainToEntity(game)
            val newState = true

            // Act
            detailGameViewModel.setFavoriteGame(game, newState)

            // Assert
            Mockito.verify(gameUseCase).setFavoriteGame(game, newState)
        }
}
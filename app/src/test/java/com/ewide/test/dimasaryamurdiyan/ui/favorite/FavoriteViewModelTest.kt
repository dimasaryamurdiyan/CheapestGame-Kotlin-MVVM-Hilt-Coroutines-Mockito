package com.ewide.test.dimasaryamurdiyan.ui.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
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
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest: TestCase(){
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var favoriteViewModel: FavoriteViewModel

    @Mock
    private lateinit var gameUseCase: GameUseCase

    @Before
    public override fun setUp() {
        favoriteViewModel = FavoriteViewModel(gameUseCase)
    }

    @Test
    fun `getFavoriteGame() should populate gameList with data from gameUseCase`() =
        testCoroutineRule.runBlockingTest {
            // Arrange
            val gameList = DataDummy.localGames

            Mockito.`when`(gameUseCase.getFavoriteGame()).thenReturn(
                flowOf(gameList)
            )

            // Act
            favoriteViewModel.getFavoriteGame()

            // Assert
            assertEquals(gameList, favoriteViewModel.gameList.value)
        }
}
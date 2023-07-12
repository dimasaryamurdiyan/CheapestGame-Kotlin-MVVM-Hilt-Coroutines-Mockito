package com.ewide.test.dimasaryamurdiyan.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewide.test.dimasaryamurdiyan.data.Resource
import com.ewide.test.dimasaryamurdiyan.domain.model.DetailGame
import com.ewide.test.dimasaryamurdiyan.domain.model.Game
import com.ewide.test.dimasaryamurdiyan.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailGameViewModel @Inject constructor(val gameUseCase: GameUseCase): ViewModel() {
    private val _detailGame = MutableLiveData<Resource<DetailGame>>()
    val detailGame: LiveData<Resource<DetailGame>> get() = _detailGame

    fun getDetailGame(gameId: Int) {
        viewModelScope.launch {
            gameUseCase.getDetailGame(gameId).collect{
                _detailGame.postValue(it)
            }
        }
    }

    fun setFavoriteGame(game: Game, newStatus:Boolean) =
        gameUseCase.setFavoriteGame(game, newStatus)
}
package com.ewide.test.dimasaryamurdiyan.ui.games

import androidx.lifecycle.*
import com.ewide.test.dimasaryamurdiyan.data.Resource
import com.ewide.test.dimasaryamurdiyan.domain.model.Game
import com.ewide.test.dimasaryamurdiyan.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(val gameUseCase: GameUseCase): ViewModel() {
    private val _gameList = MutableLiveData<Resource<List<Game>>>()
    val gameList: LiveData<Resource<List<Game>>> get() = _gameList

    fun searchGame(title: String) {
        viewModelScope.launch {
            gameUseCase.getAllGame(title).collect{
                _gameList.postValue(it)
            }
        }
    }
}
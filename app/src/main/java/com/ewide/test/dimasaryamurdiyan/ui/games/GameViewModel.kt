package com.ewide.test.dimasaryamurdiyan.ui.games

import androidx.lifecycle.*
import com.ewide.test.dimasaryamurdiyan.data.Resource
import com.ewide.test.dimasaryamurdiyan.domain.model.Game
import com.ewide.test.dimasaryamurdiyan.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(val gameUseCase: GameUseCase): ViewModel() {
    private val _gameList = MutableLiveData<Resource<List<Game>>>()
    val gameList: LiveData<Resource<List<Game>>> get() = _gameList

    init {
        getAllGame("bat")
    }

    private fun getAllGame(title: String) {
        viewModelScope.launch {
            gameUseCase.getAllGame(title).collect{
                _gameList.postValue(it)
            }
        }
    }

    fun searchGame(title: String) {
        viewModelScope.launch {
            gameUseCase.searchGame(title).collect{
                _gameList.postValue(Resource.Success(it))
            }
        }
    }

    fun sortedASC(){
        viewModelScope.launch {
            _gameList.postValue(Resource.Loading())
            gameUseCase.getAllGameASC()
                .catch { _gameList.postValue(Resource.Error(it.message.toString())) }
                .collect { _gameList.postValue(Resource.Success(it)) }
        }
    }

    fun sortedDESC(){
        viewModelScope.launch {
            _gameList.postValue(Resource.Loading())
            gameUseCase.getAllGameDESC()
                .catch { _gameList.postValue(Resource.Error(it.message.toString())) }
                .collect { _gameList.postValue(Resource.Success(it)) }
        }
    }
}
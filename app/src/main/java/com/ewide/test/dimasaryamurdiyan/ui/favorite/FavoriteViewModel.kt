package com.ewide.test.dimasaryamurdiyan.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ewide.test.dimasaryamurdiyan.domain.model.Game
import com.ewide.test.dimasaryamurdiyan.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(val gameUseCase: GameUseCase): ViewModel(){
    private val _gameList = MutableLiveData<List<Game>>()
    val gameList: LiveData<List<Game>> get() = _gameList

    fun getFavoriteGame() {
        viewModelScope.launch {
            gameUseCase.getFavoriteGame().collect{
                _gameList.postValue(it)
            }
        }
    }
}
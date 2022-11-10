package com.exfarnanda1945.rawgsubmission.ui.Find

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exfarnanda1945.rawgsubmission.model.GameResponse
import com.exfarnanda1945.rawgsubmission.network.config.ApiService
import com.exfarnanda1945.rawgsubmission.network.handler.ApiResponse
import com.exfarnanda1945.rawgsubmission.repository.GamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FindGameViewModel : ViewModel() {
    private val repo = GamesRepository(ApiService.GameService)
    private val responseLoading = ApiResponse.loading<GameResponse>()

    private var _querySearch = MutableLiveData("")

    val searchResult = MutableStateFlow(responseLoading)

    init {
        searchGame()
    }

    fun setSearchGame(search:String){
        _querySearch.value = search
        searchGame()
    }


     private fun searchGame() {
        searchResult.value = responseLoading
        viewModelScope.launch {
            repo.search(_querySearch.value!!).collect {
                searchResult.value = it
            }
        }
    }
}
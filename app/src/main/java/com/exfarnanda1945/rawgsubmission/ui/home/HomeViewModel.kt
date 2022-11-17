package com.exfarnanda1945.rawgsubmission.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exfarnanda1945.rawgsubmission.model.game_response.GameResponse
import com.exfarnanda1945.rawgsubmission.network.config.ApiService
import com.exfarnanda1945.rawgsubmission.network.handler.ApiResponse
import com.exfarnanda1945.rawgsubmission.repository.GamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel:ViewModel() {
    private val repo =GamesRepository(ApiService.GameService)
    private val responseLoading = ApiResponse.loading<GameResponse>()

    val gameListBanner= MutableStateFlow(responseLoading)
    val gameBestOfTheYear = MutableStateFlow(responseLoading)
    val gameLatestRelease = MutableStateFlow(responseLoading)

    init {
        getListBanner(7)
        getGameBestOfTheYear(10)
        getGameLatestRelease(10)
    }

    private fun getListBanner(pageSize:Int){
        gameListBanner.value = responseLoading
        viewModelScope.launch {
            repo.getList(pageSize).collect{gameListBanner.value = it}
        }
    }

   private fun getGameBestOfTheYear(pageSize:Int){
        gameBestOfTheYear.value = responseLoading
        viewModelScope.launch {
            repo.getGameBestOfTheYear(pageSize).collect{gameBestOfTheYear.value = it}
        }
    }

    private fun getGameLatestRelease(pageSize:Int){
        gameLatestRelease.value = responseLoading
        viewModelScope.launch {
            repo.getLatestGameRelease(pageSize).collect{gameLatestRelease.value = it}
        }
    }

}
package com.exfarnanda1945.rawgsubmission.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exfarnanda1945.rawgsubmission.model.game_detail_response.GameDetailResponse
import com.exfarnanda1945.rawgsubmission.model.game_screenshots.GameScreenshotsResponse
import com.exfarnanda1945.rawgsubmission.network.config.ApiService
import com.exfarnanda1945.rawgsubmission.network.handler.ApiResponse
import com.exfarnanda1945.rawgsubmission.repository.GameDetailRepository
import com.exfarnanda1945.rawgsubmission.repository.GameScreenshotsRepository
import kotlinx.coroutines.launch

class GameDetailsViewModel : ViewModel() {
    private val detailRepo = GameDetailRepository(ApiService.GameDetailService)
    private val screenshotsApi = GameScreenshotsRepository(ApiService.GameScreenshotsService)

    private var _detailGameResult =
        MutableLiveData<ApiResponse<GameDetailResponse>>(ApiResponse.loading())
    val detailGameResult: LiveData<ApiResponse<GameDetailResponse>> get() = _detailGameResult

    private var _screenshotsGame =
        MutableLiveData<ApiResponse<GameScreenshotsResponse>>(ApiResponse.loading())
    val screenshotsGame: LiveData<ApiResponse<GameScreenshotsResponse>> get() = _screenshotsGame


     fun getDetailResult(gameId: Int) {
        viewModelScope.launch {
            val result = detailRepo.getDetail(gameId)
            _detailGameResult.postValue(result)
        }
    }

     fun getScreenshots(gameId: Int) {
        viewModelScope.launch {
            val result = screenshotsApi.getScreenshots(gameId)
            _screenshotsGame.postValue(result)
        }
    }


}
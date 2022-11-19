package com.exfarnanda1945.rawgsubmission.ui.genre

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exfarnanda1945.rawgsubmission.model.base_game_count.BaseGameCountResponse
import com.exfarnanda1945.rawgsubmission.network.config.ApiService
import com.exfarnanda1945.rawgsubmission.network.handler.ApiResponse
import com.exfarnanda1945.rawgsubmission.repository.GenreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class GenreListViewModel : ViewModel() {
    private val repo = GenreRepository(ApiService.GenreService)
    private val responseLoading = ApiResponse.loading<BaseGameCountResponse>()

    val listResult = MutableStateFlow(responseLoading)

    init {
        genreList()
    }

    private fun genreList() {
        listResult.value = responseLoading
        viewModelScope.launch {
            repo.getList().collect {
                listResult.value = it
            }
        }
    }

}
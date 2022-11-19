package com.exfarnanda1945.rawgsubmission.ui.developer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exfarnanda1945.rawgsubmission.model.base_game_count.BaseGameCountResponse
import com.exfarnanda1945.rawgsubmission.network.config.ApiService
import com.exfarnanda1945.rawgsubmission.network.handler.ApiResponse
import com.exfarnanda1945.rawgsubmission.repository.DeveloperRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DeveloperListViewModel : ViewModel() {
    private val repo = DeveloperRepository(ApiService.DeveloperService)
    private val responseLoading = ApiResponse.loading<BaseGameCountResponse>()

    private var _querySearch = MutableLiveData("")
    val querySearch: LiveData<String> get() = _querySearch

    val listResult = MutableStateFlow(responseLoading)

    init {
        searchDeveloper()
    }

    fun setSearchDev(search:String){
        _querySearch.value = search
        searchDeveloper()
    }


    private fun searchDeveloper() {
        listResult.value = responseLoading
        viewModelScope.launch {
            repo.getList(10,_querySearch.value!!).collect {
                listResult.value = it
            }
        }
    }
}
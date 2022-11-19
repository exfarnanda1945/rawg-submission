package com.exfarnanda1945.rawgsubmission.ui.publisher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exfarnanda1945.rawgsubmission.model.base_game_count.BaseGameCountResponse
import com.exfarnanda1945.rawgsubmission.network.config.ApiService
import com.exfarnanda1945.rawgsubmission.network.handler.ApiResponse
import com.exfarnanda1945.rawgsubmission.repository.PublisherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class PublisherListViewModel:ViewModel() {
    private val repo = PublisherRepository(ApiService.PublisherService)
    private val responseLoading = ApiResponse.loading<BaseGameCountResponse>()

    private var _querySearch = MutableLiveData("")
    val querySearch: LiveData<String> get() = _querySearch

    val listResult = MutableStateFlow(responseLoading)

    init {
        searchPublisher()
    }

    fun setSearchPublisher(search:String){
        _querySearch.value = search
        searchPublisher()
    }


    private fun searchPublisher() {
        listResult.value = responseLoading
        viewModelScope.launch {
            repo.getList(10,_querySearch.value!!).collect {
                listResult.value = it
            }
        }
    }

}
package com.exfarnanda1945.rawgsubmission.model.base_game_count

import com.google.gson.annotations.SerializedName

data class BaseGameCountResponse(
    @field:SerializedName("results")
    val results: List<BaseGameCountResultsItem?>? = null
)
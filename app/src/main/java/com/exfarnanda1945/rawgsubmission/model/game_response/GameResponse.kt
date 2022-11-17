package com.exfarnanda1945.rawgsubmission.model.game_response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GameResponse(
	@field:SerializedName("results")
	val results: List<GameResponseResultsItem?>? = null
) : Parcelable
package com.exfarnanda1945.rawgsubmission.model.game_screenshots

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GameScreenshotsResponse(
	@field:SerializedName("results")
	val results: List<GameScreenshotsResultsItem?>? = null
) : Parcelable
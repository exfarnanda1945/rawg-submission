package com.exfarnanda1945.rawgsubmission.model.game_screenshots

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GameScreenshotsResultsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("is_deleted")
	val isDeleted: Boolean? = null,

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("height")
	val height: Int? = null
) : Parcelable
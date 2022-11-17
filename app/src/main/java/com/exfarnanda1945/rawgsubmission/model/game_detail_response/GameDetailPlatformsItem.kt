package com.exfarnanda1945.rawgsubmission.model.game_detail_response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GameDetailPlatformsItem(

	@field:SerializedName("requirements")
	val requirements: GameDetailRequirements? = null,

	@field:SerializedName("released_at")
	val releasedAt: String? = null,

	@field:SerializedName("platform")
	val platform: GameDetailPlatform? = null
) : Parcelable
package com.exfarnanda1945.rawgsubmission.model.game_detail_response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GameDetailParentPlatformsItem(

	@field:SerializedName("platform")
	val platform: GameDetailPlatform? = null
) : Parcelable
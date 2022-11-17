package com.exfarnanda1945.rawgsubmission.model.game_response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GameResponseResultsItem(
	@field:SerializedName("rating")
	val rating: Double? = 0.0,

	@field:SerializedName("name")
	val name:String? = null,

	@field:SerializedName("metacritic")
	val metacritic: Int? = 0,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("ratings_count")
	val ratingsCount: Int? = 0,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("released")
	val released: String? = null,

	@field:SerializedName("background_image")
	val backgroundImage: String? = null,

) : Parcelable
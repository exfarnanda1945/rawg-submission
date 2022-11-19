package com.exfarnanda1945.rawgsubmission.model.game_detail_response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GameDetailResponse(

	@field:SerializedName("website")
	val website: String? = null,

	@field:SerializedName("developers")
	val developers: List<GameDetailDevelopersItem?>? = null,

	@field:SerializedName("name_original")
	val nameOriginal: String? = null,

	@field:SerializedName("metacritic")
	val metacritic: Int? = 0,

	@field:SerializedName("rating")
	val rating: Double? = 0.0,

	@field:SerializedName("description_raw")
	val descriptionRaw: String? = null,

	@field:SerializedName("background_image_additional")
	val backgroundImageAdditional: String? = null,

	@field:SerializedName("platforms")
	val platforms: List<GameDetailPlatformsItem?>? = null,

	@field:SerializedName("background_image")
	val backgroundImage: String? = null,

	@field:SerializedName("genre")
	val genres: List<GameDetailGenresItem?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("publishers")
	val publishers: List<GameDetailPublishersItem?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("parent_platforms")
	val parentPlatforms: List<GameDetailParentPlatformsItem?>? = null,

	@field:SerializedName("slug")
	val slug: String? = null,

	@field:SerializedName("released")
	val released: String? = null
) : Parcelable
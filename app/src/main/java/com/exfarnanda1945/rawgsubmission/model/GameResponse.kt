package com.exfarnanda1945.rawgsubmission.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class GameResponse(
	@field:SerializedName("results")
	val results: List<ResultsItem?>? = null
) : Parcelable

@Parcelize
data class Platform(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null
) : Parcelable

@Parcelize
data class ShortScreenshotsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
) : Parcelable

@Parcelize
data class PlatformsItem(
	@field:SerializedName("requirements_en")
	val requirementsEn: RequirementsEn? = null,
	@field:SerializedName("platform")
	val platform: Platform? = null

) : Parcelable

@Parcelize
data class RequirementsEn(

	@field:SerializedName("minimum")
	val minimum: String? = null,

	@field:SerializedName("recommended")
	val recommended: String? = null
):Parcelable

@Parcelize
data class GenresItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null
) : Parcelable

@Parcelize
data class Store(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("slug")
	val slug: String? = null
) : Parcelable

@Parcelize
data class ParentPlatformsItem(

	@field:SerializedName("platform")
	val platform: Platform? = null
) : Parcelable

@Parcelize
data class StoresItem(

	@field:SerializedName("store")
	val store: Store? = null
) : Parcelable

@Parcelize
data class ResultsItem(

	@field:SerializedName("stores")
	val stores: List<StoresItem?>? = null,


	@field:SerializedName("rating")
	val rating: Double? = null,

	@field:SerializedName("metacritic")
	val metacritic: Int? = null,

	@field:SerializedName("short_screenshots")
	val shortScreenshots: List<ShortScreenshotsItem?>? = null,

	@field:SerializedName("platforms")
	val platforms: List<PlatformsItem?>? = null,

	@field:SerializedName("background_image")
	val backgroundImage: String? = null,

	@field:SerializedName("genres")
	val genres: List<GenresItem?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("parent_platforms")
	val parentPlatforms: List<ParentPlatformsItem?>? = null,

	@field:SerializedName("released")
	val released: String? = null
) : Parcelable

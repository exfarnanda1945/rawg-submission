package com.exfarnanda1945.rawgsubmission.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameResponseList(
    @field:SerializedName("results")
    val results: List<GameResponseResultsItem?>? = null
) : Parcelable

@Parcelize
data class GameResponsePlatform(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("slug")
    val slug: String? = null
) : Parcelable

@Parcelize
data class GameResponseShortScreenshotsItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("id")
    val id: Int? = null
) : Parcelable

@Parcelize
data class GameResponsePlatformsItem(
    @field:SerializedName("requirements_en")
    val requirementsEn: GameResponseRequirementsEn? = null,
    @field:SerializedName("platform")
    val platform: GameResponsePlatform? = null

) : Parcelable

@Parcelize
data class GameResponseRequirementsEn(

    @field:SerializedName("minimum")
    val minimum: String? = null,

    @field:SerializedName("recommended")
    val recommended: String? = null
) : Parcelable

@Parcelize
data class GameResponseGenresItem(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("slug")
    val slug: String? = null
) : Parcelable

@Parcelize
data class GameResponseStore(

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("slug")
    val slug: String? = null
) : Parcelable

@Parcelize
data class GameResponseParentPlatformsItem(

    @field:SerializedName("platform")
    val platform: GameResponsePlatform? = null
) : Parcelable

@Parcelize
data class GameResponseStoresItem(

    @field:SerializedName("store")
    val store: GameResponseStore? = null
) : Parcelable

@Parcelize
data class GameResponseResultsItem(

    @field:SerializedName("stores")
    val stores: List<GameResponseStoresItem?>? = null,


    @field:SerializedName("rating")
    val rating: Double? = 0.0,

    @field:SerializedName("metacritic")
    val metacritic: Int? = 0,

    @field:SerializedName("short_screenshots")
    val shortScreenshots: List<GameResponseShortScreenshotsItem?>? = null,

    @field:SerializedName("platforms")
    val platforms: List<GameResponsePlatformsItem?>? = null,

    @field:SerializedName("background_image")
    val backgroundImage: String? = "https://pertaniansehat.com/v01/wp-content/uploads/2015/08/default-placeholder.png",

    @field:SerializedName("genres")
    val genres: List<GameResponseGenresItem?>? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("parent_platforms")
    val parentPlatforms: List<GameResponseParentPlatformsItem?>? = null,

    @field:SerializedName("released")
    val released: String? = null
) : Parcelable

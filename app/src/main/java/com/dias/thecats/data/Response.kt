package com.dias.thecats.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatResponse(

	@field:SerializedName("Response")
	val response: List<Cat?>? = null,
) : Parcelable

@Parcelize
data class Cat(

	@field:SerializedName("width")
	val width: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("height")
	val height: Int? = null,
) : Parcelable

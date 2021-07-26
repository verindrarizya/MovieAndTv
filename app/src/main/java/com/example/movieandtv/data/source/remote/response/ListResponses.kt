package com.example.movieandtv.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListResponses<T>(

	@field:SerializedName("results")
	val results: List<T>?

)
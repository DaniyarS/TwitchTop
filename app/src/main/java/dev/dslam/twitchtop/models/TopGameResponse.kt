package dev.dslam.twitchtop.models

import com.google.gson.annotations.SerializedName

data class TopGameResponse(
    @SerializedName("data")
    val items: List<TopGame>
)

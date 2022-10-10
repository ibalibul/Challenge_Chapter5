package com.iqbal.challenge_chapter5.model


import com.google.gson.annotations.SerializedName

data class GetFilmResponseItem(
    @SerializedName("description")
    val description: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String
)
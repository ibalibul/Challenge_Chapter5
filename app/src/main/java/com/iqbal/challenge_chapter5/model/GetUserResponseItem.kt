package com.iqbal.challenge_chapter5.model


import com.google.gson.annotations.SerializedName

data class GetUserResponseItem(
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String
)
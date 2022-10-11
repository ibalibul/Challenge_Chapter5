package com.iqbal.challenge_chapter5.network

import com.iqbal.challenge_chapter5.model.DataFilm
import com.iqbal.challenge_chapter5.model.DataUser
import com.iqbal.challenge_chapter5.model.GetFilmResponseItem
import com.iqbal.challenge_chapter5.model.GetUserResponseItem
import retrofit2.Call
import retrofit2.http.*

interface APIinterface {

    @GET("film")
    fun getAllFilm() : Call<List<GetFilmResponseItem>>

    @POST("film")
    fun addDataFilm(@Body request : DataFilm) : Call<GetFilmResponseItem>

    @GET("user")
    fun getAllUser() : Call<List<GetUserResponseItem>>

    @POST("user")
    fun registerUser(@Body request : DataUser) : Call<GetUserResponseItem>

    @PUT("film/{id}")
    fun UpdateDataFilm(@Path("id")id : Int,@Body reques : DataUser) : Call<GetUserResponseItem>

    @DELETE("film/{id}")
    fun deletFilm(@Path("id")id: Int) : Call<GetFilmResponseItem>



}
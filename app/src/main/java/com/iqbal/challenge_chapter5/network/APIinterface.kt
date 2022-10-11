package com.iqbal.challenge_chapter5.network

import com.iqbal.challenge_chapter5.model.*
import retrofit2.Call
import retrofit2.http.*

interface APIinterface {

    @GET("film")
    fun getAllFilm() : Call<List<GetFilmResponseItem>>

    @POST("film")
    fun addDataFilm(@Body request : DataFilm) : Call<GetFilmResponseItem>

    @GET("user")
    fun getAllUser() : Call<List<GetResponseUserNewItem>>

    @POST("user")
    fun registerUser(@Body request : DataUser) : Call<GetResponseUserNewItem>

    @PUT("user/{id}")
    fun UpdateDataFilm(@Path("id")id : String,@Body reques : DataUser) : Call<GetResponseUserNewItem>

    @DELETE("film/{id}")
    fun deletFilm(@Path("id")id: Int) : Call<GetFilmResponseItem>



}
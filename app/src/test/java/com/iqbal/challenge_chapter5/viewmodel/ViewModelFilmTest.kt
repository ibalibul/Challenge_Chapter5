package com.iqbal.challenge_chapter5.viewmodel

import com.iqbal.challenge_chapter5.model.GetFilmResponseItem
import com.iqbal.challenge_chapter5.model.GetResponseUserNewItem
import com.iqbal.challenge_chapter5.network.APIinterface
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class ViewModelFilmTest {

    lateinit var service : APIinterface

    @Before
    fun setUp(){
        service = mockk()
    }

    @Test
    fun testcallApiFilm() : Unit = runBlocking{
        val responseDataFilm = mockk<Call<List<GetFilmResponseItem>>>()
        every {
            runBlocking {
                service.getAllFilm()
            }
        }returns responseDataFilm
        val result = service.getAllFilm()

        verify {
            runBlocking {
                service.getAllFilm()
            }
        }
        assertEquals(result, responseDataFilm)
    }
}
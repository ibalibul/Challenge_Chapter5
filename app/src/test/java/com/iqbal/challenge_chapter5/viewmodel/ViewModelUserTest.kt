package com.iqbal.challenge_chapter5.viewmodel

import com.iqbal.challenge_chapter5.model.DataUser
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

class ViewModelUserTest {

    lateinit var service : APIinterface

    @Before
    fun setUp(){
        service = mockk()
    }

    @Test
    fun testcallPostUser() : Unit = runBlocking{
        val responseDataUser =  mockk<Call<GetResponseUserNewItem>>()
        val dumy = DataUser("name","username","password")
        every {
            runBlocking {
                service.registerUser(dumy)
            }
        }returns responseDataUser
        val result = service.registerUser(dumy)

        verify {
            runBlocking {
                service.registerUser(dumy)
            }
        }
        assertEquals(result, responseDataUser)
    }
}
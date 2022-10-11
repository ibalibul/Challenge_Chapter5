package com.iqbal.challenge_chapter5.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iqbal.challenge_chapter5.model.DataUser
import com.iqbal.challenge_chapter5.model.GetUserResponseItem
import com.iqbal.challenge_chapter5.network.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelUser : ViewModel(){

    lateinit var postLDUser : MutableLiveData<GetUserResponseItem?>
    lateinit var putLDuser : MutableLiveData<GetUserResponseItem>

    init {
        postLDUser = MutableLiveData()
        putLDuser = MutableLiveData()
    }

    fun postLiveDataUser() : MutableLiveData<GetUserResponseItem?> {
        return postLDUser
    }

    fun updateLiveData() : MutableLiveData<GetUserResponseItem>{
        return putLDuser
    }

    fun callPostApiUser(name : String, username : String, password : String ) {
        APIClient.instance.registerUser(
            DataUser(name, username))
            .enqueue(object : retrofit2.Callback<GetUserResponseItem>{
                override fun onResponse(
                    call: Call<GetUserResponseItem>,
                    response: Response<GetUserResponseItem>
                ) {
                    if (response.isSuccessful){
                        postLDUser.postValue(response.body())
                    }else{
                        Log.d("Error",response.message())
                        postLDUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<GetUserResponseItem>, t: Throwable) {
                    Log.d("Error",t.message.toString())
                    postLDUser.postValue(null)
                }

            })
    }

    fun   UpdateDataUser(id : Int, name : String,password : String){
        APIClient.instance.UpdateDataFilm(id,DataUser(name,password))
            .enqueue(object : Callback<GetUserResponseItem>{
                override fun onResponse(
                    call: Call<GetUserResponseItem>,
                    response: Response<GetUserResponseItem>
                ) {
                    if (response.isSuccessful){
                        updateLiveData().postValue(response.body())
                    }else{
                        updateLiveData().postValue(null)
                    }
                }

                override fun onFailure(call: Call<GetUserResponseItem>, t: Throwable) {
                    updateLiveData().postValue(null)
                }

            })
    }
}
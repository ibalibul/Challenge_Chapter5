package com.iqbal.challenge_chapter5.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iqbal.challenge_chapter5.model.DataUser
import com.iqbal.challenge_chapter5.model.GetResponseUserNewItem
import com.iqbal.challenge_chapter5.model.GetUserResponseItem
import com.iqbal.challenge_chapter5.network.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ViewModelUser : ViewModel(){

    lateinit var postLDUser : MutableLiveData<GetResponseUserNewItem?>
    lateinit var putLDuser : MutableLiveData<GetResponseUserNewItem>

    init {
        postLDUser = MutableLiveData()
        putLDuser = MutableLiveData()
    }

    fun postLiveDataUser() : MutableLiveData<GetResponseUserNewItem?> {
        return postLDUser
    }

    fun updateLiveData() : MutableLiveData<GetResponseUserNewItem>{
        return putLDuser
    }

    fun callPostApiUser(name : String, username : String, password : String ) {
        APIClient.instance.registerUser(DataUser(name, username,password))
            .enqueue(object : retrofit2.Callback<GetResponseUserNewItem>{
                override fun onResponse(
                    call: Call<GetResponseUserNewItem>,
                    response: Response<GetResponseUserNewItem>
                ) {
                    if (response.isSuccessful){
                        postLDUser.postValue(response.body())
                    }else{
                        Log.d("Error",response.message())
                        postLDUser.postValue(null)
                    }
                }

                override fun onFailure(call: Call<GetResponseUserNewItem>, t: Throwable) {
                    Log.d("Error",t.message.toString())
                    postLDUser.postValue(null)
                }

            })
    }

    fun   UpdateDataUser(id : String,  name : String,username : String, password : String){
        APIClient.instance.UpdateDataFilm(id,DataUser(name,username,password))
            .enqueue(object : Callback<GetResponseUserNewItem>{
                override fun onResponse(
                    call: Call<GetResponseUserNewItem>,
                    response: Response<GetResponseUserNewItem>
                ) {
                    if (response.isSuccessful){
                        updateLiveData().postValue(response.body())
                    }else{
                        updateLiveData().postValue(null)
                    }
                }

                override fun onFailure(call: Call<GetResponseUserNewItem>, t: Throwable) {
                    updateLiveData().postValue(null)
                }

            })
    }
}
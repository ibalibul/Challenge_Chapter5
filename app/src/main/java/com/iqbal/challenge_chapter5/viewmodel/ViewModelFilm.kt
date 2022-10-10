package com.iqbal.challenge_chapter5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.iqbal.challenge_chapter5.model.DataFilm
import com.iqbal.challenge_chapter5.model.GetFilmResponseItem
import com.iqbal.challenge_chapter5.network.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelFilm : ViewModel() {

    lateinit var liveDataFilm : MutableLiveData<List<GetFilmResponseItem>?>
    lateinit var liveDataPostFilm : MutableLiveData<GetFilmResponseItem?>
    lateinit var updateFilm : MutableLiveData<GetFilmResponseItem>
    lateinit var deletFilm : MutableLiveData<GetFilmResponseItem>

    init {
        liveDataFilm = MutableLiveData()
        liveDataPostFilm = MutableLiveData()
    }

    fun getDataFilm(): MutableLiveData<List<GetFilmResponseItem>?> {
        return liveDataFilm
    }

    fun PostLiveData() : MutableLiveData<GetFilmResponseItem?> {
        return liveDataPostFilm
    }



    fun callApiFilm() {
        APIClient.instance.getAllFilm()
            .enqueue(object : Callback<List<GetFilmResponseItem>>{
                override fun onResponse(
                    call: Call<List<GetFilmResponseItem>>,
                    response: Response<List<GetFilmResponseItem>>
                ) {
                    if (response.isSuccessful){
                        liveDataFilm.postValue(response.body())
                    }else {
                        liveDataFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<List<GetFilmResponseItem>>, t: Throwable) {
                    liveDataFilm.postValue(null)
                }

            })
    }

    fun callPostApiFilm(nama : String, category : String, directory : String, img :Int) {
        APIClient.instance.addDataFilm(DataFilm(nama,category,directory,img))
            .enqueue(object : Callback<GetFilmResponseItem>{
                override fun onResponse(
                    call: Call<GetFilmResponseItem>,
                    response: Response<GetFilmResponseItem>
                ) {
                    if (response.isSuccessful){
                        liveDataPostFilm.postValue(response.body())
                    }else{
                        liveDataPostFilm.postValue(null)
                    }
                }

                override fun onFailure(call: Call<GetFilmResponseItem>, t: Throwable) {
                    liveDataPostFilm.postValue(null)
                }

            })
    }

}

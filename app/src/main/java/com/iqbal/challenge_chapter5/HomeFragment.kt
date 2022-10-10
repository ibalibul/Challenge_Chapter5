package com.iqbal.challenge_chapter5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.iqbal.challenge_chapter5.adapter.FilmAdapter
import com.iqbal.challenge_chapter5.databinding.FragmentHomeBinding
import com.iqbal.challenge_chapter5.model.GetFilmResponseItem
import com.iqbal.challenge_chapter5.network.APIClient
import com.iqbal.challenge_chapter5.viewmodel.ViewModelFilm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showFilm()

    }

    fun showFilm () {
        val viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)
        viewModel.getDataFilm().observe(viewLifecycleOwner, Observer {
            if (it != null){
                binding.rvListfilm.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
                binding.rvListfilm.adapter = FilmAdapter(it)
            }
        })
        viewModel.callApiFilm()
    }


//        fun showFilm() {
//            APIClient.instance.getAllFilm()
//                .enqueue(object : Callback<List<GetFilmResponseItem>>{
//                    override fun onResponse(
//                        call: Call<List<GetFilmResponseItem>>,
//                        response: Response<List<GetFilmResponseItem>>
//                    ) {
//                        if (response.isSuccessful) {
//                            binding.rvListfilm.layoutManager =
//                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//                            binding.rvListfilm.adapter = FilmAdapter(response.body()!!)
//                        } else {
//                            Toast.makeText(context,"load data fild", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                    override fun onFailure(call: Call<List<GetFilmResponseItem>>, t: Throwable) {
//                        Toast.makeText(context,"Something Wrong", Toast.LENGTH_SHORT).show()
//                    }
//
//                })
//        }


}
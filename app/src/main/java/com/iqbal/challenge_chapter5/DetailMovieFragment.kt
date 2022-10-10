package com.iqbal.challenge_chapter5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.iqbal.challenge_chapter5.databinding.FragmentDetailMovieBinding
import com.iqbal.challenge_chapter5.model.GetFilmResponseItem

class DetailMovieFragment : Fragment() {

    lateinit var binding : FragmentDetailMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentDetailMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var datafilm = arguments?.getSerializable("datafilm") as GetFilmResponseItem
        binding.judulDetail.text = datafilm.name
        binding.DirectoryDetail.text = datafilm.director
        binding.categoryDetail.text = datafilm.description


    }

}
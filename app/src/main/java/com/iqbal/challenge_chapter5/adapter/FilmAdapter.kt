package com.iqbal.challenge_chapter5.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iqbal.challenge_chapter5.databinding.ItemFilmBinding
import com.iqbal.challenge_chapter5.model.GetFilmResponseItem

class FilmAdapter (var listFilm : List<GetFilmResponseItem>) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    class ViewHolder (var binding : ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.txtJudul.text = listFilm[position].name
        holder.binding.txtDirector.text = listFilm[position].director
        holder.binding.txtDeskripsi.text = listFilm[position].description
        Glide.with(holder.itemView).load(listFilm[position].image).into(holder.binding.imgFilm)

    }

    override fun getItemCount(): Int {
        return listFilm.size
    }
}
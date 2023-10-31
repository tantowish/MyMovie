package com.example.mymovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.databinding.ItemMovieBinding



class MovieAdapter(
    var movies: List<Movie>,
    private var onItemClickListener: ((Movie) -> Unit)? = null
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(val binding:ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binding.apply {
            image.setImageResource(movies[position].imageResourceId)
            tvTitle.text = movies[position].title
            tvStar.text = movies[position].rate.toString()
            root.setOnClickListener {
                onItemClickListener?.invoke(movies[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}
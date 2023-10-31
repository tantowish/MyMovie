package com.example.mymovie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovie.databinding.ItemGenreBinding

class GenreAdapter(
    var genres: List<String>,
    private var onItemClickListener: ((String) -> Unit)? = null
) : RecyclerView.Adapter<GenreAdapter.GenreViewHolder>() {

    inner class GenreViewHolder(val binding: ItemGenreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGenreBinding.inflate(layoutInflater, parent, false)
        return GenreViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.binding.apply {
            // Update the view elements to display genre information
            tvGenre.text = genres[position]

            // Set an onClick listener to handle item clicks
            root.setOnClickListener {
                onItemClickListener?.invoke(genres[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return genres.size
    }
}
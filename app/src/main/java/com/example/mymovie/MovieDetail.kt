package com.example.mymovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymovie.databinding.ActivityMovieDetailBinding

class MovieDetail : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movie = intent.getSerializableExtra("movieData") as Movie

        val adapter = GenreAdapter(movie.genres)

        with(binding){
            rvGenre.adapter = adapter
            tvBack.setOnClickListener{
                finish()
            }
            image.setImageResource(movie.imageResourceId)
            tvTitle.text = movie.title
            tvDesc.setText(movie.desc)
            tvRate.text = ""+movie.rate
            tvDirector.text = "Director : "+movie.director

                btnTicket.setOnClickListener {
                    val intent = Intent(this@MovieDetail, Payment::class.java)
                    intent.putExtra("movieData", movie)
                    startActivity(intent)
                }
        }
    }
}
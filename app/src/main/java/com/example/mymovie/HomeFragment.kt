package com.example.mymovie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymovie.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString("usn")

        val movieList = mutableListOf(
            Movie("Finding Nemo",R.drawable.nemo, R.string.desc, 4.9,"Andrew Stanton",listOf("Kids", "Animation")),
            Movie("Finding Dory",R.drawable.dory,R.string.desc, 4.8,"Andrew Stanton",listOf("Kids", "Animation")),
            Movie("Aladdin",R.drawable.aladdin,R.string.desc, 4.6,"Guy Ritchie",listOf("Kids", "Animation")),
            Movie("Toy Story",R.drawable.toystory,R.string.desc, 4.9,"John Lasseter, Tim Allen, Andrew Stanton",listOf("Kids", "Animation")),
            Movie("Toy Story 3",R.drawable.toystory3,R.string.desc, 4.9,"Lee Unkrich",listOf("Kids", "Animation")),
            Movie("Luca",R.drawable.luca, R.string.desc, 4.9,"Enrico Casarosa, Luca Guadagnino",listOf("Kids", "Animation")),
        )

        val adapter = MovieAdapter(movieList) { clickedMovie ->
            val intent = Intent(requireContext(), MovieDetail::class.java)
            intent.putExtra("movieData", clickedMovie)
            startActivity(intent)
        }

//        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext())

        binding.rvMovies.adapter = adapter

        binding.tvUsername.text = username
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

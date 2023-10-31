package com.example.mymovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.mymovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragment = HomeFragment()
        val bundle = Bundle()
        fragment.arguments = bundle
        replaceFragment(fragment)
        val username = intent.getStringExtra("usn")
        bundle.putString("usn", username)



        binding.bottomNavBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.navHome ->  replaceFragment(HomeFragment())
                R.id.navProfile ->  replaceFragment(ProfileFragment())
                else->{}
            }
            true
        }



    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val  fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}
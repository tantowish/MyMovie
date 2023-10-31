package com.example.mymovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymovie.databinding.ActivityOrderSummaryBinding
import java.text.SimpleDateFormat
import java.util.*

class OrderSummary : AppCompatActivity() {

    private lateinit var binding: ActivityOrderSummaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderSummaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getSerializableExtra("movieData") as Movie
        val bioskop = intent.getStringExtra("bioskop")
        val date = intent.getStringExtra("selectedDate")
        val inputFormat = SimpleDateFormat("yyyy-MMM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE, dd-MM-yyyy", Locale.getDefault())
        val formattedDate = outputFormat.format(inputFormat.parse(date))
        val orderNumber = intent.getStringExtra("orderNumber")
        val payment = intent.getStringExtra("payment")
        val paymentPick = intent.getStringExtra("paymentPick")
        val price = intent.getStringExtra("price")
        val seat = intent.getStringExtra("seat")




        with(binding){
            tvBack.setOnClickListener{
                finish()
            }
            image.setImageResource(movie.imageResourceId)
            tvTitle.text = movie.title
            tvBioskop.text = bioskop
            tvDate.text = formattedDate
            tvOrderNumber.text = orderNumber
            tvPayment.text = "$payment ($paymentPick)"
            tvPrice.text = price
            tvPay.text = tvPrice.text
            tvSeat.text = seat
            tvSeat2.text= seat+" Seat"
        }
    }
}
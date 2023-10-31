package com.example.mymovie


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.mymovie.databinding.ActivityPaymentBinding
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.*

class Payment : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentBinding
    private lateinit var bioskop: Array<String>
    private lateinit var seat: Array<String>
    val seatPrices = arrayOf(0,35000, 40000, 50000, 100000, 90000, 120000, 200000)
    private lateinit var payments: Array<String>

    private lateinit var adapterPaymentPick1: ArrayAdapter<String>
    private lateinit var adapterPaymentPick2: ArrayAdapter<String>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getSerializableExtra("movieData") as Movie

        bioskop = resources.getStringArray(R.array.bioskop)
        seat = resources.getStringArray(R.array.seats)
        payments = resources.getStringArray(R.array.payments)

        with(binding){
            tvBack.setOnClickListener{
                finish()
            }

            val adapterBioskop = ArrayAdapter(this@Payment, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, bioskop)
            spinnerBioskop.adapter = adapterBioskop
            val adapterSeat = ArrayAdapter(this@Payment, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, seat)
            spinnerSeat.adapter = adapterSeat
            val adapterPayment = ArrayAdapter(this@Payment, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, payments)
            spinnerPayment.adapter = adapterPayment

            spinnerSeat.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedSeatPrice = seatPrices[position]
                    tvPriceSet.text = "Rp$selectedSeatPrice"
                    tvTotPrice.text = tvPriceSet.text
                    tvSeatSet.text = spinnerSeat.selectedItem.toString() +" seat"
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    tvPriceSet.text ="Rp0-"
                    tvTotPrice.text = tvPriceSet.text
                }
            }


            adapterPaymentPick1 = ArrayAdapter(this@Payment, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.payment1))
            adapterPaymentPick2 = ArrayAdapter(this@Payment, com.google.android.material.R.layout.support_simple_spinner_dropdown_item, resources.getStringArray(R.array.payment2))

            spinnerPayment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    when (position) {
                        1 -> spinnerPaymentPick.adapter = adapterPaymentPick1
                        2 -> spinnerPaymentPick.adapter = adapterPaymentPick2
                    }
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }

            btnTanggal.setOnClickListener{
                val datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Select Date")
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()
                datePicker.show(supportFragmentManager, "datePicker")
                datePicker.addOnPositiveButtonClickListener {
                    val simpleDateFormat = SimpleDateFormat("yyyy-MMM-dd", Locale.getDefault())
                    tvTanggalSet.text=simpleDateFormat.format(Date(it).time)
                }
            }
            btnOrder.setOnClickListener {
                val selectedDate = tvTanggalSet.text.toString()
                val selectedAccNumber = accNumber.text.toString()
                val selectedPayment = spinnerPayment.selectedItem.toString()
                var selectedPaymentPick = spinnerPaymentPick.selectedItem
                if(selectedPaymentPick != null){
                    selectedPaymentPick = selectedPaymentPick.toString()
                }
                else{
                    selectedPaymentPick = "Select"
                }
                val selectedBioskop = spinnerBioskop.selectedItem.toString()
                val selectedSeat = spinnerSeat.selectedItem.toString()

                if (selectedDate.isNotBlank() && selectedDate != "Select" &&
                    selectedAccNumber.isNotBlank() &&
                    selectedPayment != "Select" &&
                    selectedPaymentPick != "Select" &&
                    selectedBioskop != "Select" &&
                    selectedSeat != "Select"
                ) {
                    val currentDate = SimpleDateFormat("ddMMyyyy", Locale.getDefault()).format(Date())
                    val random = (1..100).random()
                    val orderNumber = "$currentDate-$random"

                    val intent = Intent(this@Payment, OrderSummary::class.java)
                    intent.putExtra("movieData", movie)
                    intent.putExtra("selectedDate", selectedDate)
                    intent.putExtra("accNumber", selectedAccNumber)
                    intent.putExtra("payment", selectedPayment)
                    intent.putExtra("paymentPick", selectedPaymentPick)
                    intent.putExtra("bioskop", selectedBioskop)
                    intent.putExtra("seat", selectedSeat)
                    intent.putExtra("price", tvTotPrice.text)
                    intent.putExtra("orderNumber", orderNumber)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Payment, "Please input all the required information", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
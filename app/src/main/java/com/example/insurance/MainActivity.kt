package com.example.insurance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.insurance.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    //Step 1: Declare an instance of the View Binding Class
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Step 2: Initialize the binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        //Step 3: Assign the binding root to the UI
        setContentView(binding.root)
        binding.buttonCalculate.setOnClickListener{
            var basicPremium:Int = 0
            var extraMale:Int = 0
            var extraSmoker:Int = 0
            var total:Int = 0
            //Read input data
            val age = binding.spinnerAge.selectedItemPosition  //array
            if(age == 0){ //Less than 17
                //Calculate the basic premium
                basicPremium = 60
            }else if(age == 1){
                basicPremium = 70
            }else if(age == 2){
                basicPremium = 90
            }else if(age == 3){
                basicPremium = 120
            }else{
                basicPremium = 150
            }

            val gender = binding.radioGroupGender.checkedRadioButtonId
            if(gender == binding.radioButtonMale.id){
                //Calculate extra premium for sale
                if(age == 0){ //Less than 17
                    //Calculate the basic premium
                    extraMale = 0
                }else if(age == 1){
                    extraMale = 50
                }else if(age == 2){
                    extraMale = 100
                }else if(age == 3){
                    extraMale = 150
                }else{
                    extraMale = 200
                }
            }
            val smoker = binding.checkBoxSmoker.isChecked //boolean
            if(smoker){
                //Calculate extra premium for a smoker
                if(age == 0){ //Less than 17
                    extraSmoker = 0
                }else if(age == 1){
                    extraSmoker = 100
                }else if(age == 2){
                    extraSmoker = 150
                }else if(age == 3){
                    extraSmoker = 200
                }else if(age == 4){
                    extraSmoker = 250
                }else{
                    extraSmoker = 300
                }
            }
            total = basicPremium + extraMale + extraSmoker
            //Generate currency symbol
            val numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault())
            val currencySign = numberFormat.currency?.symbol
            binding.textViewBasicPremium.text = String.format("%s %d", currencySign,basicPremium)
            binding.textViewExtraMale.text = String.format("%s %d", currencySign,extraMale)
            binding.textViewExtraSmoker.text = String.format("%s %d", currencySign,extraSmoker)
            binding.textViewTotal.text = String.format("%s %d", currencySign,total)

        }
        binding.buttonReset.setOnClickListener{
            binding.spinnerAge.setSelection(0)
            binding.radioGroupGender.clearCheck()
            binding.checkBoxSmoker.isChecked = false
            binding.textViewBasicPremium.text = ""
            binding.textViewExtraMale.text = ""
            binding.textViewExtraSmoker.text = ""
            binding.textViewTotal.text = ""

        }
    }
}
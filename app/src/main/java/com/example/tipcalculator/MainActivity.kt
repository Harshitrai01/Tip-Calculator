package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // On clicking calculator button, this function will be called.
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }

    private fun calculateTip(){
        // Getting the cost through UI
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null) {
            binding.tipResult.text = ""
            return
        }

        // In calculateTip(), get the checkedRadioButtonId attribute of the tipOptions RadioGroup,
        // and assign it to a variable called selectedId.
        val selectedId = binding.tipOptions.checkedRadioButtonId

        // Selecting the percentage of the tip
        val tipPercentage = when (selectedId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        // In calculateTip() after the other code you've added,
        // multiply tipPercentage by cost, and assign it to a variable called tip.

        var tip = tipPercentage * cost

        // Note the use of var instead of val. This is because you may need to round up the
        // value if the user selected that option, so the value might change.

        // Checking for roundUpButton
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }

        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }

}

//// Old way with findViewById()
//val myButton: Button = findViewById(R.id.my_button)
//myButton.text = "A button"

//// Better way with view binding
//val myButton: Button = binding.myButton
//myButton.text = "A button"

// Best way with view binding and no extra variable
//binding.myButton.text = "A button"
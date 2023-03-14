



package com.zybooks.pizzaparty

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

//const val SLICES_PER_PIZZA = 8
private const val KEY_TOTAL_PIZZAS = "totalPizzas"
class MainActivity : AppCompatActivity() {

    private lateinit var numAttendEditText: EditText //This is for how many people are eating.
    private lateinit var numPizzasTextView: TextView //This is for how many pizzas are needed.
    private lateinit var howHungryRadioGroup: RadioGroup //This is for how hungry the people are.
    private var totalPizzas = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        numAttendEditText = findViewById(R.id.num_attend_edit_text)
        numPizzasTextView = findViewById(R.id.num_pizzas_text_view)
        howHungryRadioGroup = findViewById(R.id.hungry_radio_group)

        // Restore state
        if (savedInstanceState != null) {
            totalPizzas = savedInstanceState.getInt(KEY_TOTAL_PIZZAS)
            displayTotal()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_TOTAL_PIZZAS, totalPizzas)
    }
    /**
     * This function/method calculates the number of pizzas needed based on the number of people
     * attending the party as well as how hungry they are.
     */
    fun calculateClick(view: View) {

        // Get the text that was typed into the EditText
        val numAttendStr = numAttendEditText.text.toString()

        // Convert the text into an integer
        val numAttend = numAttendStr.toIntOrNull() ?: 0

        // Get hunger level selection
        val hungerLevel = when (howHungryRadioGroup.getCheckedRadioButtonId()) {
            R.id.light_radio_button -> PizzaCalculator.HungerLevel.LIGHT
            R.id.medium_radio_button -> PizzaCalculator.HungerLevel.MEDIUM
            else -> PizzaCalculator.HungerLevel.RAVENOUS
        }

        // Get the number of pizzas needed
        val calc = PizzaCalculator(numAttend, hungerLevel)
        totalPizzas = calc.totalPizzas
        displayTotal()

        // Place totalPizzas into the string resource and display
        //val totalText = getString(R.string.total_pizzas_num, totalPizzas)
        //numPizzasTextView.setText(totalText)
    }

    private fun displayTotal() {
        val totalText = getString(R.string.total_pizzas_num, totalPizzas)
        numPizzasTextView.text = totalText
    }
}
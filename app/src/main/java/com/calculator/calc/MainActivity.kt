package com.calculator.calc

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.calculator.calc.databinding.MainLayoutBinding
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {
    private val buffer = DisplayBuffer()
    private val engine = Engine()

    private lateinit var binding: MainLayoutBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

/*    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("calc_screen", binding.rsScreen.text.toString())
        outState.putString("calc_op_screen", binding.opScreen.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        binding.rsScreen.text = savedInstanceState.getString("calc_screen")
        binding.opScreen.text = savedInstanceState.getString("calc_op_screen")
    }*/

    fun operand(buttonClicked: View) {
        val inputDigit = when (buttonClicked.id) {
            R.id.button_one -> "1"
            R.id.button_two -> "2"
            R.id.button_three -> "3"
            R.id.button_four -> "4"
            R.id.button_five -> "5"
            R.id.button_six -> "6"
            R.id.button_seven -> "7"
            R.id.button_eight -> "8"
            R.id.button_nine -> "9"
            else -> "0"
        }
        binding.screen.text = buffer.addDigit(inputDigit)
    }

    fun operator(buttonClicked: View) {
        try {
            val operator = when (buttonClicked.id) {
                R.id.button_plus -> "+"
                R.id.button_minus -> "-"
                R.id.button_multiply -> "×"
                R.id.button_divide -> "÷"
                else -> throw IllegalArgumentException("Operator not found")
            }
            binding.screen.text = buffer.addOperator(operator)
        } catch (e: Exception) {
            binding.screen.text = "Error:3 ${e.message} "
        }
    }

    fun percentOperator(v: View) {
        try {
            binding.screen.text = engine.calculatePercentage(buffer.formula)
        } catch (e: Exception) {
            binding.screen.text = "Error:2 ${e.message}"
        }
    }

    fun resultIs(v: View) {
        try {
            binding.screen.text = engine.calculate(buffer.formula)
        } catch (e: Exception) {
            binding.screen.text = "ERROR:0 ${e.message}"
        }
        binding.opScreen.text = ""
    }

    fun clearScreen(v: View) {
        binding.screen.text = buffer.clear()
    }

    fun doBackspace(v: View) {
        binding.screen.text = buffer.backspace()
    }

    fun operatorMinusOrPlus(v: View) {
        try {
            binding.screen.text = engine.calculateNegation(buffer.formula)
        } catch (e: Exception) {
            binding.screen.text = "Error:1 ${e.message}"
        }
    }

    fun decimal(view: View) {
        binding.screen.text = buffer.addDecimal()
    }
}
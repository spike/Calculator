package com.calculator.calc

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.calculator.calc.databinding.MainLayoutBinding
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {
    private val displayBuffer = DisplayBuffer()
    private val engine = Engine()
    private lateinit var binding: MainLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainLayoutBinding.inflate(layoutInflater)

        setContentView(binding.root)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.activeScreen.text = displayBuffer.stack.toString()
        outState.putString("screen", displayBuffer.stack.toString())
        binding.previousScreen.text = displayBuffer.previous
        outState.putString("previous", displayBuffer.previous)
        outState.putBoolean("frozen", displayBuffer.frozen)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        displayBuffer.stack.refill(savedInstanceState.getString("screen").toString())
        binding.activeScreen.text = displayBuffer.stack.toString()
        displayBuffer.previous = savedInstanceState.getString("previous").toString()
        binding.previousScreen.text = displayBuffer.previous
        displayBuffer.frozen = savedInstanceState.getBoolean("frozen")
    }

    fun operand(buttonClicked: View) {
        if (displayBuffer.frozen) {
            displayBuffer.clear()
            binding.previousScreen.text = ""
        }
        val inputDigit = when (buttonClicked.id) {
            R.id.button_one -> '1'
            R.id.button_two -> '2'
            R.id.button_three -> '3'
            R.id.button_four -> '4'
            R.id.button_five -> '5'
            R.id.button_six -> '6'
            R.id.button_seven -> '7'
            R.id.button_eight -> '8'
            R.id.button_nine -> '9'
            else -> '0'
        }
        binding.activeScreen.text = displayBuffer.addDigit(inputDigit)
    }

    fun operator(buttonClicked: View) {
        displayBuffer.frozen = false
        try {
            val operator = when (buttonClicked.id) {
                R.id.button_plus -> "+"
                R.id.button_minus -> "-"
                R.id.button_multiply -> "×"
                R.id.button_divide -> "÷"
                else -> throw IllegalArgumentException("Operator not found")
            }
            val (previous, current) = displayBuffer.addOperator(operator)
            binding.previousScreen.text = previous
            binding.activeScreen.text = current
        } catch (e: Exception) {
            binding.activeScreen.text = "Error:3 ${e.message} "
        }
    }

    fun percentOperator(v: View) {
        try {
            displayBuffer.frozen = true
            val (previous, current) = engine.calculatePercentage(displayBuffer.stack.toString())
            displayBuffer.previous = previous
            displayBuffer.stack.refill(current)
            binding.activeScreen.text = displayBuffer.stack.toString()
            binding.previousScreen.text = displayBuffer.previous
        } catch (e: Exception) {
            binding.activeScreen.text = "Error:2 ${e.message}"
        }
    }

    fun resultIs(v: View) {
        try {
            displayBuffer.frozen = true
            val (previous, current) = engine.calculate(displayBuffer.stack.toString())
            displayBuffer.previous = previous
            displayBuffer.stack.refill(current)
            binding.previousScreen.text = displayBuffer.previous
            binding.activeScreen.text = displayBuffer.stack.toString()
        } catch (e: Exception) {
            binding.activeScreen.text = "ERROR:0 ${e.message}"
        }
    }

    fun clearScreen(v: View) {
        binding.activeScreen.text = displayBuffer.clear()
        binding.previousScreen.text = displayBuffer.previous
    }

    fun doBackspace(v: View) {
        val (previous, current) = displayBuffer.backspace()
        binding.activeScreen.text = current
        binding.previousScreen.text = previous
    }

    fun operatorMinusOrPlus(v: View) {
        try {
            displayBuffer.stack.refill(engine.calculateNegation(displayBuffer.stack.toString()))
            binding.activeScreen.text = displayBuffer.stack.toString()
        } catch (e: Exception) {
            binding.activeScreen.text = "Error:1 ${e.message}"
        }
    }

    fun decimal(v: View) {
        binding.activeScreen.text = displayBuffer.addDecimal()
    }
}
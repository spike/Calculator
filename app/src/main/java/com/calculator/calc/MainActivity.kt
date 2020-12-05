package com.calculator.calc

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.calculator.calc.databinding.MainLayoutBinding
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {
    private var operator: Char = ' '
    private var operandNumber: Char = ' '
    private val c = Calculation()
    private val display = Display()
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

    fun operand(v: View) {
        var buttonClicked = v
        operandNumber = when (buttonClicked.id) {
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
        binding.rsScreen.text = display.addCharacter(operandNumber.toString())
    }

    fun operator(v: View) {
        var buttonClicked = v
        try {
            operator = when (buttonClicked.id) {
                R.id.button_plus -> '+'
                R.id.button_minus -> '-'
                R.id.button_multiply -> '×'
                R.id.button_divide -> '÷'
                else -> throw IllegalArgumentException("Operator not found")
            }
            binding.rsScreen.text = display.addOperator(operator.toString())
        } catch (e: Exception) {
            binding.rsScreen.text = "Error:3 ${e.message} "
        }
    }

/*    fun percentOperator(v: View) {
        try {
            binding.rsScreen.text = engine.addPercentSign()
        } catch (e: Exception) {
            binding.rsScreen.text = "Error:2 ${e.message}"
        }
    }*/

    fun resultIs(v: View) {
        try {
            binding.rsScreen.text = engine.calculate()
        } catch (e: Exception) {
            binding.rsScreen.text = "ERROR:0 ${e.message}"
        }
        binding.opScreen.text = ""
    }

    fun clearScreen(v: View) {
        c.clear()
        binding.rsScreen.text = display.clear()
    }

    fun doBackspace(v: View) {
        binding.rsScreen.text = display.backspace()
    }

/*    fun operatorMinusOrPlus(v: View) {
        try {
            binding.rsScreen.text = engine.negate()
        } catch (e: Exception) {
            binding.rsScreen.text = "Error:1 ${e.message}"
        }
    }*/

    fun decimal(view: View) {
        binding.rsScreen.text = display.addDecimal()
    }
}
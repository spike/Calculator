package com.calculator.calc

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
// import kotlinx.android.synthetic.main.main_layout.*
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {
    var operator: Char = ' '
    var operandNumber: Char = ' '
    val c = Calculation()
    private lateinit var screen: TextView
    private lateinit var opScreen: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        actionBar?.hide()
        screen = findViewById<TextView>(R.id.calculator_screen)
        opScreen = findViewById<TextView>(R.id.calculator_subscreen_for_current_operator)
    }

/*    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString("calc_screen", screen.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val s = savedInstanceState.getString("calc_screen")
        screen.setText(s.toString())
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
        screen.setText(c.zeroThroughNine(operandNumber))
        opScreen.setText("")
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
            opScreen.setText(operator.toString())
            c.operatorSign(operator)
        } catch (e: Exception) {
            screen.setText("Error:3 ${e.message} ")
        }
    }

    fun percentOperator(v: View) {
        try {
            screen.setText(c.percentOperator())
        } catch (e: Exception) {
            screen.setText("Error:2 ${e.message}")
        }
    }

    fun resultIs(v: View) {
        try {
            screen.setText(c.equalSign())
        } catch (e: Exception) {
            screen.setText("ERROR:0 ${e.message}")
        }
        opScreen.setText("")
    }

    fun clearScreen(v: View) {
        c.clear()
        screen.setText(c.clear())
        opScreen.setText("")
    }

    fun doBackspace(v: View) {
        screen.setText(c.backSpace())
    }

    fun operatorMinusOrPlus(v: View) {
        try {
            screen.setText(c.makeNegative())
        } catch (e: Exception) {
            screen.setText("Error:1 ${e.message}")
        }
    }

    fun decimal(view: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        screen.setText(c.decimalMarker())
    }
}
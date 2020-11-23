package com.calculator.calc

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity() {
    var operator: Char = ' '
    var operandNumber: Char = ' '
    val c = Calculation()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun operand(v: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        val operationScreen = findViewById<TextView>(R.id.calculator_subscreen_for_current_operator)
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
        operationScreen.setText("")
    }

    fun operator(v: View) {
        val operationSubscreen = findViewById<TextView>(R.id.calculator_subscreen_for_current_operator)
        var buttonClicked = v
        val screen = findViewById<TextView>(R.id.calculator_screen)
        try {
            operator = when (buttonClicked.id) {
                R.id.button_plus -> '+'
                R.id.button_minus -> '-'
                R.id.button_multiply -> '×'
                R.id.button_divide -> '÷'
                else -> throw IllegalArgumentException("Operator not found")
            }
            operationSubscreen.setText(operator.toString())
            c.operatorSign(operator)
        } catch (e: Exception) {
            screen.setText("Error:3 ${e.message} ")
        }
    }

    fun percentOperator(v: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        try {
            screen.setText(c.percentOperator())
        } catch (e: Exception) {
            screen.setText("Error:2 ${e.message}")
        }
    }

    fun resultIs(v: View) {
        val currentOperationSubscreen = findViewById<TextView>(R.id.calculator_subscreen_for_current_operator)
        val screen = findViewById<TextView>(R.id.calculator_screen)
        try {
            screen.setText(c.equalSign())
        } catch (e: Exception) {
            screen.setText("ERROR:0 ${e.message}")
        }
        currentOperationSubscreen.setText("")

    }

    fun clearScreen(v: View) {
        val currentOperationSubscreen = findViewById<TextView>(R.id.calculator_subscreen_for_current_operator)
        val screen = findViewById<TextView>(R.id.calculator_screen)
        c.clear()
        screen.setText(c.clear())
        currentOperationSubscreen.setText("")
    }

    fun doBackspace(v: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        screen.setText(c.backSpace())
    }

    fun operatorMinusOrPlus(v: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        try {
            screen.setText(c.makeNegative())
        } catch (e: Exception) {
            screen.setText("Error:1 ${e.message}")
        }
    }

    fun decimal(view: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        screen.setText(c.decimalPoint())
    }
}
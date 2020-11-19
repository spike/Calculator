package com.calculator.calc

import android.content.Intent
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
        val operationScreen = findViewById<TextView>(R.id.calculator_screen2)
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
            R.id.button_zero -> '0'
            else -> '0'
        }
        screen.setText(c.zeroThroughNine(operandNumber))
        operationScreen.setText("")
    }

    fun operator(v: View) {
        val operationScreen = findViewById<TextView>(R.id.calculator_screen2)
        var buttonClicked = v
        val screen = findViewById<TextView>(R.id.calculator_screen)
        try {
            operator = when (buttonClicked.id) {
                R.id.button_plus -> '+'
                R.id.button_minus -> '-'
                R.id.button_multiply -> '×'
                R.id.button_divide -> '÷'
                R.id.button_percent -> {
                    percentOperator()
                    '%'
                }
                else -> throw IllegalArgumentException("Operator not found")
            }
            operationScreen.setText(operator.toString())
            c.operatorSign(operator)
        } catch (e: Exception) {
            screen.setText("Error: ${e.message} ")
        }
    }

    fun percentOperator() {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        screen.setText(c.percentOperator())
    }

    fun resultIs(v: View) {
        val operationScreen = findViewById<TextView>(R.id.calculator_screen2)
        val screen = findViewById<TextView>(R.id.calculator_screen)
        try {
            screen.setText(c.equalSign())
        } catch (e: Exception) {
            screen.setText("ERROR: ${e.message}")
        }
        operationScreen.setText("")

    }

    fun clearScreen(v: View) {
        val operationScreen = findViewById<TextView>(R.id.calculator_screen2)
        val screen = findViewById<TextView>(R.id.calculator_screen)
        c.clear()
        screen.setText("0")
        operationScreen.setText("")
    }

    fun doBackspace(v: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        screen.setText(c.backSpace())
    }

    fun operatorMinusOrPlus(v: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        screen.setText(c.makeNegative())

    }

    fun decimal(view: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        screen.setText(c.decimalPoint())
    }
}
package com.calculator.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

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
    }

    fun operator(v: View) {
        var buttonClicked = v
        operator = when (buttonClicked.id) {
            R.id.button_plus -> '+'
            R.id.button_minus -> '-'
            R.id.button_multiply -> '*'
            R.id.button_divide -> '/'
            else -> ' '
        }
        c.operatorSign(operator)
    }

    fun percentOperator(v: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        screen.setText(c.percentOperator())
    }

    fun resultIs(v: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        screen.setText(c.equalSign())
    }

    fun clearScreen(v: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        c.clear()
        screen.setText("0")
    }

    fun deleteOneCharacter(v: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        screen.setText(c.backSpace())
    }

    fun openPreferences(v: View) {
    }

    fun decimal(view: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        screen.setText(c.decimalPoint())

    }
}
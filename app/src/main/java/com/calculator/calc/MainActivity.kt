package com.calculator.calc

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var firstOperand = ""
    var secondOperand = ""
    var operator = ""
    var operandNumber = ""
    var isFirstOperand = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fun display() {

        }
    }

    fun operand(v: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        var buttonClicked = v
        operandNumber += when (buttonClicked.id) {
            R.id.button_one -> "1"
            R.id.button_two -> "2"
            R.id.button_three -> "3"
            R.id.button_four -> "4"
            R.id.button_five -> "5"
            R.id.button_six -> "6"
            R.id.button_seven -> "7"
            R.id.button_eight -> "8"
            R.id.button_nine -> "9"
            R.id.button_zero -> "0"
            else -> 0
        }
        if (isFirstOperand) {
            firstOperand = operandNumber
        } else {
            secondOperand = operandNumber
        }
       screen.setText(operandNumber)
        // change color of the screen or something
    }

    fun operator(v: View) {
        var buttonClicked = v
        operator = when (buttonClicked.id) {
            R.id.button_plus -> "+"
            R.id.button_minus -> "-"
            R.id.button_multiply -> "*"
            R.id.button_divide -> "/"
            else -> ""
        }
        isFirstOperand = false
        operandNumber = ""
    }

    fun resultIs(v: View) {
    }

    fun clearScreen(v: View) {
        var buttonClicked = v
        firstOperand = ""
        isFirstOperand = true
    }

    fun deleteOneCharacter(v: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        operandNumber = operandNumber.dropLast(1)
        screen.setText(operandNumber)
    }

    fun openPreferences(v: View) {
    }


}

class StackWithList {
    val elements: MutableList<Any> = mutableListOf()

    fun isEmpty() = elements.isEmpty()
    fun size() = elements.size

    fun push(item: Any) = elements.add(item)
    fun pop(): Any? {
        val item = elements.lastOrNull()
        if (!isEmpty()) {
            elements.removeAt(elements.size - 1)
        }
        return item
    }

    fun peek(): Any? = elements.lastOrNull()

    override fun toString(): String = elements.toString()

    fun contentToString(): String {
        var result = ""
        for (e in elements) {
            result += "" + e
        }
        return result
    }

    fun clear(): Boolean {
        elements.clear()
        return true
    }

    fun toCharArray() = elements.toString().toCharArray()

}


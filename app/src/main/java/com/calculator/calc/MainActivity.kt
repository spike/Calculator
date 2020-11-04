package com.calculator.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
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
            else -> "0"
        }
        if (isFirstOperand) {
            firstOperand = operandNumber
        } else {
            secondOperand = operandNumber
        }
        while ((operandNumber.length > 1) &&
            (operandNumber.first().equals('0')) &&
            (!operandNumber.last().equals('.'))) {
            operandNumber = operandNumber.drop(1)
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
        val screen = findViewById<TextView>(R.id.calculator_screen)
        val result = when(operator) {
            "+" -> firstOperand.toInt() + secondOperand.toInt()
            "-" -> firstOperand.toInt() - secondOperand.toInt()
            "*" -> firstOperand.toInt() * secondOperand.toInt()
            "/" -> firstOperand.toDouble() / secondOperand.toDouble()
            else -> 0
        }
        operandNumber = result.toString()
        firstOperand = result.toString()
        isFirstOperand = true
        screen.setText(result.toString())
    }

    fun clearScreen(v: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        firstOperand = ""
        isFirstOperand = true
        screen.setText("0")
    }

    fun deleteOneCharacter(v: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        operandNumber = operandNumber.dropLast(1)
        screen.setText(operandNumber)
    }

    fun openPreferences(v: View) {
    }

    fun decimal(view: View) {
        val screen = findViewById<TextView>(R.id.calculator_screen)
        operandNumber += "."
        screen.setText(operandNumber)

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


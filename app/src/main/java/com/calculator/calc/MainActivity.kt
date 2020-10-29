package com.calculator.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonClear = findViewById<Button>(R.id.button_clear)
        val buttonDivide = findViewById<Button>(R.id.button_divide)
        val buttonDelete = findViewById<Button>(R.id.button_delete)
        val buttonSeven = findViewById<Button>(R.id.button_seven)
        val buttonEight = findViewById<Button>(R.id.button_eight)
        val buttonNine = findViewById<Button>(R.id.button_nine)
        val buttonMinus = findViewById<Button>(R.id.button_minus)
        val buttonFour = findViewById<Button>(R.id.button_four)
        val buttonFive = findViewById<Button>(R.id.button_five)
        val buttonSix = findViewById<Button>(R.id.button_six)
        val buttonPlus = findViewById<Button>(R.id.button_plus)
        val buttonOne = findViewById<Button>(R.id.button_one)
        val buttonTwo = findViewById<Button>(R.id.button_two)
        val buttonThree = findViewById<Button>(R.id.button_three)
        val buttonParenthesis = findViewById<Button>(R.id.button_parenthesis)
        val buttonZero = findViewById<Button>(R.id.button_zero)
        val buttonPeriod = findViewById<Button>(R.id.button_period)
        val buttonPositiveNegative = findViewById<Button>(R.id.button_positive_or_negative)
        val buttonEqual = findViewById<Button>(R.id.button_equal)

        var stacklist  = StackWithList()


        fun keyTyped(c: Char): Boolean {
            Log.i("OnScreen", " ${stacklist.toString()}")
            return when (c) {
                'c' -> stacklist.push('c')
                'd' -> stacklist.push('d')
                '1' -> stacklist.push(1)
                '2' -> stacklist.push(2)
                '3' -> stacklist.push(3)
                '4' -> stacklist.push(4)
                '5' -> stacklist.push(5)
                '6' -> stacklist.push(6)
                '7' -> stacklist.push(7)
                '8' -> stacklist.push(8)
                '9' -> stacklist.push(9)
                '/' -> stacklist.push('/')
                '.' -> stacklist.push('.')
                '+' -> stacklist.push('+')
                '-' -> stacklist.push('-')
                'n' -> stacklist.push('n')
                '*' -> stacklist.push('*')
                '=' -> stacklist.push('=')
                else -> false
            }
        }
     buttonClear.setOnClickListener{
            keyTyped('C')
     }
        buttonDivide.setOnClickListener{
            keyTyped('/')
        }
        buttonDelete.setOnClickListener{
            keyTyped('d')
        }
        buttonSeven.setOnClickListener{
            keyTyped('7')
        }
        buttonEight.setOnClickListener{
            keyTyped('8')
        }
        buttonNine.setOnClickListener{
            keyTyped('9')
        }
        buttonMinus.setOnClickListener{
            keyTyped('-')
        }
        buttonFour.setOnClickListener{
            keyTyped('4')
        }
        buttonFive.setOnClickListener{
            keyTyped('5')
        }
        buttonSix.setOnClickListener{
            keyTyped('6')
        }
        buttonPlus.setOnClickListener{
            keyTyped('+')
        }
        buttonOne.setOnClickListener{
            keyTyped('1')
        }
        buttonTwo.setOnClickListener{
            keyTyped('2')
        }
        buttonThree.setOnClickListener{
            keyTyped('3')
        }
        buttonParenthesis.setOnClickListener{
            keyTyped('p')
        }
        buttonZero.setOnClickListener{
            keyTyped('0')
        }
        buttonPeriod.setOnClickListener{
            keyTyped('.')
        }
        buttonPositiveNegative.setOnClickListener{
            keyTyped('n')
        }
        buttonEqual.setOnClickListener{
            keyTyped('=')
        }

    }
}

class StackWithList {
    val elements: MutableList<Any> = mutableListOf()

    fun isEmpty() = elements.isEmpty()
    fun size() = elements.size

    fun push(item: Any) = elements.add(item)
    fun pop(): Any? {
        val item = elements.lastOrNull()
        if (!isEmpty()){
            elements.removeAt(elements.size -1)
        }
        return item
    }
    fun peek(): Any? = elements.lastOrNull()

    override fun toString(): String = elements.toString()
}
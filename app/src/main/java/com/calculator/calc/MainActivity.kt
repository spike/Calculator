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

        fun keyTyped(c: Char): Double {
            Log.i("Key Typed", " $c")
            return when (c) {
                'c' -> 0.0
                '1' -> 1.0
                '2' -> 2.0
                '3' -> 3.0
                '4' -> 4.0
                '5' -> 5.0
                '6' -> 6.0
                '7' -> 7.0
                '8' -> 8.0
                '9' -> 9.0
                '/' -> 0.0
                else -> 0.0
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

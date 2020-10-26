package com.calculator.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

     buttonClear.setOnClickListener{
     }
        buttonDivide.setOnClickListener{
        }
        buttonDelete.setOnClickListener{
        }
        buttonSeven.setOnClickListener{
        }
        buttonEight.setOnClickListener{
        }
        buttonNine.setOnClickListener{
        }
        buttonMinus.setOnClickListener{
        }
        buttonFour.setOnClickListener{
        }
        buttonFive.setOnClickListener{
        }
        buttonSix.setOnClickListener{
        }
        buttonPlus.setOnClickListener{
        }
        buttonOne.setOnClickListener{
        }
        buttonTwo.setOnClickListener{
        }
        buttonThree.setOnClickListener{
        }
        buttonParenthesis.setOnClickListener{
        }
        buttonZero.setOnClickListener{
        }
        buttonPeriod.setOnClickListener{
        }
        buttonPositiveNegative.setOnClickListener{
        }
        buttonEqual.setOnClickListener{
        }


    }
}

package com.calculator.calc

import java.lang.IllegalArgumentException
import java.math.BigDecimal

class Display {

    fun backspace(input: String): String {
        return if (input == "0")
            "0"
        else
            input.dropLast(1)
    }

    fun clear(input: String): String {
        return "0"
    }


}
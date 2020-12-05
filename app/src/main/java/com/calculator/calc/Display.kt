package com.calculator.calc

import java.lang.IllegalArgumentException
import java.math.BigDecimal

class Display {
    var screen: String = "0"

    fun backspace(input: String): String {
        screen = if (input == "0")
            "0"
        else
            input.dropLast(1)
        return screen
    }
    fun clear(input: String): String {
        screen = "0"
        return screen
    }
    fun addCharacter(input: String): String {
        screen = if (screen == "0" && input == "0")
            input
        else
            screen + input
        return screen
    }
    fun addOperator(input: String): String {
        screen = if (screen == "0" && input == "0")
            input
        else
            screen + input
        return screen
    }
    fun addDecimal(input: String): String {
        screen = if (screen == "0" && input == "0")
            input
        else
            screen + input
        return screen
    }

 /* I might not need this anymore
    fun removeTrailingZeros(bd: BigDecimal): String {
        return bd.toString().replace("[0]*$".toRegex(), "").replace("\\.$".toRegex(), "")
    } */


/* Let's wait and see for that one
    private fun removeZeroExponent(bd: String): String {
        if (bd.contains("^0E".toRegex()))
            return "0"
        else
            return bd
    } */

}
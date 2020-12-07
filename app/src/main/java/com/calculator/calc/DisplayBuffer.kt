package com.calculator.calc

class DisplayBuffer {
    var formula: String = "0"
    var previous: String = "0"

    fun backspace(): String {
        formula = if (formula == "0")
            "0"
        else
            formula.dropLast(1)
        return formula
    }
    fun clear(): String {
        formula = "0"
        previous = ""
        return formula
    }
    fun addDigit(input: String): String {
        val regex = """[\d|.]+$""".toRegex()
        val matchResult = regex.find(formula)
        formula = if (formula == "0")
            input
        else {
            if (input == "0" && matchResult!!.value == "0") {
                formula
            } else {
                formula + input
            }
        }
        return formula
    }
    fun addOperator(input: String): String {
        if (listOf('+','-','*', '×','/', '÷').contains(formula.last())) {
            formula = when(input) {
                "+", "*", "×", "/", "÷", "-" -> formula.dropLast(1) + input
                else -> formula + input
            }
        } else {
            formula += input
        }
        return formula
    }
    fun addDecimal(): String {
        val regex = """[\d|.]+$""".toRegex()
        val matchResult = regex.find(formula)
        formula = if (matchResult!!.value.contains(".")) {
                formula
            } else {
                formula + "."
            }
        return formula
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
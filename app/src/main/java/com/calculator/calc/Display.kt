package com.calculator.calc

class Display {
    var formula: String = "0"

    fun backspace(): String {
        formula = if (formula == "0")
            "0"
        else
            formula.dropLast(1)
        return formula
    }
    fun clear(): String {
        formula = "0"
        return formula
    }
    fun addCharacter(input: String): String {
        formula = if (formula == "0")
            input
        else
            formula + input
        return formula
    }
    fun addOperator(input: String): String {
        if (listOf('+','-','*','/').contains(formula.last())) {
            formula = when(input) {
                "+", "*", "/" -> formula.dropLast(1) + input
                // "-" ->
                else -> formula + input
            }
        } else {
            formula += input
        }
        return formula
    }
    fun addDecimal(): String {
        formula = if (formula == "0")
            "."
        else
            formula + "."
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
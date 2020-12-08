package com.calculator.calc

class DisplayBuffer {
    var formula: String = "0"
    var previous: String = "0"
    var frozen: Boolean = false

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
        frozen = false
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
}
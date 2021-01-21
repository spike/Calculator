package com.calculator.calc

import java.util.*

class DisplayBuffer {
    var formula: String = "0"
    var previous: String = "0"
    var frozen: Boolean = false
    var stack: StackBuffer = StackBuffer()


    fun backspace(): Pair<String, String> {
        previous = formula
        if (frozen) {  // what is frozen?
            formula = "0"
        }
        formula = if (formula == "0")
            "0"
        else
            formula.dropLast(1)
        if (formula == "") formula = "0"
        if (previous == "0") previous = ""
        return Pair(previous, formula)
    }
    fun clear(): String {
        stack.clear()
        formula = "0"
        previous = ""
        frozen = false  // again, what is frozen?
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
    fun addOperator(input: String): Pair<String, String> {
        val regex = """^([\d|\.]+[\D]+[\d|.]+)$""".toRegex()
        val matchResult = regex.find(formula)
        if (matchResult == null) {
        } else {
            val engine = Engine()
            val (prev, current) = engine.calculate(formula)
            formula = current + input
            previous = prev + input
            return Pair(previous, formula)
        }
        if (listOf('+','-','*', '×','/', '÷').contains(formula.last())) {
            formula = when(input) {
                "+", "*", "×", "/", "÷", "-" -> formula.dropLast(1) + input
                else -> formula + input
            }
        } else {
            formula += input
        }
        if (previous == "0") previous = ""
        return Pair(previous, formula)
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
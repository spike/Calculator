package com.calculator.calc

import java.util.*

class DisplayBuffer {
    var formula: String = "0"
    var previous: String = "0"
    var frozen: Boolean = false
    // I think frozen is when you press on an operator button
    // on older calculators. I should be able to remove it once
    // I'm done with my refactoring.
    var stack: StackBuffer = StackBuffer()


    fun backspace(): Pair<String, String> {
        previous = stack.toString()
        formula = stack.toString()
        if (frozen) {  // what is frozen?
            formula = "0"
            stack.refill("0")
        }
        if (formula == "0")
            formula = "0"
        else {
            stack.pop()
            formula = stack.toString()
        }
/*        if (formula == "") formula = "0" */
        if (previous == "0") previous = ""
        return Pair(previous, formula)
    }
    fun clear(): String {
        stack.refill("0")
        formula = stack.toString()
        previous = ""
        frozen = false
        return formula
    }
    fun addDigit(input: String): String {
        if (input[0] == '0') {
            if (!stack.isEmpty() && stack.peek() != '0') {
                stack.push(input[0])
            }
        } else {
            stack.push(input[0])
        }
        formula = stack.toString()
        return formula
    }
    fun addOperator(input: String): Pair<String, String> {
        val regex = """^([\d|\.]+[\D]+[\d|.]+)$""".toRegex()
        val matchResult = regex.find(stack.toString())
        if (matchResult == null) {
        } else {
            val engine = Engine()
            val (prev, current) = engine.calculate(stack.toString())
            formula = current + input
            previous = prev + input
            return Pair(previous, formula)
        }
        if (listOf('+','-','*', '×','/', '÷').contains(stack.peek())) {
            when(input) {
                "+", "*", "×", "/", "÷", "-" -> {
                    stack.pop()
                    stack.push(input[0])
                    formula = stack.toString()
                }
                else -> {
                    stack.push(input[0])
                    formula = stack.toString()
                }
            }
        } else {
            stack.push(input[0])
            formula = stack.toString()
        }
        if (previous == "0") previous = ""
        return Pair(previous, formula)
    }
    fun addDecimal(): String {
        val regex = """[\d|.]+$""".toRegex()
        val matchResult = regex.find(stack.toString())
        if (matchResult!!.value.contains(".")) {
                formula = stack.toString()
            } else {
                stack.push('.')
                formula = stack.toString()
            }
        return formula
    }
}
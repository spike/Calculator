package com.calculator.calc

import java.math.BigDecimal

class DisplayBuffer {
    private var formula: String = "0"
    var previous: String = "0"
    var frozen: Boolean = false
    var stack: DisplayStack = DisplayStack()
    var stackOfOperators: OperationStack = OperationStack()
    var stackOfNums: NumericalStack = NumericalStack()


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
        if (previous == "0") previous = ""
        return Pair(previous, formula)
    }
    fun clear(): String {
        stack.clear()
        formula = stack.toString()
        previous = ""
        frozen = false
        return formula
    }
    fun addDigit(input: Char): String {
        if (stack.size() == 1 && stack.peek() == '0') {
            stack.pop()
            stack.push(input)
        } else {
            stack.push(input)
        }
        formula = stack.toString()
        return formula
    }
    fun addOperator2(input: Char): Pair<String, String> {
        previous = stack.toString() + input
        var result = BigDecimal("0".toString()).setScale(27)
        if (listOf('*', '/', '=').contains(stackOfOperators.peek())) {
            val a = stackOfNums.pop()
            val b = stackOfNums.pop()
            val op = stackOfOperators.pop()
            result = when (op!!) {
                '*', '×' -> BigDecimal(a!!.toString()).setScale(27) *
                        BigDecimal(b!!.toString())
                else -> BigDecimal(a!!.toString()).setScale(27) /
                        BigDecimal(b!!.toString())
            }
            stackOfOperators.push(input)
        } else {

        }
        val resultString = removeTrailingZeros(result.toPlainString())
        stackOfNums.push(resultString)
        return Pair(previous, resultString + input)
    }

    private fun removeTrailingZeros(number: String): String {
        return number.replace("[0]*$".toRegex(), "").replace("\\.$".toRegex(), "")
    }
        fun addOperator(input: Char): Pair<String, String> {
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
                '+', '*', '×', '/', '÷', '-' -> {
                    stack.pop()
                    stack.push(input)
                    formula = stack.toString()
                }
                else -> {
                    stack.push(input)
                    formula = stack.toString()
                }
            }
        } else {
            stack.push(input)
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
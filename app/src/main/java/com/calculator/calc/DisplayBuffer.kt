package com.calculator.calc

import java.math.BigDecimal

class DisplayBuffer {
    private var currentScreen: String = "0"
    var previewResult: String = "0"
    var frozen: Boolean = false
    var stack: DisplayStack = DisplayStack()
    var stackOfOperators: OperationStack = OperationStack()
    var stackOfNums: NumericalStack = NumericalStack()
    var stackOfOperatorsRight: OperationStack = OperationStack()
    var stackOfNumsRight: NumericalStack = NumericalStack()


    fun backspace(): Pair<String, String> {
        previewResult = stack.toString()
        currentScreen = stack.toString()
        if (frozen) {  // what is frozen?
            currentScreen = "0"
            stack.refill("0")
        }
        if (currentScreen == "0")
            currentScreen = "0"
        else {
            stack.pop()
            currentScreen = stack.toString()
        }
        if (previewResult == "0") previewResult = ""
        return Pair(currentScreen, previewResult)
    }
    fun clear(): String {
        stack.clear()
        currentScreen = stack.toString()
        previewResult = ""
        frozen = false
        return currentScreen
    }
    fun addDigit(input: Char): String {
        if (stack.size() == 1 && stack.peek() == '0') {
            stack.pop()
            stack.push(input)

        } else {
            stack.push(input)
        }
        currentScreen = stack.toString()
        return currentScreen
    }


    fun addOperator(input: Char): Pair<String, String> {

        var result = BigDecimal("0".toString()).setScale(27)
        when (stackOfNums.size()) {
            0 -> {

            }
            1 -> {
                stackOfOperators.push(input)
                val a = stackOfNums.peek()
                result = BigDecimal(a!!.toString()).setScale(27)
            }
            2, 3, 4, 5, 6, 7 -> {
                if (!stackOfOperators.isEmpty()) {
                    if (listOf('*', '/', '=').contains(stackOfOperators.peek())) {
                        previewResult = stack.toString() + input
                        val a = stackOfNums.pop()
                        val b = stackOfNums.pop()
                        val op = stackOfOperators.pop()
                        result = when (op) {
                            '*', '×' -> BigDecimal(a.toString()).setScale(27) *
                                    BigDecimal(b.toString())
                            else -> BigDecimal(a.toString()).setScale(27) /
                                    BigDecimal(b.toString())
                        }
                        stackOfOperators.push(input)
                    } else {
                        if (stackOfNums.size() == 1) {
                            stackOfOperators.pop()
                            stackOfOperators.push(input)
                            val a = stackOfNums.peek()
                            result = BigDecimal(a.toString()).setScale(27)
                        }
                    }
                }
            }
        }
        val resultString = removeTrailingZeros(result.toPlainString())
        stackOfNums.push(resultString)
        if (previewResult.equals("0")) previewResult = ""
        return Pair(resultString + input, previewResult)
    }

    private fun removeTrailingZeros(number: String): String {
        return number.replace("[0]*$".toRegex(), "").replace("\\.$".toRegex(), "")
    }
    fun addDecimal(): String {
        val regex = """[\d|.]+$""".toRegex()
        val matchResult = regex.find(stack.toString())
        if (matchResult!!.value.contains(".")) {
                currentScreen = stack.toString()
            } else {
                stack.push('.')
                currentScreen = stack.toString()
            }
        return currentScreen
    }
}
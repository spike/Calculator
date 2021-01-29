package com.calculator.calc

import java.math.BigDecimal

class DisplayBuffer {
    private var previewResult: String = "0"
    var currentScreen: String = "0"
    var frozen: Boolean = false
    var stack: DisplayStack = DisplayStack()
    var stackOfOperators: OperationStack = OperationStack()
    var stackOfNums: NumericalStack = NumericalStack()


    fun backspace(): Pair<String, String> {
        currentScreen = stack.toString()
        previewResult = stack.toString()
        if (frozen) {  // what is frozen?
            previewResult = "0"
            stack.refill("0")
        }
        if (previewResult == "0")
            previewResult = "0"
        else {
            stack.pop()
            previewResult = stack.toString()
        }
        if (currentScreen == "0") currentScreen = ""
        return Pair(currentScreen, previewResult)
    }
    fun clear(): String {
        stack.clear()
        previewResult = stack.toString()
        currentScreen = ""
        frozen = false
        return previewResult
    }
    fun addDigit(input: Char): String {
        if (stack.size() == 1 && stack.peek() == '0') {
            stack.pop()
            stack.push(input)

        } else {
            stack.push(input)
        }
        previewResult = stack.toString()
        return previewResult
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
                        currentScreen = stack.toString() + input
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
                        if (stackOfNums.size() == 1) {
                            stackOfOperators.pop()
                            stackOfOperators.push(input)
                            val a = stackOfNums.peek()
                            result = BigDecimal(a!!.toString()).setScale(27)
                        }

                    }
                }
            }
        }
        val resultString = removeTrailingZeros(result.toPlainString())
        stackOfNums.push(resultString)
        if (currentScreen.equals("0")) currentScreen = ""
        return Pair(currentScreen, resultString + input)
    }

    private fun removeTrailingZeros(number: String): String {
        return number.replace("[0]*$".toRegex(), "").replace("\\.$".toRegex(), "")
    }
    fun addDecimal(): String {
        val regex = """[\d|.]+$""".toRegex()
        val matchResult = regex.find(stack.toString())
        if (matchResult!!.value.contains(".")) {
                previewResult = stack.toString()
            } else {
                stack.push('.')
                previewResult = stack.toString()
            }
        return previewResult
    }
}
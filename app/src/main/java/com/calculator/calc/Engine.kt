package com.calculator.calc

import java.math.BigDecimal

class Engine {
    fun previewCalculate(displayBuffer: DisplayBuffer): Pair<String, String> {
        return previewCalculate("", displayBuffer)
    }
    fun previewCalculate(previousFormula: String, displayBuffer: DisplayBuffer): Pair<String, String> {
        val nums: NumericalStack = displayBuffer.stackOfNums
        val ops: OperationStack = displayBuffer.stackOfOperators
        return when (Pair<Int, Int>(nums.size(), ops.size())) {
            Pair<Int, Int>(0, 0) -> Pair("0","")
            Pair<Int, Int>(1, 0) -> Pair(nums.peek(), "")
            Pair<Int, Int>(2, 1) -> {
                val b = nums.pop()
                val a = nums.peek()
                nums.push(b)
                val op = ops.peek()
                var result = when (op) {
                    '+' -> BigDecimal(a.toString()).setScale(27) +
                            BigDecimal(b.toString())
                    '-' -> BigDecimal(a.toString()).setScale(27) -
                            BigDecimal(b.toString())
                    '*', '×' -> BigDecimal(a.toString()).setScale(27) *
                            BigDecimal(b.toString())
                    else -> BigDecimal(a.toString()).setScale(27) /
                            BigDecimal(b.toString())
                }
                val resultString = removeTrailingZeros(result.toPlainString())
                if (previousFormula.isNotEmpty()) {
                    Pair("$a$op$previousFormula", resultString)
                } else {
                    Pair("$a$op$b", resultString)
                }
            }
            else -> {
                val b = nums.pop()
                val a = nums.peek()
                val op = ops.pop()
                // if previous operator has a higher or the same precedence
                // do this
                if (true) {
                    displayBuffer.stackOfNumsRight.push(a)
                    displayBuffer.stackOfNumsRight.push(b)
                    displayBuffer.stackOfOperatorsRight.push(op)
                    previewCalculate("$a$op$b$previousFormula", displayBuffer)
                } else {
                    // if previous operator has a lower precedence
                    // do this:
                    val result = when (op) {
                        '*', '×' -> BigDecimal(a.toString()).setScale(27) *
                                BigDecimal(b.toString())
                        else -> BigDecimal(a.toString()).setScale(27) /
                                BigDecimal(b.toString())
                    }
                    val resultString = removeTrailingZeros(result.toPlainString())
                    nums.push(resultString)
                    previewCalculate("$a$op$b", displayBuffer)
                }
            }
        }
    }

    fun calculate(formula: String): Pair<String, String> {
        val regex2 = """([-]?[\d|.]+)([\D]?)([-]?[\d|.]+)$""".toRegex()
        val matchResult = regex2.find(formula)
        val operand1 = matchResult?.groups?.get(1)?.value
        val operator = matchResult?.groups?.get(2)?.value
        val operand2 = matchResult?.groups?.get(3)?.value
        val lst = matchResult!!.groupValues
        if (operator == "") {
            return Pair(formula, formula)
        }
        var result = when (operator!!) {
            "+" -> BigDecimal(operand1!!.toString()).setScale(27) +
                    BigDecimal(operand2!!.toString())
            "-" -> BigDecimal(operand1!!.toString()).setScale(27) -
                    BigDecimal(operand2!!.toString())
            "*", "×" -> BigDecimal(operand1!!.toString()).setScale(27) *
                    BigDecimal(operand2!!.toString())
            else -> BigDecimal(operand1!!.toString()).setScale(27) /
                    BigDecimal(operand2!!.toString())
        }
        val resultString = removeTrailingZeros(result.toPlainString())
        return Pair(formula, regex2.replaceFirst(formula, resultString))
    }


    fun calculatePercentage(formula: String): Pair<String, String> {
        val regex = """[\d|.]+$""".toRegex()
        val matchResult = regex.find(formula)
        var result =
            BigDecimal(matchResult!!.value).setScale(27) * BigDecimal("0.01")
        val resultString = removeTrailingZeros(result.toPlainString())
        return Pair("$formula%", regex.replaceFirst(formula, resultString))
    }
    private fun removeTrailingZeros(number: String): String {
        return number.replace("[0]*$".toRegex(), "").replace("\\.$".toRegex(), "")
    }

    fun calculateNegation(formula: String): String {
        var result = ""
        var onlyOneOperatorRegex = """^([-]?)([\d|.]+)$""".toRegex()
        var matchResult = onlyOneOperatorRegex.find(formula)
        var replacement = ""
        if (matchResult == null) {
            val regex = """([\D]?[\+\-×÷])([\d|.]+)$""".toRegex()
            matchResult = regex.find(formula)
            val operators = matchResult?.groups?.get(1)?.value.toString()
            val secondOperand = matchResult?.groups?.get(2)?.value.toString()
            replacement =
                when (operators) {
                    "+" -> "-"
                    "-" -> "+"
                    "×+", "×" -> "×-"
                    "×-" -> "×"
                    "÷+", "÷" -> "÷-"
                    "÷-" -> "÷"
                    "++", "--" -> "-"
                    "-+", "+-" -> "+"
                    else -> "ERROR5:"
                }
            replacement += secondOperand
            result = regex.replaceFirst(formula, replacement)
        } else {
            val operator = matchResult?.groups?.get(1)?.value.toString()
            val operand = matchResult?.groups?.get(2)?.value.toString()
            if (operator == "-") {
                result = operand
            } else {
                result = "-$operand"
            }
        }
            return result
    }
}



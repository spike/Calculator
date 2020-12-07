package com.calculator.calc

import java.math.BigDecimal

class Engine {
    fun calculate(formula: String): String {
        val regex2 = """([-]?[\d|.]+)([\D]?)([-]?[\d|.]+)$""".toRegex()
        val matchResult = regex2.find(formula)
        val operand1 = matchResult?.groups?.get(1)?.value
        val operator = matchResult?.groups?.get(2)?.value
        val operand2 = matchResult?.groups?.get(3)?.value
        val lst = matchResult!!.groupValues
        if (operator == "") {
            return formula
        }
        var result = when (operator!!) {
            "+" -> BigDecimal.valueOf(operand1!!.toDouble()).setScale(20) +
                    BigDecimal.valueOf(operand2!!.toDouble())
            "-" -> BigDecimal.valueOf(operand1!!.toDouble()).setScale(20) -
                    BigDecimal.valueOf(operand2!!.toDouble())
            "*", "×" -> BigDecimal.valueOf(operand1!!.toDouble()).setScale(20) *
                    BigDecimal.valueOf(operand2!!.toDouble())
            else -> BigDecimal.valueOf(operand1!!.toDouble()).setScale(20) /
                    BigDecimal.valueOf(operand2!!.toDouble())
        }
        val resultString = removeTrailingZeros(result)
            return regex2.replaceFirst(formula, resultString)
    }
    fun calculatePercentage(formula: String): String {
        val regex = """[\d|.]+$""".toRegex()
        val matchResult = regex.find(formula)
        var result = BigDecimal.valueOf(
            matchResult!!.value.toDouble()).setScale(20) * BigDecimal.valueOf(0.01)
        val resultString = removeTrailingZeros(result)
        return regex.replaceFirst(formula, resultString)
    }
    private fun removeTrailingZeros(bd: BigDecimal): String {
        return bd.toString().replace("[0]*$".toRegex(), "").replace("\\.$".toRegex(), "")
    }

    fun calculateNegation(formula: String): String {
        var negativeOrPositiveRegex = """-[\d|.]+$""".toRegex()
        var matchResult = negativeOrPositiveRegex.find(formula)
        if (matchResult == null) {
            negativeOrPositiveRegex = """[\d|.]+$""".toRegex()
            matchResult = negativeOrPositiveRegex.find(formula)
        }
        var result = BigDecimal.valueOf(
            matchResult!!.value.toDouble()).setScale(20) *
                BigDecimal.valueOf(-1.0)
        val resultString = removeTrailingZeros(result)
            return negativeOrPositiveRegex.replaceFirst(formula, resultString)
    }
}


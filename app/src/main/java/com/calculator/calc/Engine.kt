package com.calculator.calc

import java.math.BigDecimal

class Engine {
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


        var negativeOrPositiveRegex = """^-[\d|.]+$""".toRegex()
        var matchResult = negativeOrPositiveRegex.find(formula)
        if (matchResult == null) {
            negativeOrPositiveRegex = """-[\d|.]+$""".toRegex()
            matchResult = negativeOrPositiveRegex.find(formula)
            if (matchResult == null) {

            } else {
                var result =
                    BigDecimal(matchResult!!.value).setScale(27) * BigDecimal("-1.0")
                val resultString = "+" + removeTrailingZeros(result.toPlainString())
                return negativeOrPositiveRegex.replaceFirst(formula, resultString)
            }
        }
        if (matchResult == null) {
            negativeOrPositiveRegex = """[\d|.]+$""".toRegex()
            matchResult = negativeOrPositiveRegex.find(formula)
        }
        var result =
            BigDecimal(matchResult!!.value).setScale(27) * BigDecimal("-1.0")

        val resultString = removeTrailingZeros(result.toPlainString())
            return negativeOrPositiveRegex.replaceFirst(formula, resultString)
    }
}


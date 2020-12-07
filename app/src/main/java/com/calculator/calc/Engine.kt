package com.calculator.calc

import java.lang.IllegalArgumentException
import java.math.BigDecimal

class Engine {

    fun calculate(formula: String): String {
        val regex = """([-]?[\d|.]+)([\D]?)([-]?[\d|.]+)$""".toRegex()
        val matchResult = regex.find(formula)
        val operand1 = matchResult?.groups?.get(1)?.value
        val operator = matchResult?.groups?.get(2)?.value
        val operand2 = matchResult?.groups?.get(3)?.value
        val lst = matchResult!!.groupValues

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
            return regex.replaceFirst(formula, resultString)
    }
    fun calculatePercentage(formula: String): String {
        val regex = """[\d|.]*$""".toRegex()
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
        var regex = """-[\d|.]*$""".toRegex()
        var matchResult = regex.find(formula)
        if (matchResult == null) {
            regex = """[\d|.]*$""".toRegex()
            matchResult = regex.find(formula)
        }
        var result = BigDecimal.valueOf(
            matchResult!!.value.toDouble()).setScale(20) *
                BigDecimal.valueOf(-1.0)
        val resultString = removeTrailingZeros(result)
            return regex.replaceFirst(formula, resultString)
    }

/*
    fun calculate(a: String): List<String> {
        for (e in a) {
            screen = when (e) {
                in '0'..'9' -> zeroThroughNine(e)
                '.' -> decimalMarker()
                in '*'..'/' -> operatorSign(e)
                '%' -> percentOperator()
                'm' -> makeNegative()
                '=' -> equalSign()
                else -> ""
            }
            result.add(screen)
        }
        return result
    }
*/

/*
    fun equalSign(): String {
        var num3: BigDecimal = BigDecimal.valueOf(0.0)
        if (completedSecondNumber) {
            num3 = when (operator) {
                "+" -> BigDecimal.valueOf(firstOperandAsString.toDouble())
                    .setScale(20) + BigDecimal.valueOf(secondOperandAsString.toDouble())
                "×", "*" -> BigDecimal.valueOf(firstOperandAsString.toDouble())
                    .setScale(20) * BigDecimal.valueOf(secondOperandAsString.toDouble())
                "÷", "/" -> BigDecimal.valueOf(firstOperandAsString.toDouble())
                    .setScale(20) / BigDecimal.valueOf(secondOperandAsString.toDouble())
                "-" -> BigDecimal.valueOf(firstOperandAsString.toDouble())
                    .setScale(20) - BigDecimal.valueOf(secondOperandAsString.toDouble())
                else -> throw IllegalArgumentException("No operator found: else -> ?")
            }
        }
        if (upcomingOperator.length > 0) {
            operator = upcomingOperator
        }
        if (lastCalledEqual) {
            firstOperandAsString = lastResult
        } else {
            secondOperandAsString = ""
            completedSecondNumber = false
            workingOnFirstNumber = false
            lastCalledEqual = true
            firstOperandAsString = removeZeroExponent(removeTrailingZeros(num3))
            lastResult = firstOperandAsString
        }
        return firstOperandAsString
    }

    private fun removeTrailingZeros(bd: BigDecimal): String { // formatting, not a calculation
        return bd.toString().replace("[0]*$".toRegex(), "").replace("\\.$".toRegex(), "")
    }

    private fun removeZeroExponent(bd: String): String {  // formatting, not a calculation
        if (bd.contains("^0E".toRegex()))
                return "0"
            else
                return bd
    } */

}


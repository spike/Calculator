package com.calculator.calc

import java.lang.IllegalArgumentException
import java.math.BigDecimal

class Engine {

    fun calculate(): String {
        return "result"
    }
    fun addPercentSign(): String {
        return "percent"
    }
    fun negate(): String {
        return "negated"
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


package com.calculator.calc

import java.lang.IllegalArgumentException
import java.math.BigDecimal

class Engine {
    var workingOnFirstNumber: Boolean = true
    var completedSecondNumber: Boolean = false
    var result: MutableList<String> = mutableListOf()
    var buf: String = ""
    var screen: String = ""
    var firstOperandAsString = "0"
    var secondOperandAsString = ""
    var operator = ""
    var isPreviousOperatorConsecutive = false

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



    var lastCalledEqual = false
    var lastResult = ""

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
    }



    fun zeroThroughNine(e: Char): String {
        lastCalledEqual = false
        return if (workingOnFirstNumber) {
            partOfFirstNumber(e)
        } else
            partOfSecondNumber(e)
    }

    private fun partOfFirstNumber(e: Char): String {
        isPreviousOperatorConsecutive = false
        if ((firstOperandAsString.length == 1) &&
            firstOperandAsString.first().equals('0')) {
            firstOperandAsString = firstOperandAsString.drop(1) + e
        } else {
            firstOperandAsString += e
        }
        return firstOperandAsString
    }

    private fun partOfSecondNumber(e: Char): String {
        isPreviousOperatorConsecutive = false
        completedSecondNumber = true
        secondOperandAsString +=e
        return secondOperandAsString
    }

    fun operatorSign(e: Char): String {
        return if (workingOnFirstNumber) {
            firstOperatorMiddle(e)
        } else {
            secondOperator(e)
        }

    }
    private fun firstOperatorMiddle(e: Char): String {
        workingOnFirstNumber = false
        secondOperandAsString = ""
        operator = e.toString()
        noDecimalMarkerInSecondYet = true
        isPreviousOperatorConsecutive = true
        return operator
    }
    var upcomingOperator = ""
    private fun secondOperator(e: Char): String {
        var secondResult = ""
        noDecimalMarkerInSecondYet = true
        upcomingOperator = e.toString()
        if (isPreviousOperatorConsecutive) {
            operator = e.toString()
            secondResult = e.toString()
        } else {
            secondResult = "(${equalSign()})${e.toString()}"
        }
        isPreviousOperatorConsecutive = true
        return secondResult
    }

    fun decimalMarker(): String {
        lastCalledEqual = false
        return if (workingOnFirstNumber) {
            decimalMarkerInFirstNumber()
        } else {
            decimalMarkerInSecondNumber()
        }
    }
    var noDecimalMarkerInFirstYet = true
    var noDecimalMarkerInSecondYet = true

    fun decimalMarkerInFirstNumber(): String {
        if (noDecimalMarkerInFirstYet) {
            if (firstOperandAsString.length > 0) {
                firstOperandAsString += "."
            } else {
                firstOperandAsString = "0."
            }
        }
        noDecimalMarkerInFirstYet = false
        return firstOperandAsString
    }
    private fun decimalMarkerInSecondNumber(): String {
        if (noDecimalMarkerInSecondYet) {
            if (secondOperandAsString.length > 0) {
                secondOperandAsString += "."
            } else {
                secondOperandAsString = "0."
            }
        }
        noDecimalMarkerInSecondYet = false
        return secondOperandAsString
    }

    fun clear(): String {
        firstOperandAsString = "0"
        secondOperandAsString = "0"
        operator = ""
        upcomingOperator = ""
        buf = ""
        isPreviousOperatorConsecutive = false
        workingOnFirstNumber = true
        completedSecondNumber = false
        noDecimalMarkerInSecondYet = true
        noDecimalMarkerInFirstYet = true
        return firstOperandAsString
    }

    fun percentOperator(): String {
        return if (workingOnFirstNumber) {
            percentOnFirstOperand()
        } else {
            percentOnSecondOperand()
        }
    }

    private fun percentOnFirstOperand(): String {
        firstOperandAsString = removeTrailingZeros(BigDecimal.valueOf(firstOperandAsString.toDouble()).setScale(20) / BigDecimal.valueOf(100.0)).toString()
        return firstOperandAsString
    }

    private fun percentOnSecondOperand(): String {
        secondOperandAsString = removeTrailingZeros(BigDecimal.valueOf(secondOperandAsString.toDouble()).setScale(20) / BigDecimal.valueOf(100.0)).toString()
        return secondOperandAsString
    }

    fun makeNegative(): String {
        return if (workingOnFirstNumber) {
            makeNegativeFirstOperand()
        } else {
            makeNegativeSecondOperand()
        }
    }
    fun makeNegativeFirstOperand(): String {
        firstOperandAsString = removeTrailingZeros(BigDecimal.valueOf(firstOperandAsString.toDouble()).setScale(20) * BigDecimal.valueOf(-1.0)).toString()
        return firstOperandAsString
    }
    fun makeNegativeSecondOperand(): String {
        secondOperandAsString = removeTrailingZeros(BigDecimal.valueOf(secondOperandAsString.toDouble()).setScale(20) * BigDecimal.valueOf(-1.0)).toString()
        return secondOperandAsString
    }
}


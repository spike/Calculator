package com.calculator.calc

import java.math.BigDecimal
import java.math.RoundingMode

class Calculation {
    // "12345+5="
    // listOf("1", "12", "123", "1234", "12345", "+", "5", "12350")
    var leadingCharacter: Boolean = true
    var workingOnFirstNumber: Boolean = true
    var completedSecondNumber: Boolean = false
    var operatorSet: Boolean = false
    var result: MutableList<String> = mutableListOf()
    var buf: String = ""
    var screen: String = ""
    var firstOperandAsString = ""
    var secondOperandAsString = ""
    var operator = ""


    fun calculate(a: String): List<String> {
        for (e in a) {
            screen = when (e.toInt()) {
                in 48..57 -> zeroThroughNine(e)
               // 48 -> zero()
                46 -> decimalPoint()
                in 42..47 -> operatorSign(e)
                61 -> equalSign()
                98 -> backSpace()
                99 -> clearScreen()
                else -> ""
            }
            result.add(screen)
        }
        return result
    }

    fun clearScreen(): String {
        return ""
    }

    fun backSpace(): String {
        // backSpaceOnOperator()
        return if (workingOnFirstNumber) {
            backSpaceOnFirstNumber()
        } else {
            backSpaceOnSecondNumber()
        }
    }

    private fun backSpaceOnFirstNumber(): String {
        if (firstOperandAsString.length > 0) {
            firstOperandAsString = firstOperandAsString.dropLast(1)
        } else {
            firstOperandAsString
        }
        return firstOperandAsString
    }

    private fun backSpaceOnSecondNumber(): String {
        if (secondOperandAsString.length > 0) {
            secondOperandAsString = secondOperandAsString.dropLast(1)
        } else {
            secondOperandAsString
        }
        return secondOperandAsString
    }
    var lastCalledOperator = false
    var lastCalledEqual = false
    var lastResult = ""
    fun equalSign(): String {
        var num3: BigDecimal = BigDecimal.valueOf(0.0)
        if (completedSecondNumber) {
            num3 = when (operator) {
                "+" -> BigDecimal.valueOf(firstOperandAsString.toDouble()) + BigDecimal.valueOf(secondOperandAsString.toDouble())
                "*" -> BigDecimal.valueOf(firstOperandAsString.toDouble()) * BigDecimal.valueOf(secondOperandAsString.toDouble())
                "/" -> BigDecimal.valueOf(firstOperandAsString.toDouble()) / BigDecimal.valueOf(secondOperandAsString.toDouble())
                "-" -> BigDecimal.valueOf(firstOperandAsString.toDouble()) - BigDecimal.valueOf(secondOperandAsString.toDouble())
                else -> BigDecimal.valueOf(99999.999)
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
            firstOperandAsString = num3.stripTrailingZeros().toString()
            lastResult = firstOperandAsString
        }
        return firstOperandAsString
    }

    fun zeroThroughNine(e: Char): String {
        lastCalledEqual = false
        return if (workingOnFirstNumber) {
            partOfFirstNumber(e)
        } else
            partOfSecondNumber(e)
    }

    private fun partOfFirstNumber(e: Char): String {
        if ((firstOperandAsString.length == 1) &&
            firstOperandAsString.first().equals('0')) {
            firstOperandAsString = firstOperandAsString.drop(1) + e
        } else {
            firstOperandAsString += e
        }
        return firstOperandAsString
    }

    private fun partOfSecondNumber(e: Char): String {
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
        noDecimalPointInSecondYet = true
        return operator
    }
    var upcomingOperator = ""
    private fun secondOperator(e: Char): String {
        noDecimalPointInSecondYet = true
        upcomingOperator = e.toString()
        return "(${equalSign()})${e.toString()}"
        // return "()${e.toString()}"
    }

    fun minusSign(): String {
        return ""
    }

    fun decimalPoint(): String {
        lastCalledEqual = false
        return if (workingOnFirstNumber) {
            decimalPointInFirstNumber()
        } else {
            decimalPointInSecondNumber()
        }
    }
    var noDecimalPointInFirstYet = true
    var noDecimalPointInSecondYet = true

    fun decimalPointInFirstNumber(): String {
        if (noDecimalPointInFirstYet) {
            if (firstOperandAsString.length > 0) {
                firstOperandAsString += "."
            } else {
                firstOperandAsString = "0."
            }
        }
        noDecimalPointInFirstYet = false
        return firstOperandAsString
    }
    private fun decimalPointInSecondNumber(): String {
        if (noDecimalPointInSecondYet) {
            if (secondOperandAsString.length > 0) {
                secondOperandAsString += "."
            } else {
                secondOperandAsString = "0."
            }
        }
        noDecimalPointInSecondYet = false
        return secondOperandAsString
    }

    fun clear() {
        firstOperandAsString = ""
        secondOperandAsString = ""
        operator = ""
        upcomingOperator = ""
        buf = ""
        workingOnFirstNumber = true
        completedSecondNumber = false
        noDecimalPointInSecondYet = true
        noDecimalPointInFirstYet = true
    }

}


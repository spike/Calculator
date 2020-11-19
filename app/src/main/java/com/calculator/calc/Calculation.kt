package com.calculator.calc

import java.lang.IllegalArgumentException
import java.math.BigDecimal

class Calculation {
    var workingOnFirstNumber: Boolean = true
    var completedSecondNumber: Boolean = false
    var result: MutableList<String> = mutableListOf()
    var buf: String = ""
    var screen: String = ""
    var firstOperandAsString = ""
    var secondOperandAsString = ""
    var operator = ""
    var isPreviousOperatorConsecutive = false

    fun calculate(a: String): List<String> {
        for (e in a) {
            screen = when (e.toInt()) {
                in 48..57 -> zeroThroughNine(e)
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
        return if (workingOnFirstNumber) {
            backSpaceOnFirstNumber()
        } else {
            backSpaceOnSecondNumber()
        }
    }

    private fun backSpaceOnFirstNumber(): String {
        if (firstOperandAsString.length > 0) {
            firstOperandAsString = firstOperandAsString.dropLast(1)
        }
        if (firstOperandAsString.length == 0) {
            firstOperandAsString = "0"
        }
        return firstOperandAsString
    }

    private fun backSpaceOnSecondNumber(): String {
        if (secondOperandAsString.length > 0) {
            secondOperandAsString = secondOperandAsString.dropLast(1)
        }
        if (secondOperandAsString.length == 0) {
            secondOperandAsString = "0"
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
                "+" -> BigDecimal.valueOf(firstOperandAsString.toDouble())
                    .setScale(20) + BigDecimal.valueOf(secondOperandAsString.toDouble())
                "×", "*" -> BigDecimal.valueOf(firstOperandAsString.toDouble())
                    .setScale(20) * BigDecimal.valueOf(secondOperandAsString.toDouble())
                "÷", "/" -> BigDecimal.valueOf(firstOperandAsString.toDouble())
                    .setScale(20) / BigDecimal.valueOf(secondOperandAsString.toDouble())
                "-" -> BigDecimal.valueOf(firstOperandAsString.toDouble())
                    .setScale(20) - BigDecimal.valueOf(secondOperandAsString.toDouble())
                else -> { BigDecimal.valueOf(0.0)
                // throw IllegalArgumentException("Wrong operator sent")
                }
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
            firstOperandAsString = removeTrailingZeros(num3)
            lastResult = firstOperandAsString
        }
        return firstOperandAsString
    }

    private fun removeTrailingZeros(bd: BigDecimal): String {
        return bd.toString().replace("[0]*$".toRegex(), "").replace("\\.$".toRegex(), "")
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
        noDecimalPointInSecondYet = true
        isPreviousOperatorConsecutive = true
        return operator
    }
    var upcomingOperator = ""
    private fun secondOperator(e: Char): String {
        var secondResult = ""
        noDecimalPointInSecondYet = true
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
        isPreviousOperatorConsecutive = false
        workingOnFirstNumber = true
        completedSecondNumber = false
        noDecimalPointInSecondYet = true
        noDecimalPointInFirstYet = true
    }

    fun percentOperator(): String {
        operatorSign('/')
        zeroThroughNine('1')
        zeroThroughNine('0')
        zeroThroughNine('0')
        return equalSign()
    }

    fun makeNegative(): String {

      /*  operatorSign('*')
        zeroThroughNine('1')
        zeroThroughNine('0')
        zeroThroughNine('0') */
        return equalSign()
    }

}


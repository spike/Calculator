package com.calculator.calc

import java.math.BigDecimal

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
        return if (firstOperandAsString.length > 0) {
            firstOperandAsString.dropLast(1)
        } else {
            firstOperandAsString
        }
    }

    private fun backSpaceOnSecondNumber(): String {
        return if (secondOperandAsString.length > 0) {
            secondOperandAsString.dropLast(1)
        } else {
            secondOperandAsString
        }
    }

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
        secondOperandAsString = ""
        completedSecondNumber = false
        firstOperandAsString = num3.setScale(0).toString()
        workingOnFirstNumber = false
        return firstOperandAsString
    }

    fun zeroThroughNine(e: Char): String {
        return if (workingOnFirstNumber) {
            partOfFirstNumber(e)
        } else
            partOfSecondNumber(e)
    }

    private fun partOfFirstNumber(e: Char): String {
        firstOperandAsString += e
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
        return operator
    }
    var upcomingOperator = ""
    private fun secondOperator(e: Char): String {
        upcomingOperator = e.toString()
        return "(${equalSign()})${e.toString()}"
        // return "()${e.toString()}"
    }

    fun minusSign(): String {
        return ""
    }

    fun decimalPoint(): String {
        return if (workingOnFirstNumber) {
            decimalPointInFirstNumber()
        } else {
            decimalPointInSecondNumber()
        }
    }

    fun decimalPointInFirstNumber(): String {
        if (firstOperandAsString.length > 0) {
            firstOperandAsString += "."
        } else {
            firstOperandAsString = "0."
        }
        return firstOperandAsString
    }
    private fun decimalPointInSecondNumber(): String {
        if (secondOperandAsString.length > 0) {
            secondOperandAsString += "."
        } else {
            secondOperandAsString = "0."
        }
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
    }

}


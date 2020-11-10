package com.calculator.calc

import java.math.BigDecimal

class Calculation {
    // "12345+5="
    // listOf("1", "12", "123", "1234", "12345", "+", "5", "12350")
    var leadingCharacter: Boolean = true
    var workingOnFirstOperand: Boolean = true
    var completedSecondNumber: Boolean = false
    var operatorSet: Boolean = false
    var result: MutableList<String> = mutableListOf()
    var buf: String = ""
    var screen: String = ""
    var firstOperand = ""
    var secondOperand = ""
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

    fun zero(): String {
        buf += "0"
        leadingCharacter = false
        return buf
    }



    fun backSpace(): String {
        // backSpaceOnOperator()
        return if (workingOnFirstOperand) {
            backSpaceOnFirstNumber()
        } else {
            backSpaceOnSecondNumber()
        }
    }

    private fun backSpaceOnFirstNumber(): String {
        return if (firstOperand.length > 0) {
            firstOperand.dropLast(1)
        } else {
            firstOperand
        }
    }

    private fun backSpaceOnSecondNumber(): String {
        return if (secondOperand.length > 0) {
            secondOperand.dropLast(1)
        } else {
            secondOperand
        }
    }

    fun equalSign(): String {
        var num3: BigDecimal = BigDecimal.valueOf(0.0)
        if (completedSecondNumber) {
            num3 = when (operator) {
                "+" -> BigDecimal.valueOf(firstOperand.toDouble()) + BigDecimal.valueOf(secondOperand.toDouble())
                "*" -> BigDecimal.valueOf(firstOperand.toDouble()) * BigDecimal.valueOf(secondOperand.toDouble())
                "/" -> BigDecimal.valueOf(firstOperand.toDouble()) / BigDecimal.valueOf(secondOperand.toDouble())
                "-" -> BigDecimal.valueOf(firstOperand.toDouble()) - BigDecimal.valueOf(secondOperand.toDouble())
                else -> BigDecimal.valueOf(99999.999)
            }
        }

        secondOperand = ""
        completedSecondNumber = false
        firstOperand = num3.toString()
        workingOnFirstOperand = false
        return firstOperand
    }

    fun zeroThroughNine(e: Char): String {
        return if (workingOnFirstOperand) {
            partOfFirstNumber(e)
        } else
            partOfSecondNumber(e)
    }

    private fun partOfFirstNumber(e: Char): String {
        firstOperand += e
        return firstOperand
    }

    private fun partOfSecondNumber(e: Char): String {
        completedSecondNumber = true
        secondOperand +=e
        return secondOperand
    }

    fun operatorSign(e: Char): String {
        return if (workingOnFirstOperand) {
            firstOperatorMiddle(e)
        } else {
            secondOperator(e)
        }
    }
    private fun firstOperatorMiddle(e: Char): String {
        workingOnFirstOperand = false
        secondOperand = ""
        operator = e.toString()
        return operator
    }
    private fun secondOperator(e: Char): String {
        operator = e.toString()
        return "(${equalSign()})${e.toString()}"
        // return "()${e.toString()}"
    }

    fun minusSign(): String {
        return ""
    }

    fun decimalPoint(): String {
        return if (workingOnFirstOperand) {
            decimalPointInFirstNumber()
        } else {
            decimalPointInSecondNumber()
        }
    }

    fun decimalPointInFirstNumber(): String {
        if (firstOperand.length > 0) {
            firstOperand += "."
        } else {
            firstOperand = "0."
        }
        return firstOperand
    }
    private fun decimalPointInSecondNumber(): String {
        if (secondOperand.length > 0) {
            secondOperand += "."
        } else {
            secondOperand = "0."
        }
        return secondOperand
    }

}


package com.calculator.calc

import java.math.BigDecimal

class Calculation {
    // "12345+5="
    // listOf("1", "12", "123", "1234", "12345", "+", "5", "12350")
    var leadingCharacter: Boolean = true
    var workingOnFirstOperand: Boolean = true
    var secondNumber: Boolean = false
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
                in 49..57 -> oneThroughNine(e)
                48 -> zero()
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

    fun oneThroughNine(e: Char): String {
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
        secondOperand +=e
        return secondOperand
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

    var num: BigDecimal = BigDecimal.valueOf(0)
    var num2: Double = 0.0


    fun equalSign(): String {
        secondOperand = buf
        println("$workingOnFirstOperand and $secondNumber")
        if (secondNumber) {

            num2 = when (operator) {
                "+" -> {
                    println("==${(firstOperand.toDouble())} + ${secondOperand.toDouble()}")
                    (firstOperand.toDouble() + secondOperand.toDouble())
                }
                "*" -> (firstOperand.toDouble() * secondOperand.toDouble())
                "/" -> (firstOperand.toDouble() / secondOperand.toDouble())
                "-" -> (firstOperand.toDouble() - secondOperand.toDouble())
                else -> 999999.9
            }
        }
        operatorSet = true
        buf = num2.toString()
        return buf
    }

    fun operatorSign(e: Char): String {
        return if (workingOnFirstOperand) {
            firstOperatorMiddle(e)
        } else {
            secondOperatorMiddle(e)
        }
    }
    private fun firstOperatorMiddle(e: Char): String {
        workingOnFirstOperand = false
        secondOperand = ""
        operator = e.toString()
        return operator
    }
    private fun secondOperatorMiddle(e: Char): String {
        //operator = e.toString()
        //return operator
        return ""
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

    private fun decimalPointInFirstNumber(): String {
        return ""
    }
    private fun decimalPointInSecondNumber(): String {
        return ""
    }

}


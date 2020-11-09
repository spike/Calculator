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
        if (workingOnFirstOperand) { // working on first operand
            buf += e
            leadingCharacter = false
        } else {
            if (operator.equals(buf.first().toString())) {
                buf = buf.drop(1) + e
            } else {
                buf += e
            }
            secondNumber = true
        }
        return buf
    }

    fun backSpace(): String {
        if (buf.length > 1) {
            buf = buf.dropLast(1)
        } else {
            buf = "0"
            workingOnFirstOperand = true
        }
        return buf
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
        if (workingOnFirstOperand) {
            workingOnFirstOperand = false
            firstOperand = buf
            secondOperand = ""
            operator = e.toString()
        } else {

        }
        return buf
    }

    fun minusSign(): String {
        return ""
    }

    fun decimalPoint(): String {
        if (leadingCharacter) {
            buf = "0."
            leadingCharacter = false
        } else {
            if (!buf.contains('.')) { // no duplicates
                buf += "."
            }
        }
        if (workingOnFirstOperand) {
            firstOperand = buf
        } else {
            secondOperand = buf
        }
        return buf
    }

}


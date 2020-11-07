package com.calculator.calc

class Calculation {
    // "12345+5="
    // listOf("1", "12", "123", "1234", "12345", "+", "5", "12350")
    var dotless: Boolean = true
    var operatorless: Boolean = true
    var leadingCharacter: Boolean = true
    var firstNumber: Boolean = true
    var secondNumber: Boolean = false
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
               // 45 -> minusSign()
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
        if (firstNumber) {
            buf += e
            leadingCharacter = false
        } else {
            if (operator.equals(buf.first().toString())) {
                buf = buf.drop(1) + e
                println("==$operator ${buf.first().toString()}")
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
            firstNumber = true
        }
        return buf
    }

    fun equalSign(): String {
        secondOperand = buf
        if (firstNumber && secondNumber) {
            buf = when (operator) {
                "+" -> (firstOperand.toBigDecimal() + secondOperand.toBigDecimal()).toString()
                "*" -> (firstOperand.toBigDecimal() * secondOperand.toBigDecimal()).toString()
                "/" -> (firstOperand.toBigDecimal() / secondOperand.toBigDecimal()).toString()
                "-" -> (firstOperand.toBigDecimal() - secondOperand.toBigDecimal()).toString()
                else -> "?"
            }
        }
        return buf
    }

    fun operatorSign(e: Char): String {
        if (firstNumber) {
            firstNumber = false
            firstOperand = buf
            operator = e.toString()
            buf = e.toString()
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
        if (firstNumber) {
            firstOperand = buf
        } else {
            secondOperand = buf
        }
        return buf
    }



    fun loadOperand(s: String): Boolean {
        return false
    }

    fun loadOperator(s: String): Boolean {
        return false

    }
    fun getResult(): String {
        return ""

    }
    fun clear(): String {
        return ""
    }
}


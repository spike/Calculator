package com.calculator.calc

class Calculation {
    // "12345+5="
    // listOf("1", "12", "123", "1234", "12345", "+", "5", "12350")
    var dotless: Boolean = true
    var operatorless: Boolean = true
    var leadingCharacter: Boolean = true
    var firstNumber: Boolean = true
    var result: MutableList<String> = mutableListOf()
    var number: String = ""

    fun calculate(a: String): List<String> {
        for (e in a) {
            when (e.toChar().toInt()) {
                in 49..57 -> oneThroughNine()
                48 -> zero()
                46 -> decimalPoint()
                45 -> minusSign()
                in 42..47 -> operatorSign()
                61 -> equalSign()
                98 -> backSpace()
               // else -> // error
            }
        }
        return result
    }

    private fun backSpace() {
        TODO("Not yet implemented")
    }

    private fun equalSign() {
        TODO("Not yet implemented")
    }

    private fun operatorSign() {
        TODO("Not yet implemented")
    }

    private fun minusSign() {
        TODO("Not yet implemented")
    }

    private fun decimalPoint() {
        TODO("Not yet implemented")
    }

    private fun zero() {
        TODO("Not yet implemented")
    }

    private fun oneThroughNine() {
        TODO("Not yet implemented")
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


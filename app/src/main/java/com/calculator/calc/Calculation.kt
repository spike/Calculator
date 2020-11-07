package com.calculator.calc

class Calculation {

    fun calculate(a: String): List<String> {
        var result: MutableList<String> = mutableListOf()
        for (e in a) {

        }
        return result
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


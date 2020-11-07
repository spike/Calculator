package com.calculator.calc

class Calculation {
    // val aaa ="12345+5="
    // val bbb = listOf("12345", "5", "12350")
    fun calculate(a: String): List<String> {
        var result: MutableList<String> = mutableListOf()
        for (e in a) {
          //  if (e.equals())
            // isSign
            // isDot
            // isNumber


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


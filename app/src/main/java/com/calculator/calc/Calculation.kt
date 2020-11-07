package com.calculator.calc

class Calculation {
    // val aaa ="12345+5="
    // val bbb = listOf("12345", "5", "12350")
    fun calculate(a: String): List<String> {
        var result: MutableList<String> = mutableListOf()
        var operand: String = ""
        var dotless: Boolean = true
        var operatorless: Boolean = true
        for (e in a) {
          if (e.isDigit()) {
              operand += e
          } else {
              result.add(operand)
              operand = ""
              if (e.equals('.')) {
                  if (dotless) {
                      operand += e
                      dotless = false
                  }
              } else { // then it must be an operator

              }
          }

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


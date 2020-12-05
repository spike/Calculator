package com.calculator.calc

import java.lang.IllegalArgumentException
import java.math.BigDecimal

class Editing {

    fun backspace(input: String): String {
      return input.dropLast(1)
    }

}
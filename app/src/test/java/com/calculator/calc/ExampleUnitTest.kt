package com.calculator.calc

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ExampleUnitTest {
    private lateinit var c: Calculation

    fun compute(opOne: String, opTwo: String, sign: String): String {
        c.loadOperand(opOne)
        c.loadOperator(sign)
        c.loadOperand(opTwo)
        return c.getResult()
    }
    @Before
    fun setUp(): Unit {
        c = Calculation()
    }
    @Test
    fun testSubtraction() {
        assertEquals("123400", compute("123456", "56", "-"))
    }
}


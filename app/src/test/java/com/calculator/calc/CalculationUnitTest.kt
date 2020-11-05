package com.calculator.calc

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.After

class CalculationUnitTest {
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
    @Test
    fun testAddition() {
        assertEquals("123496", compute("123456", "40", "+"))
    }
    @Test
    fun testDivision() {
        assertEquals("5", compute("20", "4", "/"))
    }
    @Test
    fun testMultiplication() {
        assertEquals("250", compute("25", "10", "*"))
    }
    @After
    fun tearDown(): Unit {
        c.clear()
    }
}


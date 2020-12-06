package com.calculator.calc

import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class DisplayUnitTestSuite {
    private lateinit var display: Display

    @Before
    fun setUp(): Unit {
        display = Display()
    }
    @After
    fun tearDown(): Unit {
        display.clear()
    }

    @Test
    fun testBackspace() {
        display.formula = "12345+5"
        val expected = "12345+"
        assertEquals(expected, display.backspace())
    }
    @Test
    fun testBackspaceZero() {
        display.formula ="0"
        val expected = "0"
        assertEquals(expected, display.backspace())
    }
    @Test
    fun testClear() {
        display.formula = "1234567"
        val expected = "0"
        assertEquals(expected, display.clear())
    }
    @Test
    fun testAddDigit() {
        display.formula = "90+4"
        val input = "8"
        val expected = "90+48"
        assertEquals(expected, display.addCharacter(input))
    }
    @Test
    fun testAddOperator() {
        display.formula = "9"
        val input = "+"
        val expected = "9+"
        assertEquals(expected, display.addOperator(input))
    }
    @Test
    fun testAddPercent() {
        display.formula = "2275"
        val input = "%"
        val expected = "2275%"
        assertEquals(expected, display.addOperator(input))
    }
    @Test
    fun testAddUpfrontZeros() {
        display.formula = "0"
        val input = "0"
        val expected = "0"
        assertEquals(expected, display.addCharacter(input))
    }
    @Test
    fun testExtraneousAdjacentOperator() {
        display.formula = "3+"
        val input = "*"
        val expected = "3*"
        assertEquals(expected, display.addOperator(input))
    }
    @Test
    fun testAllowedAdjacentMinusOperator() {
        display.formula = "3*"
        val input = "-"
        val expected = "3*-"
        assertEquals(expected, display.addOperator(input))
    }
    @Test
    fun testExtraneousSecondDecimal() {
        display.formula = "3.4"
        val input = "."
        val expected = "3.4"
        assertEquals(expected, display.addDecimal())
    }
    @Test
    fun testSecondDecimalInSecondNumber() {
        display.formula = "3.4+6"
        val input = "."
        val expected = "3.4+6."
        assertEquals(expected, display.addDecimal())
    }
    @Test
    fun testExtraneousSecondDecimalInSecondNumber() {
        display.formula = "3.4+6.8"
        val input = "."
        val expected = "3.4+6.8"
        assertEquals(expected, display.addDecimal())
    }
    @Test
    fun testPercentThenOperator() {
        display.formula = "38%"
        val input = "+"
        val expected = "38%+"
        assertEquals(expected, display.addOperator(input))
    }
/*    @Test       // This should be done by the calculation engine
    fun testMinusPlusUnaryOperator() {
        display.screen = "35"
        val input = "m"
        val expected = "-35"
        assertEquals(expected, display.addOperator(input))
    }*/
    @Test
    fun testDecimalRightAfterPercentSign() {
        display.formula = "38%"
        val input = "."
        val expected = "38%0."
        assertEquals(expected, display.addDecimal())
    }
    @Test
    fun testMissingFirstOperator() {
        display.formula = "0"
        val input = "/"
        val expected = "0/"
        assertEquals(expected, display.addOperator(input))
    }
    @Test
    fun testLeadingZerosInSecondOperator() {
        display.formula = "8-0"
        val input = "0"
        val expected = "8-0"
        assertEquals(expected, display.addCharacter(input))
    }
}
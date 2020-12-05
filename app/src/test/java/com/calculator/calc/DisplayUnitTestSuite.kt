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
        display.screen = "0"
    }

    @Test
    fun testBackspace() {
        val input ="12345+5"
        val expected = "12345+"
        assertEquals(expected, display.backspace(input))
    }
    @Test
    fun testBackspaceZero() {
        val input ="0"
        val expected = "0"
        assertEquals(expected, display.backspace(input))
    }
    @Test
    fun testClear() {
        val input = "90+40"
        val expected = "0"
        assertEquals(expected, display.clear(input))
    }
    @Test
    fun testAddDigit() {
        display.screen = "90+4"
        val input = "8"
        val expected = "90+48"
        assertEquals(expected, display.addCharacter(input))
    }
    @Test
    fun testAddOperator() {
        display.screen = "9"
        val input = "+"
        val expected = "9+"
        assertEquals(expected, display.addOperator(input))
    }
    @Test
    fun testAddPercent() {
        display.screen = "2275"
        val input = "%"
        val expected = "2275%"
        assertEquals(expected, display.addOperator(input))
    }
    @Test
    fun testAddUpfrontZeros() {
        display.screen = "0"
        val input = "0"
        val expected = "0"
        assertEquals(expected, display.addCharacter(input))
    }
    @Test
    fun testExtraneousAdjacentOperator() {
        display.screen = "3+"
        val input = "*"
        val expected = "3*"
        assertEquals(expected, display.addOperator(input))
    }
    @Test
    fun testExtraneousSecondDecimal() {
        display.screen = "3.4"
        val input = "."
        val expected = "3.4"
        assertEquals(expected, display.addDecimal(input))
    }
    @Test
    fun testSecondDecimalInSecondNumber() {
        display.screen = "3.4+6"
        val input = "."
        val expected = "3.4+6."
        assertEquals(expected, display.addDecimal(input))
    }
    @Test
    fun testExtraneousSecondDecimalInSecondNumber() {
        display.screen = "3.4+6.8"
        val input = "."
        val expected = "3.4+6.8"
        assertEquals(expected, display.addDecimal(input))
    }
    @Test
    fun testPercentThenOperator() {
        display.screen = "38%"
        val input = "+"
        val expected = "38%+"
        assertEquals(expected, display.addOperator(input))
    }
    @Test
    fun testMinusPlusUnaryOperator() {
        display.screen = "35"
        val input = "m"
        val expected = "-35"
        assertEquals(expected, display.addOperator(input))
    }
    @Test
    fun testDecimalRightAfterPercentSign() {
        display.screen = "38%"
        val input = "."
        val expected = "38%"
        assertEquals(expected, display.addDecimal(input))
    }
    @Test
    fun testMissingFirstOperator() {
        display.screen = "0"
        val input = "/"
        val expected = "0"
        assertEquals(expected, display.addDecimal(input))
    }
    @Test
    fun testLeadingZerosInSecondOperator() {
        display.screen = "8-0"
        val input = "0"
        val expected = "8-0"
        assertEquals(expected, display.addDecimal(input))
    }
}
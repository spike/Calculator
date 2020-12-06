package com.calculator.calc

import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class BufferUnitTestSuite {
    private lateinit var buffer: Buffer

    @Before
    fun setUp(): Unit {
        buffer = Buffer()
    }
    @After
    fun tearDown(): Unit {
        buffer.clear()
    }

    @Test
    fun testBackspace() {
        buffer.formula = "12345+5"
        val expected = "12345+"
        assertEquals(expected, buffer.backspace())
    }
    @Test
    fun testBackspaceZero() {
        buffer.formula ="0"
        val expected = "0"
        assertEquals(expected, buffer.backspace())
    }
    @Test
    fun testClear() {
        buffer.formula = "1234567"
        val expected = "0"
        assertEquals(expected, buffer.clear())
    }
    @Test
    fun testAddDigit() {
        buffer.formula = "90+4"
        val input = "8"
        val expected = "90+48"
        assertEquals(expected, buffer.addDigit(input))
    }
    @Test
    fun testAddOperator() {
        buffer.formula = "9"
        val input = "+"
        val expected = "9+"
        assertEquals(expected, buffer.addOperator(input))
    }
    @Test
    fun testAddPercent() {
        buffer.formula = "2275"
        val input = "%"
        val expected = "2275%"
        assertEquals(expected, buffer.addOperator(input))
    }
    @Test
    fun testAddUpfrontZeros() {
        buffer.formula = "0"
        val input = "0"
        val expected = "0"
        assertEquals(expected, buffer.addDigit(input))
    }
    @Test
    fun testExtraneousAdjacentOperator() {
        buffer.formula = "3+"
        val input = "*"
        val expected = "3*"
        assertEquals(expected, buffer.addOperator(input))
    }
    @Test
    fun testAllowedAdjacentMinusOperator() {
        buffer.formula = "3*"
        val input = "-"
        val expected = "3*-"
        assertEquals(expected, buffer.addOperator(input))
    }
    @Test
    fun testExtraneousSecondDecimal() {
        buffer.formula = "3.4"
        val input = "."
        val expected = "3.4"
        assertEquals(expected, buffer.addDecimal())
    }
    @Test
    fun testSecondDecimalInSecondNumber() {
        buffer.formula = "3.4+6"
        val input = "."
        val expected = "3.4+6."
        assertEquals(expected, buffer.addDecimal())
    }
    @Test
    fun testExtraneousSecondDecimalInSecondNumber() {
        buffer.formula = "3.4+6.8"
        val input = "."
        val expected = "3.4+6.8"
        assertEquals(expected, buffer.addDecimal())
    }
    @Test
    fun testPercentThenOperator() {
        buffer.formula = "38%"
        val input = "+"
        val expected = "38%+"
        assertEquals(expected, buffer.addOperator(input))
    }
    @Test
    fun testDecimalRightAfterPercentSign() {
        buffer.formula = "38%"
        val input = "."
        val expected = "38%0."
        assertEquals(expected, buffer.addDecimal())
    }
    @Test
    fun testMissingFirstOperator() {
        buffer.formula = "0"
        val input = "/"
        val expected = "0/"
        assertEquals(expected, buffer.addOperator(input))
    }
    @Test
    fun testLeadingZerosInSecondOperator() {
        buffer.formula = "8-0"
        val input = "0"
        val expected = "8-0"
        assertEquals(expected, buffer.addDigit(input))
    }
}
package com.calculator.calc

import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class DisplayBufferUnitTestSuite {
    private lateinit var displayBuffer: DisplayBuffer

    @Before
    fun setUp(): Unit {
        displayBuffer = DisplayBuffer()
    }
    @After
    fun tearDown(): Unit {
        displayBuffer.clear()
    }
    @Test
    fun testBackspace() {
        displayBuffer.formula = "12345+5"
        val expected = "12345+"
        assertEquals(expected, displayBuffer.backspace())
    }
    @Test
    fun testBackspaceZero() {
        displayBuffer.formula ="0"
        val expected = "0"
        assertEquals(expected, displayBuffer.backspace())
    }
    @Test
    fun testClear() {
        displayBuffer.formula = "1234567"
        val expected = "0"
        assertEquals(expected, displayBuffer.clear())
    }
    @Test
    fun testAddDigit() {
        displayBuffer.formula = "90+4"
        val input = "8"
        val expected = "90+48"
        assertEquals(expected, displayBuffer.addDigit(input))
    }
    @Test
    fun testAddOperator() {
        displayBuffer.formula = "9"
        val input = "+"
        val expected = "9+"
        assertEquals(expected, displayBuffer.addOperator(input))
    }
/*    @Test
    fun testAddSecondOperator() {
        displayBuffer.formula = "9+1"
        displayBuffer.previous = ""
        val input = "*"
        val expectedFormula = "10*"
        val expectedPrevious = "(9+1)*"
        assertEquals(expectedFormula, displayBuffer.addOperator(input))
        assertEquals(expectedPrevious, displayBuffer.previous)
    }*/
    @Test
    fun testAddPercent() {
        displayBuffer.formula = "2275"
        val input = "%"
        val expected = "2275%"
        assertEquals(expected, displayBuffer.addOperator(input))
    }
    @Test
    fun testAddUpfrontZeros() {
        displayBuffer.formula = "0"
        val input = "0"
        val expected = "0"
        assertEquals(expected, displayBuffer.addDigit(input))
    }
    @Test
    fun testExtraneousAdjacentOperator() {
        displayBuffer.formula = "3+"
        val input = "*"
        val expected = "3*"
        assertEquals(expected, displayBuffer.addOperator(input))
    }
    @Test
    fun testExtraneousSecondDecimal() {
        displayBuffer.formula = "3.4"
        val input = "."
        val expected = "3.4"
        assertEquals(expected, displayBuffer.addDecimal())
    }
    @Test
    fun testExtraneousSecondDecimalInSecondOperand() {
        displayBuffer.formula = "35.2*1.5"
        val input = "."
        val expected = "35.2*1.5"
        assertEquals(expected, displayBuffer.addDecimal())
    }
    @Test
    fun testSecondDecimalInSecondNumber() {
        displayBuffer.formula = "3.4+6"
        val input = "."
        val expected = "3.4+6."
        assertEquals(expected, displayBuffer.addDecimal())
    }
    @Test
    fun testMissingFirstOperator() {
        displayBuffer.formula = "0"
        val input = "/"
        val expected = "0/"
        assertEquals(expected, displayBuffer.addOperator(input))
    }
    @Test
    fun testLeadingZerosInSecondOperator() {
        displayBuffer.formula = "5-0"
        val input = "0"
        val expected = "5-0"
        assertEquals(expected, displayBuffer.addDigit(input))
    }
    @Test
    fun testExtraneousOperator() {
        displayBuffer.formula = "8-"
        val input = "-"
        val expected = "8-"
        assertEquals(expected, displayBuffer.addOperator(input))
    }
    @Test
    fun testDecimal() {
        displayBuffer.formula = "0"
        val input = "."
        val expected = "0."
        assertEquals(expected, displayBuffer.addDecimal())
    }
}
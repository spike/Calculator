package com.calculator.calc

import org.junit.After
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class DisplayBufferUnitTestSuite {
    private lateinit var displayBuffer: DisplayBuffer
    private lateinit var stackBuffer: StackBuffer

    @Before
    fun setUp(): Unit {
        displayBuffer = DisplayBuffer()
        stackBuffer = StackBuffer()
    }
    @After
    fun tearDown(): Unit {
        displayBuffer.clear()
        stackBuffer.clear()
    }
    @Test
    fun testBackspace() {
        displayBuffer.formula = "12345+5"
        stackBuffer.refill("12345+5")
        val expected = Pair(displayBuffer.formula, "12345+")
        assertEquals(expected, displayBuffer.backspace())
    }
    @Test
    fun testBackspaceZero() {
        displayBuffer.formula ="0"
        val expected = Pair("", "0")
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
        val expected = Pair("","9+")
        assertEquals(expected, displayBuffer.addOperator(input))
    }
    @Test
    fun testSecondAddOperator() {
        displayBuffer.formula = "9*2"
        val input = "+"
        val expected = Pair("9*2+", "18+")
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
        val expected = Pair("", "3*")
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
        val expected = Pair("", "0/")
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
        val expected = Pair("", "8-")
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
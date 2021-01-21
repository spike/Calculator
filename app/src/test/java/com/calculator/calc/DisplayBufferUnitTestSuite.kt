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
        val formula = "12345+5"
        displayBuffer.stack.refill(formula)
        val expected = Pair(formula, "12345+")
        assertEquals(expected, displayBuffer.backspace())
    }
    @Test
    fun testBackspaceZero() {
        val formula ="0"
        displayBuffer.stack.refill(formula)
        val expected = Pair("", formula)
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
        displayBuffer.stack.refill("90+4")
        val input = "8"
        val expected = "90+48"
        assertEquals(expected, displayBuffer.addDigit(input))
    }
    @Test
    fun testAddOperator() {
        val formula = "9"
        displayBuffer.stack.refill(formula)
        val input = "+"
        val expected = Pair("","9+")
        assertEquals(expected, displayBuffer.addOperator(input))
    }
    @Test
    fun testSecondAddOperator() {
        val formula = "9*2"
        displayBuffer.stack.refill(formula)
        val input = "+"
        val expected = Pair("9*2+", "18+")
        assertEquals(expected, displayBuffer.addOperator(input))
    }
    @Test
    fun testAddUpfrontZeros() {
        // displayBuffer.formula = "0"
        displayBuffer.stack.refill("0")
        val input = "0"
        val expected = "0"
        assertEquals(expected, displayBuffer.addDigit(input))
    }
    @Test
    fun testExtraneousAdjacentOperator() {
        val formula = "3+"
        displayBuffer.stack.refill(formula)
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
        val formula = "0"
        displayBuffer.stack.refill(formula)
        val input = "/"
        val expected = Pair("", "0/")
        assertEquals(expected, displayBuffer.addOperator(input))
    }
    @Test
    fun testLeadingZerosInSecondOperator() {
        displayBuffer.stack.refill("5-0")
        val input = "0"
        val expected = "5-0"
        assertEquals(expected, displayBuffer.addDigit(input))
    }
    @Test
    fun testExtraneousOperator() {
        val formula = "8-"
        displayBuffer.stack.refill(formula)
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
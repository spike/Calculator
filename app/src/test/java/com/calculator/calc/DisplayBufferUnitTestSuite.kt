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
        val starting = "12345+5"
        displayBuffer.stack.refill(starting)
        val expected = Pair(starting, "12345+")
        assertEquals(expected, displayBuffer.backspace())
    }
    @Test
    fun testBackspaceZero() {
        val starting ="0"
        displayBuffer.stack.refill(starting)
        val expected = Pair("", starting)
        assertEquals(expected, displayBuffer.backspace())
    }
    @Test
    fun testClear() {
        val starting = "1234567"
        displayBuffer.stack.refill(starting)
        val expected = "0"
        assertEquals(expected, displayBuffer.clear())
    }
    @Test
    fun testAddUpfrontZeros() {
        val starting = "0"
        displayBuffer.stack.refill(starting)
        val input = '0'
        val expected = starting
        assertEquals(expected, displayBuffer.addDigit(input))
    }
    @Test
    fun testExtraneousAdjacentOperator() {
        val starting = "3+"
        displayBuffer.stack.refill(starting)
        displayBuffer.stackOfNums.push("3")
        displayBuffer.stackOfOperators.push('+')
        val input = '*'
        val expected = Pair("", "3*")
        assertEquals(expected, displayBuffer.addOperator(input))
    }
    @Test
    fun testExtraneousSecondDecimal() {
        val starting = "3.4"
        displayBuffer.stack.refill(starting)
        val input = "."
        val expected = "3.4"
        assertEquals(expected, displayBuffer.addDecimal())
    }
    @Test
    fun testExtraneousSecondDecimalInSecondOperand() {
        val starting = "35.2*1.5"
        displayBuffer.stack.refill(starting)
        val input = "."
        val expected = "35.2*1.5"
        assertEquals(expected, displayBuffer.addDecimal())
    }
    @Test
    fun testSecondDecimalInSecondNumber() {
        val starting = "3.4+6"
        displayBuffer.stack.refill(starting)
        val input = "."
        val expected = "3.4+6."
        assertEquals(expected, displayBuffer.addDecimal())
    }
/*    @Test
    fun testMissingFirstOperator() {
        val starting = "0"
        displayBuffer.stack.refill(starting)
        val input = '/'
        val expected = Pair("", "0/")
        assertEquals(expected, displayBuffer.addOperator(input))
    }*/
    @Test
    fun testLeadingZerosInSecondOperator() {
        val starting = "5-0"
        displayBuffer.stack.refill(starting)
        displayBuffer.stackOfNums.push("5")
        displayBuffer.stackOfOperators.push('-')
        displayBuffer.stackOfNums.push("0")
        val input = '0'
        val expected = starting
        assertEquals(expected, displayBuffer.addDigit(input))
    }
    @Test
    fun testExtraneousOperator() {
        val starting = "8-"
        displayBuffer.stack.refill(starting)
        displayBuffer.stackOfNums.push("8")
        displayBuffer.stackOfOperators.push('-')
        val input = '-'
        val expected = Pair("", "8-")
        assertEquals(expected, displayBuffer.addOperator(input))
    }
    @Test
    fun testDecimal() {
        val starting = "0"
        displayBuffer.stack.refill(starting)
        val input = "."
        val expected = "0."
        assertEquals(expected, displayBuffer.addDecimal())
    }

    @Test
    fun testAddDigit() {
        val starting = "90+4"
        displayBuffer.stack.refill(starting)
        val input = '8'
        val expected = "90+48"
        assertEquals(expected, displayBuffer.addDigit(input))
    }
    @Test
    fun testAddPlusOperator2() {
        val starting = "3*5"
        displayBuffer.stack.refill(starting)
        displayBuffer.stackOfNums.push("3")
        displayBuffer.stackOfNums.push("5")
        displayBuffer.stackOfOperators.push('*')

        // displayBuffer.stackOfNums = [3, 5]
        // displayBuffer.stackOfOperators = ['*']
        val input = '+'
        val expected = Pair("3*5+", "15+")
        assertEquals(expected, displayBuffer.addOperator(input))
    }
    @Test
    fun testAddOperator() {
        val starting = "9"
        displayBuffer.stack.refill(starting)
        displayBuffer.stackOfNums.push("9")
        val input = '+'
        val expected = Pair("","9+")
        assertEquals(expected, displayBuffer.addOperator(input))
    }
    @Test
    fun testSecondAddOperator() {
        val starting = "9*2"
        displayBuffer.stack.refill(starting)
        displayBuffer.stackOfNums.push("9")
        displayBuffer.stackOfNums.push("2")
        displayBuffer.stackOfOperators.push('*')
        val input = '+'
        val expected = Pair("9*2+", "18+")
        assertEquals(expected, displayBuffer.addOperator(input))
    }
}
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
        assertEquals(expected, display.addCharacter(input))
    }
    @Test
    fun testAddPercent() {
        display.screen = "2275"
        val input = "%"
        val expected = "2275%"
        assertEquals(expected, display.addCharacter(input))
    }
    @Test
    fun testAddUpfrontZeros() {
        display.screen = "0"
        val input = "0"
        val expected = "0"
        assertEquals(expected, display.addCharacter(input))
    }


}
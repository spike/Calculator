package com.calculator.calc

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class DisplayUnitTestSuite {
    private lateinit var display: Display

    @Before
    fun setUp(): Unit {
        display = Display()
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
        val input ="90+40"
        val expected = "0"
        assertEquals(expected, display.clear(input))
    }


}
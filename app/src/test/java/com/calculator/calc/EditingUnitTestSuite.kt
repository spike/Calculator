package com.calculator.calc

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.After

class EditingUnitTestSuite {
    private lateinit var e: Editing

    @Before
    fun setUp(): Unit {
        e = Editing()
    }

    @Test
    fun testBackspace() {
        val input ="12345+5"
        val expected = listOf("12345+")
        assertEquals(expected, e.backspace(input))
    }
}
package com.calculator.calc

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.After

class EngineUnitTestSuite {
    private lateinit var engine: Engine

    @Before
    fun setUp(): Unit {
        engine = Engine()
    }
    @Test
    fun testCalculateAddition() {
        val input ="12345+5="
        val expected = "12350"
        assertEquals(expected, engine.calculate())
    }
    @Test
    fun testCalculateMultiplication() {
        val input = "2*3="
        val expected = "6"
        assertEquals(expected, engine.calculate())
    }
    @Test
    fun testDecimalResult() {
        val input = "37/100="
        val expected = "0.37"
        assertEquals(expected, engine.calculate())
    }

    @After
    fun tearDown() {
        engine.clear()
    }
}


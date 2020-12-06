package com.calculator.calc

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.After

class EngineUnitTestSuite {
    private lateinit var engine: Engine
    private lateinit var display: Display

    @Before
    fun setUp(): Unit {
        engine = Engine()
        display = Display()
    }
    @After
    fun tearDown() {
        display.clear()
    }
    @Test
    fun testCalculateAddition() {
        display.formula ="45+5"
        val expected = "50"
        assertEquals(expected, engine.calculate(display.formula))
    }
    @Test
    fun testCalculateMultiplication() {
        display.formula = "2*3"
        val expected = "6"
        assertEquals(expected, engine.calculate(display.formula))
    }
    @Test
    fun testDecimalResult() {
        display.formula = "37/100"
        val expected = "0.37"
        assertEquals(expected, engine.calculate(display.formula))
    }
    @Test
    fun testPercent() {
        display.formula = "75"
        val expected = "0.75"
        assertEquals(expected, engine.addPercentSign(display.formula))
    }
    @Test
    fun testNegate() {
        display.formula = "5"
        val expected = "-5"
        assertEquals(expected, engine.negate(display.formula))
    }
}


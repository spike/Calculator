package com.calculator.calc

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.After

class EngineUnitTestSuite {
    private lateinit var engine: Engine
    private lateinit var buffer: Buffer

    @Before
    fun setUp(): Unit {
        engine = Engine()
        buffer = Buffer()
    }
    @After
    fun tearDown() {
        buffer.clear()
    }
    @Test
    fun testCalculateAddition() {
        buffer.formula ="45+5"
        val expected = "50"
        assertEquals(expected, engine.calculate(buffer.formula))
    }
    @Test
    fun testCalculateMultiplication() {
        buffer.formula = "2*3"
        val expected = "6"
        assertEquals(expected, engine.calculate(buffer.formula))
    }
    @Test
    fun testDecimalResult() {
        buffer.formula = "37/100"
        val expected = "0.37"
        assertEquals(expected, engine.calculate(buffer.formula))
    }
    @Test
    fun testPercent() {
        buffer.formula = "75"
        val expected = "0.75"
        assertEquals(expected, engine.calculatePercentage(buffer.formula))
    }
    @Test
    fun testPercentAsSecondOperand() {
        buffer.formula = "20*62"
        val expected = "20*0.62"
        assertEquals(expected, engine.calculatePercentage(buffer.formula))
    }
    @Test
    fun testNegate() {
        buffer.formula = "5"
        val expected = "-5"
        assertEquals(expected, engine.calculateNegation(buffer.formula))
    }
    @Test
    fun testNegateTheOtherWay() {
        buffer.formula = "-2"
        val expected = "2"
        assertEquals(expected, engine.calculateNegation(buffer.formula))
    }
}


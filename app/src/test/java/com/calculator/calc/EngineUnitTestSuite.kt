package com.calculator.calc

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.After

class EngineUnitTestSuite {
    private lateinit var engine: Engine
    private lateinit var displayBuffer: DisplayBuffer

    @Before
    fun setUp(): Unit {
        engine = Engine()
        displayBuffer = DisplayBuffer()
    }
    @After
    fun tearDown() {
        displayBuffer.clear()
    }
    @Test
    fun testCalculateAddition() {
        displayBuffer.formula ="45+5"
        val expected = Pair("45+5", "50")
        assertEquals(expected, engine.calculate(displayBuffer.formula))
    }
    @Test
    fun testCalculateSingleOperand() {
        displayBuffer.formula ="44.5"
        val expected = Pair("44.5", "44.5")
        assertEquals(expected, engine.calculate(displayBuffer.formula))
    }

    @Test
    fun testCalculateMultiplication() {
        displayBuffer.formula = "2*3"
        val expected = Pair("2*3", "6")
        assertEquals(expected, engine.calculate(displayBuffer.formula))
    }
    @Test
    fun testDecimalResult() {
        displayBuffer.formula = "37/100"
        val expected = Pair("37/100","0.37")
        assertEquals(expected, engine.calculate(displayBuffer.formula))
    }
    @Test
    fun testPercent() {
        displayBuffer.formula = "75"
        val expected = Pair("75%", "0.75")
        assertEquals(expected, engine.calculatePercentage(displayBuffer.formula))
    }
    @Test
    fun testPercentWithDecimal() {
        displayBuffer.formula = "0.72"
        val expected = Pair("0.72%", "0.0072")
        assertEquals(expected, engine.calculatePercentage(displayBuffer.formula))
    }
    @Test
    fun testPercentAsSecondOperand() {
        displayBuffer.formula = "20*62"
        val expected = Pair("20*62%", "20*0.62")
        assertEquals(expected, engine.calculatePercentage(displayBuffer.formula))
    }
    @Test
    fun testNegate() {
        displayBuffer.formula = "5"
        val expected = "-5"
        assertEquals(expected, engine.calculateNegation(displayBuffer.formula))
    }
    @Test
    fun testNegateNegative() {
        displayBuffer.formula = "-5.8"
        val expected = "5.8"
        assertEquals(expected, engine.calculateNegation(displayBuffer.formula))
    }
    @Test
    fun testNegateDecimal() {
        displayBuffer.formula = "5.1009"
        val expected = "-5.1009"
        assertEquals(expected, engine.calculateNegation(displayBuffer.formula))
    }
    @Test
    fun testNegateTheOtherWay() {
        displayBuffer.formula = "-2"
        val expected = "2"
        assertEquals(expected, engine.calculateNegation(displayBuffer.formula))
    }

}


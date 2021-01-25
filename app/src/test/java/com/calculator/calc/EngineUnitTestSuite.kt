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
        val starting ="45+5"
        displayBuffer.stack.refill(starting)
        val expected = Pair("45+5", "50")
        assertEquals(expected, engine.calculate(displayBuffer.stack.toString()))
    }
    @Test
    fun testCalculateSingleOperand() {
        val starting ="44.5"
        displayBuffer.stack.refill(starting)
        val expected = Pair("44.5", starting)
        assertEquals(expected, engine.calculate(displayBuffer.stack.toString()))
    }

    @Test
    fun testCalculateMultiplication() {
        val starting = "2*3"
        displayBuffer.stack.refill(starting)
        val expected = Pair("2*3", "6")
        assertEquals(expected, engine.calculate(displayBuffer.stack.toString()))
    }
    @Test
    fun testDecimalResult() {
        val starting = "37/100"
        displayBuffer.stack.refill(starting)
        val expected = Pair("37/100","0.37")
        assertEquals(expected, engine.calculate(displayBuffer.stack.toString()))
    }
    @Test
    fun testPercent() {
        val starting = "75"
        displayBuffer.stack.refill(starting)
        val expected = Pair("75%", "0.75")
        assertEquals(expected, engine.calculatePercentage(displayBuffer.stack.toString()))
    }
    @Test
    fun testPercentWithDecimal() {
        val starting = "0.72"
        displayBuffer.stack.refill(starting)
        val expected = Pair("0.72%", "0.0072")
        assertEquals(expected, engine.calculatePercentage(displayBuffer.stack.toString()))
    }
    @Test
    fun testPercentAsSecondOperand() {
        val starting = "20*62"
        displayBuffer.stack.refill(starting)
        val expected = Pair("20*62%", "20*0.62")
        assertEquals(expected, engine.calculatePercentage(displayBuffer.stack.toString()))
    }
    @Test
    fun testNegate() {
        val starting = "5"
        displayBuffer.stack.refill(starting)
        val expected = "-5"
        assertEquals(expected, engine.calculateNegation(displayBuffer.stack.toString()))
    }
    @Test
    fun testNegateNegative() {
        val starting = "-5.8"
        displayBuffer.stack.refill(starting)
        val expected = "5.8"
        assertEquals(expected, engine.calculateNegation(displayBuffer.stack.toString()))
    }
    @Test
    fun testNegateTheOtherWay() {
        val starting = "-2"
        displayBuffer.stack.refill(starting)
        val expected = "2"
        assertEquals(expected, engine.calculateNegation(displayBuffer.stack.toString()))
    }
    @Test
    fun testNegateDecimal() {
        val starting = "5.1009"
        displayBuffer.stack.refill(starting)
        val expected = "-5.1009"
        assertEquals(expected, engine.calculateNegation(displayBuffer.stack.toString()))
    }
    @Test
    fun testNegateAnExpressionWithAMinusOperator() {
        val starting = "2-5"
        displayBuffer.stack.refill(starting)
        val expected = "2+5"
        assertEquals(expected, engine.calculateNegation(displayBuffer.stack.toString()))
    }
    @Test
    fun testNegateAnExpressionWithPlusAndMinusOperator() {
        val starting = "2+-3"
        displayBuffer.stack.refill(starting)
        val expected = "2+3"
        assertEquals(expected, engine.calculateNegation(displayBuffer.stack.toString()))
    }
    @Test
    fun testNegateDivisionWithPlusAndMinusOperator() {
        val starting = "2÷-3"
        displayBuffer.stack.refill(starting)
        val expected = "2÷3"
        assertEquals(expected, engine.calculateNegation(displayBuffer.stack.toString()))
    }
    @Test
    fun testCalculateCorrectOrderOfPrecedence() {
        val starting = "6-2*3+4"
        displayBuffer.stack.refill(starting)
        val expected = Pair("6-2*3+4", "4")
        assertEquals(expected, engine.calculate(displayBuffer.stack.toString()))
    }
    @Test
    fun testCalculateCorrectOrderOfPrecendenceSimple() {
        val starting = "5+10*3"
        displayBuffer.stack.refill(starting)
        val expected = Pair("5+10*3", "35")
        assertEquals(expected, engine.calculate(displayBuffer.stack.toString()))
    }
    @Test
    fun testCalculateCorrectOrderOfPrecendenceSimpleStack() {
        val starting = "3*5+10"
        displayBuffer.stack.refill(starting)
        val expected = Pair("3*5+10", "25")
        assertEquals(expected, engine.calculate(displayBuffer.stack.toString()))
    }

}


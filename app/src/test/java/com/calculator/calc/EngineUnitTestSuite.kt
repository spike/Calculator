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
        val formula ="45+5"
        displayBuffer.stack.refill(formula)
        val expected = Pair("45+5", "50")
        assertEquals(expected, engine.calculate(displayBuffer.stack.toString()))
    }
    @Test
    fun testCalculateSingleOperand() {
        val formula ="44.5"
        displayBuffer.stack.refill(formula)
        val expected = Pair("44.5", formula)
        assertEquals(expected, engine.calculate(displayBuffer.stack.toString()))
    }

    @Test
    fun testCalculateMultiplication() {
        val formula = "2*3"
        displayBuffer.stack.refill(formula)
        val expected = Pair("2*3", "6")
        assertEquals(expected, engine.calculate(displayBuffer.stack.toString()))
    }
    @Test
    fun testDecimalResult() {
        val formula = "37/100"
        displayBuffer.stack.refill(formula)
        val expected = Pair("37/100","0.37")
        assertEquals(expected, engine.calculate(displayBuffer.stack.toString()))
    }
    @Test
    fun testPercent() {
        val formula = "75"
        displayBuffer.stack.refill(formula)
        val expected = Pair("75%", "0.75")
        assertEquals(expected, engine.calculatePercentage(displayBuffer.stack.toString()))
    }
    @Test
    fun testPercentWithDecimal() {
        val formula = "0.72"
        displayBuffer.stack.refill(formula)
        val expected = Pair("0.72%", "0.0072")
        assertEquals(expected, engine.calculatePercentage(displayBuffer.stack.toString()))
    }
    @Test
    fun testPercentAsSecondOperand() {
        val formula = "20*62"
        displayBuffer.stack.refill(formula)
        val expected = Pair("20*62%", "20*0.62")
        assertEquals(expected, engine.calculatePercentage(displayBuffer.stack.toString()))
    }
    @Test
    fun testNegate() {
        val formula = "5"
        displayBuffer.stack.refill(formula)
        val expected = "-5"
        assertEquals(expected, engine.calculateNegation(displayBuffer.stack.toString()))
    }
    @Test
    fun testNegateNegative() {
        val formula = "-5.8"
        displayBuffer.stack.refill(formula)
        val expected = "5.8"
        assertEquals(expected, engine.calculateNegation(displayBuffer.stack.toString()))
    }
    @Test
    fun testNegateTheOtherWay() {
        val formula = "-2"
        displayBuffer.stack.refill(formula)
        val expected = "2"
        assertEquals(expected, engine.calculateNegation(displayBuffer.stack.toString()))
    }
    @Test
    fun testNegateDecimal() {
        val formula = "5.1009"
        displayBuffer.stack.refill(formula)
        val expected = "-5.1009"
        assertEquals(expected, engine.calculateNegation(displayBuffer.stack.toString()))
    }
    @Test
    fun testNegateAnExpressionWithAMinusOperator() {
        val formula = "2-5"
        displayBuffer.stack.refill(formula)
        val expected = "2+5"
        assertEquals(expected, engine.calculateNegation(displayBuffer.stack.toString()))
    }
    @Test
    fun testNegateAnExpressionWithPlusAndMinusOperator() {
        val formula = "2+-3"
        displayBuffer.stack.refill(formula)
        val expected = "2+3"
        assertEquals(expected, engine.calculateNegation(displayBuffer.stack.toString()))
    }
    @Test
    fun testNegateDivisionWithPlusAndMinusOperator() {
        val formula = "2÷-3"
        displayBuffer.stack.refill(formula)
        val expected = "2÷3"
        assertEquals(expected, engine.calculateNegation(displayBuffer.stack.toString()))
    }
    @Test
    fun testCalculateCorrectOrderOfExecution() {
        val formula = "6-2*3+4"
        displayBuffer.stack.refill(formula)
        val expected = Pair("6-2*3+4", "4")
        assertEquals(expected, engine.calculate(displayBuffer.stack.toString()))
    }

}


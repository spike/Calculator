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
        assertEquals(expected, engine.calculate(input))
    }
    @Test
    fun testCalculateMultiplication() {
        val input = "2*3="
        val expected = "6"
        assertEquals(expected, engine.calculate(input))
    }
    @Test
    fun testDecimalResult() {
        val input = "37/100="
        val expected = "0.37"
        assertEquals(expected, engine.calculate(input))
    }

    @Test
    fun testPercentUnaryOperator() {
        val input = "38%+2="
        val expected = listOf("3", "38", "0.38", "+", "2", "2.38")
        assertEquals(expected, engine.calculate(input))
    }
    @Test
    fun testMinusOrPlusUnaryOperator() {
        val input = "35m+5="
        val expected = listOf("3", "35", "-35", "+", "5", "-30")
        assertEquals(expected, engine.calculate(input))
    }

    @Test
    fun testWithMissingFirstOperand() {
        val input = "/6="
        val expected = listOf("/", "6", "0")
        assertEquals(expected, engine.calculate(input))
    }

    @Test
    fun testMinusBeforeOperator() {
        val input = "-6="
        val expected = listOf("-", "6", "-6")
        assertEquals(expected, engine.calculate(input))
    }

    @Test
    fun testBugMinusOrPlusAfterOperator() {
        val input = "1/m4="
        val expected = listOf("1", "/", "-", "-4", "-0.25")
        assertEquals(expected, engine.calculate(input))
    }

    @Test
    fun testLeadingZerosInSecondOperand() {
        val input = "8-002"
        val expected = listOf("8", "-", "0", "0", "2")
        assertEquals(expected, engine.calculate(input))
    }

    @After
    fun tearDown() {
        engine.clear()
    }
}


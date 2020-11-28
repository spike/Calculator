package com.calculator.calc

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.After

class CalculationsUnitTestSuite {
    private lateinit var c: Calculation

    @Before
    fun setUp(): Unit {
        c = Calculation()
    }
    @Test
    fun testCalculateAddition() {
        val input ="12345+5="
        val expected = listOf("1", "12", "123", "1234", "12345", "+", "5", "12350")
        assertEquals(expected, c.calculate(input))
    }
    @Test
    fun testCalculateMultiplication() {
        val input = "2*3="
        val expected = listOf("2", "*", "3", "6")
        assertEquals(expected, c.calculate(input))
    }
    @Test
    fun testDecimalResult() {
        val input = "37/100="
        val expected = listOf("3", "37", "/", "1", "10", "100", "0.37")
        assertEquals(expected, c.calculate(input))
    }
    @Test
    fun testCalculateExtraneousOperator() {
        val input = "3+*2=="
        val expected = listOf("3", "+", "*", "2", "6", "6")
        assertEquals(expected, c.calculate(input))
    }
    @Test
    fun testCalculateBackspaceAndExtraneousEqualSign() {
        val input = "0567b+2.56-.5=="
        val expected = listOf("0", "5", "56", "567", "56", "+", "2", "2.", "2.5", "2.56",
            "(58.56)-", "0.", "0.5", "58.06", "58.06" )
        assertEquals(expected, c.calculate(input))
    }
    @Test
    fun testCalculateExtraneousDecimalPoints() {
        val input = "1..3.2+0.5+78*1-.82="
        val expected = listOf("1", "1.", "1.", "1.3", "1.3", "1.32", "+", "0", "0.",
            "0.5", "(1.82)+", "7", "78", "(79.82)*", "1", "(79.82)-",
            "0.", "0.8", "0.82", "79")
        assertEquals(expected, c.calculate(input))
    }
    @Test
    fun testPercentUnaryOperator() {
        val input = "38%+2="
        val expected = listOf("3", "38", "0.38", "+", "2", "2.38")
        assertEquals(expected, c.calculate(input))
    }
    @Test
    fun testMinusOrPlusUnaryOperator() {
        val input = "35m+5="
        val expected = listOf("3", "35", "-35", "+", "5", "-30")
        assertEquals(expected, c.calculate(input))
    }

    @Test
    fun testWithMissingFirstOperand() {
        val input = "/6="
        val expected = listOf("/", "6", "0")
        assertEquals(expected, c.calculate(input))
    }

    // test minus or plus when invoked first before an actual number

    // add commas instead of decimal points for Europeans
    // add commas/points after every three digits

    @After
    fun tearDown() {
        c.clear()
    }
}


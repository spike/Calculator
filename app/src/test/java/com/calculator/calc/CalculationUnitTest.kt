package com.calculator.calc

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.After
import java.math.BigDecimal

class CalculationUnitTest {
    private lateinit var c: Calculation
    val a = "0567b+2.56-.5=="
    val b = listOf("0", "5", "56", "567", "56", "+", "2", "2.", "2.5", "2.56",
        "(58.56)-", "0.", "0.5", "58.06", "58.06" )
    val aa = "1..3.2+0.5+78*1-.82="
    val bb = listOf("1", "1.", "1.", "1.3", "1.3", "1.32", "+", "0", "0.",
        "0.5", "(1.82)+", "7", "78", "(79.82)*", "1", "(79.82)-",
        "0.", "0.8", "0.82", "79")
    val aaa ="12345+5="
    val bbb = listOf("1", "12", "123", "1234", "12345", "+", "5", "12350")
    val aaaa = "3+*2=="
    val bbbb = listOf("3", "+", "*", "2", "6", "6")
    val aaaaa = "37/100="
    val bbbbb = listOf("3", "37", "/", "1", "10", "100", "0.37")


    @Test
    fun testCalculateFirstCase() {
        assertEquals(b, c.calculate(a))
    }
    @Test
    fun testCalculateSecondCase() {
        assertEquals(bb, c.calculate(aa))
    }
    @Test
    fun testCalculateThirdCase() {
        assertEquals(bbb, c.calculate(aaa))
    }
    @Test
    fun testCalculateFourthCase() {
        assertEquals(bbbb, c.calculate(aaaa))
    }
    @Test
    fun testRoundingError() {
        assertEquals(bbbbb, c.calculate(aaaaa))
    }
    @Before
    fun setUp(): Unit {
        c = Calculation()
    }
    @After
    fun tearDown() {
        c.clear()
    }
}


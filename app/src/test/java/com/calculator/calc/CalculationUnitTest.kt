package com.calculator.calc

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.After

class CalculationUnitTest {
    private lateinit var c: Calculation
    val a = "0567b++2.56-.5==b"
    val b = listOf("0", "5", "56", "567", "56", "+", "+", "2", "2.", "2.5", "2.56",
        "(58.56)", "0.", "0.5", "58.06", "0" )
    val aa = "1..3.2+0.5-+78*1-.82="
    val bb = listOf("1", "1.", "1.", "1.3", "1.3", "1.32", "+", "0", "0.",
        "0.5", "(1.82)-", "(1.82)+", "7", "78", "(79.82)*", "1", "(79.82)-",
        "0", "0.8", "0.82", "79")
    val aaa ="12345+5="
    val bbb = listOf("1", "12", "123", "1234", "12345", "+", "5", "12350")
    val aaaa = "3+2+10=="
    val bbbb = listOf("3", "+", "2", "(5)+", "1", "10", "15", "15")

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

/*    fun compute(opOne: String, opTwo: String, sign: String): String {
        c.loadOperand(opOne)
        c.loadOperator(sign)
        c.loadOperand(opTwo)
        return c.getResult()
    }*/
    @Before
    fun setUp(): Unit {
        c = Calculation()
    }
   // @Test
    fun testSubtraction() {
        //assertEquals("123400", compute("123456", "56", "-"))
    }
   // @Test
    fun testAddition() {
       // assertEquals("123496", compute("123456", "40", "+"))
    }
   // @Test
    fun testDivision() {
        //assertEquals("5", compute("20", "4", "/"))
    }
   // @Test
    fun testMultiplication() {
        //assertEquals("250", compute("25", "10", "*"))
    }
    @After
    fun tearDown(): Unit {
       // c.clear()
    }
}


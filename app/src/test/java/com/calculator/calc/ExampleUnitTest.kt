package com.calculator.calc

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun test_key_typed() {
        assertEquals(7.0, keyTyped('7'))
    }

    fun keyTyped(c: Char): Double {
        return when (c) {
            '7' -> 7.0
            else -> 0.0
        }
    }
}

class StackWithList {
    val elements: MutableList<Any> = mutableListOf()

    fun isEmpty() = elements.isEmpty()
    fun size() = elements.size

    fun push(item: Any) = elements.add(item)
    fun pop(): Any? {
        val item = elements.lastOrNull()
        if (!isEmpty()){
            elements.removeAt(elements.size -1)
        }
        return item
    }
    fun peek(): Any? = elements.lastOrNull()

    override fun toString(): String = elements.toString()
}

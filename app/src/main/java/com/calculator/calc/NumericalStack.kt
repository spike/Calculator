package com.calculator.calc

import java.util.*

class NumericalStack {
    var stack: Stack<String> = Stack<String>()

    fun push(value: String) {
        stack.push(value)
    }
    fun pop(): String {
        return stack.pop()
    }

    fun peek(): String {
        return stack.peek()
    }

    fun isEmpty(): Boolean {
        return stack.isEmpty()
    }

    fun size(): Int {
        return stack.size
    }
    fun clear() {
        stack.clear()
    }

    override fun toString(): String {
        var result = ""
        for (e in stack) {
            result += e
        }
        return result
    }

}

package com.calculator.calc

import java.util.*

class OperandStack {
    var stack: Stack<Double> = Stack<Double>()

    fun push(item: Double) {
        stack.push(item)
    }
    fun pop(): Double {
        return stack.pop()
    }

    fun peek(): Double {
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
        stack.push(0.0)
    }

    override fun toString(): String {
        var result = ""
        for (e in stack) {
            result += e.toString()
        }
        return result
    }

}

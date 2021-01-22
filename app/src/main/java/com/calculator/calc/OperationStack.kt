package com.calculator.calc

import java.util.*

class OperationStack {
    var stack: Stack<Char> = Stack<Char>()

    fun push(operator: Char) {
        stack.push(operator)
    }
    fun pop(): Char {
        return stack.pop()
    }

    fun peek(): Char {
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
        stack.push('0')
    }
    fun refill(s: String) {
        for (e in s) {
            stack.push(e)
        }
    }
    override fun toString(): String {
        var result = ""
        for (e in stack) {
            result += e
        }
        return result
    }

}

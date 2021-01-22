package com.calculator.calc

import java.util.*

class OperandStack {
    var stack: Stack<String> = Stack<String>()

    fun push(item: String) {
        stack.push(item)
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
        stack.push("0")
    }

    override fun toString(): String {
        var result = ""
        for (e in stack) {
            result += e
        }
        return result
    }

}

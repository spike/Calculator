package com.calculator.calc

import java.util.*

class StackBuffer {
    var stack: Stack<Char> = Stack<Char>()

    fun push(item: Char) {
        stack.push(item)
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
    }
    fun refill(s: String) {
        for (e in s) {
            stack.push(e)
        }
    }
    fun String.toString(): String {
        var result = ""
        for (e in stack) {
            result += e
        }
        return result
    }

}

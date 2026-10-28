package com.example.calculator

/** Pure Kotlin calculator logic, so it can be unit-tested on the JVM. */
class Calculator {
    fun add(a: Double, b: Double): Double = a + b
    fun subtract(a: Double, b: Double): Double = a - b
    fun multiply(a: Double, b: Double): Double = a * b

    fun divide(a: Double, b: Double): Double {
        require(b != 0.0) { "Cannot divide by zero" }
        return a / b
    }
}

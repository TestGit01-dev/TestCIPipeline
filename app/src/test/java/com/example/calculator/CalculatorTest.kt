package com.example.calculator

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorTest {

    @Test
    fun add_returnsSumOfTwoNumbers() {
        assertEquals(5.0, Calculator().add(2.0, 3.0), 0.0001)
    }
}

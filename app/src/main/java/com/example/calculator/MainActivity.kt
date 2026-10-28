package com.example.calculator

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : Activity() {

    private val calculator = Calculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputA = findViewById<EditText>(R.id.input_a)
        val inputB = findViewById<EditText>(R.id.input_b)
        val result = findViewById<TextView>(R.id.result)

        fun compute(operation: (Double, Double) -> Double) {
            val a = inputA.text.toString().toDoubleOrNull()
            val b = inputB.text.toString().toDoubleOrNull()
            if (a == null || b == null) {
                result.setText(R.string.error_invalid_input)
                return
            }
            try {
                result.text = operation(a, b).toString()
            } catch (e: IllegalArgumentException) {
                //Display the error message in the result TextView.
                result.text = e.toString()
            }
        }
        // Set up button click listeners for each operation
        findViewById<Button>(R.id.btn_add).setOnClickListener { compute(calculator::add) }
        findViewById<Button>(R.id.btn_subtract).setOnClickListener { compute(calculator::subtract) }
        findViewById<Button>(R.id.btn_multiply).setOnClickListener { compute(calculator::multiply) }
        findViewById<Button>(R.id.btn_divide).setOnClickListener { compute(calculator::divide) }
    }
}

package com.example.lab1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btn_mult: Button
    private lateinit var btn_del: Button
    private lateinit var btn_min: Button
    private lateinit var btn_slo: Button
    private lateinit var btn_equal: Button
    private lateinit var btn_clear: Button
    private lateinit var texting: EditText
    private lateinit var oper: TextView
    private lateinit var result: TextView

    private var firstNumber: Double? = null
    private var currentOp: String = ""
    private var secondNumber: Double? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btn_mult = findViewById(R.id.button_mult)
        btn_del = findViewById(R.id.button_del)
        btn_min = findViewById(R.id.button_min)
        btn_slo = findViewById(R.id.button_slo)
        btn_equal = findViewById(R.id.button_rav)
        btn_clear = findViewById(R.id.buttonAC)
        texting = findViewById(R.id.editTextText)
        oper = findViewById(R.id.textView1)
        result = findViewById(R.id.textView2)

        btn_del.setOnClickListener { operationClicked("/") }
        btn_min.setOnClickListener { operationClicked("-") }
        btn_slo.setOnClickListener { operationClicked("+") }
        btn_mult.setOnClickListener { operationClicked("*") }


        btn_equal.setOnClickListener {
            val input = texting.text.toString()
            if (input.isNotEmpty()) {
                secondNumber = input.toDouble()
                calculate()
            }
        }


        btn_clear.setOnClickListener {
            firstNumber = null
            secondNumber = null
            currentOp = ""
            oper.text = ""
            result.text = ""
            texting.setText("")
        }
    }

    private fun operationClicked(op: String) {
        val input = texting.text.toString()


        if (firstNumber == null && input.isNotEmpty()) {
            firstNumber = input.toDouble()
            texting.setText("")
        }


        currentOp = op
        oper.text = op
    }

    private fun calculate() {
        if (firstNumber != null && secondNumber != null && currentOp.isNotEmpty()) {
            val a = firstNumber!!
            val b = secondNumber!!
            var res = 0.0
            when (currentOp) {
                "+" -> res = a + b
                "-" -> res = a - b
                "*" -> res = a * b
                "/" -> if (b != 0.0) res = a / b else {
                    result.text = "Ошибка"
                    return
                }
            }
            result.text = res.toString()


            firstNumber = res
            secondNumber = null
            texting.setText("")
        }
    }
}

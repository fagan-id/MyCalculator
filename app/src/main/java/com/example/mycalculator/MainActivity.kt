package com.example.mycalculator

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mycalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val hasilOperasi = ArrayList<String>()

        fun gabungkanElement() {
            binding.textHasil.text = hasilOperasi.joinToString(separator = "")
        }

//        Input Angka
        binding.s1.setOnClickListener {
            hasilOperasi.add("1")
            gabungkanElement()

        }
        binding.s2.setOnClickListener {
            hasilOperasi.add("2")
            gabungkanElement()
        }
        binding.s3.setOnClickListener {
            hasilOperasi.add("3")
            gabungkanElement()
        }
        binding.s4.setOnClickListener {
            hasilOperasi.add("4")
            gabungkanElement()
        }
        binding.s5.setOnClickListener {
            hasilOperasi.add("5")
            gabungkanElement()
        }
        binding.s6.setOnClickListener {
            hasilOperasi.add("6")
            gabungkanElement()
        }
        binding.s7.setOnClickListener {
            hasilOperasi.add("7")
            gabungkanElement()
        }
        binding.s8.setOnClickListener {
            hasilOperasi.add("8")
            gabungkanElement()
        }
        binding.s9.setOnClickListener {
            hasilOperasi.add("9")
            gabungkanElement()
        }
        binding.s0.setOnClickListener {
            hasilOperasi.add("0")
            gabungkanElement()
        }

//        Logic Operasi
        val operasi = arrayListOf("+", "-", "*", "/")

        binding.sPlus.setOnClickListener {
            val listTerakhir = hasilOperasi.last()
            if (!operasi.contains(listTerakhir)) {
                hasilOperasi.add("+")
            }
            gabungkanElement()
        }
        binding.sMinus.setOnClickListener {
            val listTerakhir = hasilOperasi.last()
            if (!operasi.contains(listTerakhir)) {
                hasilOperasi.add("-")
            }
            gabungkanElement()
        }
        binding.sKali.setOnClickListener {
            val listTerakhir = hasilOperasi.last()
            if (!operasi.contains(listTerakhir)) {
                hasilOperasi.add("*")
            }
            gabungkanElement()
        }
        binding.sBagi.setOnClickListener {
            val listTerakhir = hasilOperasi.last()
            if (!operasi.contains(listTerakhir)) {
                hasilOperasi.add("/")
            }
            gabungkanElement()
        }

        // Menghapus menggunakan AC
        binding.sDelete.setOnClickListener {
            hasilOperasi.clear()
            binding.textHasil.text = "0"
            binding.textDisplay.text = "0"
            Toast.makeText(this,"Calculator Cleared!",Toast.LENGTH_SHORT).show()
        }

        // Menghitung Hasil
        binding.sHasil.setOnClickListener {
            var numbers = mutableListOf<Double>()
            var operators = mutableListOf<Char>()

            var currentNumber = StringBuilder()

            // First, parse the expression into numbers and operators
            for (element in hasilOperasi) {
                if (element.length == 1 && element[0] in setOf('+', '-', '*', '/')) {
                    numbers.add(currentNumber.toString().toDouble())
                    operators.add(element[0])  // Convert String to Char
                    currentNumber = StringBuilder() // Reset for the next number
                } else {
                    currentNumber.append(element)
                }
            }

            // Add the last number to the list
            numbers.add(currentNumber.toString().toDouble())

            // Now handle the multiplication and division first
            var index = 0
            while (index < operators.size) {
                when (operators[index]) {
                    '*' -> {
                        val result = numbers[index] * numbers[index + 1]
                        numbers[index] = result // Replace the number at the current index
                        numbers.removeAt(index + 1) // Remove the next number
                        operators.removeAt(index) // Remove the operator
                    }
                    '/' -> {
                        if (numbers[index + 1] == 0.0) {
                            throw ArithmeticException("Division by zero")
                        } else {
                            val result = numbers[index] / numbers[index + 1]
                            numbers[index] = result // Replace the number at the current index
                            numbers.removeAt(index + 1) // Remove the next number
                            operators.removeAt(index) // Remove the operator
                        }
                    }
                    else -> index++ // Move to the next operator
                }
            }

            // Now handle the addition and subtraction
            var result = numbers[0]
            for (i in operators.indices) {
                when (operators[i]) {
                    '+' -> result += numbers[i + 1]
                    '-' -> result -= numbers[i + 1]
                }
            }

            // Display the result
            binding.textDisplay.text = result.toString()
        }
        binding.arrowDelete.setOnClickListener {
            if (hasilOperasi.isNotEmpty()) {
                hasilOperasi.removeAt(hasilOperasi.size - 1)
            }
            gabungkanElement()
        }
        }
}
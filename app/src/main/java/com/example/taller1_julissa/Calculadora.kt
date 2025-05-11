package com.example.taller1_julissa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taller1_julissa.ui.theme.Taller1_julissaTheme

class Calculadora : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Taller1_julissaTheme {
                CalculatorScreen(onExit = { finish() })
            }
        }
    }
}

@Composable
fun CalculatorScreen(onExit: () -> Unit) {
    var number1 by remember { mutableStateOf("") }
    var number2 by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(value = number1, onValueChange = { number1 = it }, label = { Text("Número 1") })
        TextField(value = number2, onValueChange = { number2 = it }, label = { Text("Número 2") })
        Spacer(modifier = Modifier.height(8.dp))

        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { result = sum(number1, number2) }) { Text("+") }
            Button(onClick = { result = subtract(number1, number2) }) { Text("-") }
            Button(onClick = { result = multiply(number1, number2) }) { Text("*") }
            Button(onClick = { result = divide(number1, number2) }) { Text("/") }
        }

        Spacer(modifier = Modifier.height(8.dp))
        Text("Resultado: $result")
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = onExit) {
            Text("Salir")
        }
    }
}

// Operaciones
fun sum(n1: String, n2: String) = ((n1.toDoubleOrNull() ?: 0.0) + (n2.toDoubleOrNull() ?: 0.0)).toString()
fun subtract(n1: String, n2: String) = ((n1.toDoubleOrNull() ?: 0.0) - (n2.toDoubleOrNull() ?: 0.0)).toString()
fun multiply(n1: String, n2: String) = ((n1.toDoubleOrNull() ?: 0.0) * (n2.toDoubleOrNull() ?: 0.0)).toString()
fun divide(n1: String, n2: String): String {
    val denom = n2.toDoubleOrNull()
    return if (denom == 0.0 || denom == null) "Error" else ((n1.toDoubleOrNull() ?: 0.0) / denom).toString()
}
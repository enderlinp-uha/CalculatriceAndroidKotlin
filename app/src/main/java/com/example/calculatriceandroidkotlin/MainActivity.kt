package com.example.calculatriceandroidkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatriceandroidkotlin.ui.theme.CalculatriceAndroidKotlinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalculatriceAndroidKotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val calculator by remember() { mutableStateOf(Calculator()) }
    var input      by remember() { mutableStateOf(calculator.getCurrentInput()) }
    var history    by remember() { mutableStateOf(calculator.getCurrentHistory()) }

    fun insert(character: String) {
        // Si la saisie correspond à "0" ou à "Non défini"
        // on la remplace par le caractère en cours sinon on la concatène avec le caractère précédent
        when (input in listOf(DEFAULT_VALUE, UNDEFINED_VALUE)) {
            true -> input  = character
            else -> input += character
        }
    }

    fun update(action: EAction) {
        calculator.update(action, input)
        input   = calculator.getCurrentInput()
        history = calculator.getCurrentHistory()
    }

    Column(modifier = Modifier
        .fillMaxHeight()
        .padding(24.dp),
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp),
            horizontalArrangement = Arrangement.End) {
            Text(history, color = Color.Gray, fontSize = 32.sp)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp),
            horizontalArrangement = Arrangement.End) {
            // softWrap = false fait planter l'application
            // d'où l'utilisation de lineHeight
            Text(input, fontSize = 48.sp, lineHeight = 60.sp)
        }
        Row {
            Column(modifier = Modifier.weight(2/4f),
                   horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth(),
                       colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                       onClick = { update(EAction.CLEAR) }) {
                    Text("AC", fontSize = 18.sp)
                }
            }
            Column(modifier = Modifier.weight(1/4f),
                   horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth(),
                       colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                       onClick = { insert("%") }) {
                    Text("%", fontSize = 18.sp)
                }
            }
            Column(modifier = Modifier.weight(1/4f),
                   horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth(),
                       onClick = { insert("/") }) {
                    Text("/", fontSize = 18.sp)
                }
            }
        }
        Row {
            for (digit in 7..9) {
                Column(modifier = Modifier.weight(1/4f),
                       horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth(),
                           colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                           onClick = { insert(digit.toString()) }) {
                        Text(digit.toString(), fontSize = 18.sp)
                    }
                }
            }
            Column(modifier = Modifier.weight(1/4f),
                   horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth(),
                       onClick = { insert("*") }) {
                    Text("*", fontSize = 18.sp)
                }
            }
        }
        Row {
            for (digit in 4..6) {
                Column(modifier = Modifier.weight(1/4f),
                       horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth(),
                           colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                           onClick = { insert(digit.toString()) }) {
                        Text(digit.toString(), fontSize = 18.sp)
                    }
                }
            }
            Column(modifier = Modifier.weight(1/4f),
                  horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth(),
                       onClick = { insert("-") }) {
                    Text("-", fontSize = 18.sp)
                }
            }
        }
        Row {
            for (digit in 1..3) {
                Column(modifier = Modifier.weight(1/4f),
                       horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth(),
                           colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                           onClick = { insert(digit.toString()) }) {
                        Text(digit.toString(), fontSize = 18.sp)
                    }
                }
            }
            Column(modifier = Modifier.weight(1/4f),
                   horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth(),
                       onClick = { insert("+") }) {
                    Text("+", fontSize = 18.sp)
                }
            }
        }
        Row {
            Column(modifier = Modifier.weight(3/4f),
                   horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth(),
                       colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                       onClick = { insert("0") }) {
                    Text("0", fontSize = 18.sp)
                }
            }
            Column(modifier = Modifier.weight(1/4f),
                   horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth(),
                       onClick = { update(EAction.EQUAL) }) {
                    Text("=", fontSize = 18.sp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalculatriceAndroidKotlinTheme {
        Greeting("Android")
    }
}
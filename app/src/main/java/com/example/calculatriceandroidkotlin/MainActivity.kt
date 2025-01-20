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
import androidx.compose.runtime.rememberCompositionContext
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

    val calculator by remember() { mutableStateOf( Calculator() ) }
    var result by remember() { mutableStateOf( calculator.getResult() ) }

    fun update(operator: EOperator) {
        result = calculator.update(operator).getResult()
    }

    fun concatenate(digit: Int) {
        if (result.equals("0")) {
            result = digit.toString()
        } else {
            result += digit.toString()
        }
    }

    Column(modifier = Modifier.fillMaxHeight().padding(24.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.fillMaxWidth().padding(12.dp), horizontalArrangement = Arrangement.End) {
            Text(result, fontSize = 48.sp)
        }
        Row {
            Column(modifier = Modifier.weight(2/4f), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier.padding(3.dp).fillMaxWidth(),
                       colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                       onClick = { update(EOperator.CLEAR) }) {
                    Text("CE")
                }
            }
            Column(modifier = Modifier.weight(1/4f), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier.padding(3.dp).fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
                    onClick = { update(EOperator.MODULO) }) {
                    Text("%")
                }
            }
            Column(modifier = Modifier.weight(1/4f), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier.padding(3.dp).fillMaxWidth(),
                       onClick = { update(EOperator.DIVIDE) }) {
                    Text("/")
                }
            }
        }
        Row {
            for (digit in 7..9) {
                Column(modifier = Modifier.weight(1/4f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(modifier = Modifier.padding(3.dp).fillMaxWidth(),
                           colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                           onClick = { concatenate(digit) }) {
                        Text(digit.toString())
                    }
                }
            }
            Column(modifier = Modifier.weight(1/4f), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier.padding(3.dp).fillMaxWidth(),
                       onClick = { update(EOperator.MULTIPLY) }) {
                    Text("*")
                }
            }
        }
        Row {
            for (digit in 4..6) {
                Column(modifier = Modifier.weight(1/4f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(modifier = Modifier.padding(3.dp).fillMaxWidth(),
                           colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                           onClick = { concatenate(digit) }) {
                        Text(digit.toString())
                    }
                }
            }
            Column(modifier = Modifier.weight(1/4f), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier.padding(3.dp).fillMaxWidth(),
                       onClick = { update(EOperator.DIVIDE) }) {
                    Text("-")
                }
            }
        }
        Row {
            for (digit in 1..3) {
                Column(modifier = Modifier.weight(1/4f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(modifier = Modifier.padding(3.dp).fillMaxWidth(),
                           colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                           onClick = { concatenate(digit) }) {
                        Text(digit.toString())
                    }
                }
            }
            Column(modifier = Modifier.weight(1/4f), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier.padding(3.dp).fillMaxWidth(),
                       onClick = { update(EOperator.ADD) }) {
                    Text("+")
                }
            }
        }
        Row {
            Column(modifier = Modifier.weight(2/4f), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier.padding(3.dp).fillMaxWidth(),
                       colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                       onClick = { concatenate(0) }) {
                    Text("0")
                }
            }
            Column(modifier = Modifier.weight(1/4f), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier.padding(3.dp).fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    onClick = { }) {
                    Text(",")
                }
            }
            Column(modifier = Modifier.weight(1/4f), horizontalAlignment = Alignment.CenterHorizontally) {
                Button(modifier = Modifier.padding(3.dp).fillMaxWidth(),
                       onClick = { update(EOperator.EQUAL) }) {
                    Text("=")
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
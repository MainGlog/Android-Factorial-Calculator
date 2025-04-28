package com.example.factorial

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
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
import com.example.factorial.ui.theme.FactorialTheme
import kotlin.math.exp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Factorial()
        }
    }
}

// It's discouraged to have composables as members of the class
// They are declarative and stateless
// It's harder to use preview annotations and reuse cleanly

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Factorial() {
    // The 'by' keyword now makes it so that expanded and text refer to the value of the mutablestates
    var expanded by remember { mutableStateOf(false) } // expanded represents whether the dropdown is expanded or not
    var text by remember { mutableStateOf(factorialAsString(0)) }

    // Dark Mode - click the phone settings button underneath the running devices tab
    // it's to the right of the square and left of the camera
    Box(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background), // This will set the background to whatever theme the phone is on
        contentAlignment = Alignment.Center) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded}
        ) {
            Text(modifier = Modifier
                .menuAnchor(type = MenuAnchorType.PrimaryNotEditable, enabled = true)
                .clickable {
                expanded = true // onClick
            },
                text = text,
                style = MaterialTheme.typography.headlineMedium
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                for (n in 0..10) {
                    DropdownMenuItem(
                        onClick = {
                            // The user clicks this option, indicating they wish to calculate that factorial
                            expanded = false
                            text = factorialAsString(n)
                        },
                        text = { Text( "$n!") }
                    )
                }
            }
        }

    }
}

// Non-composable functions are camelCase, Composables are PascalCase
fun factorialAsString(n: Int): String {
    var result = 1L // L = long
    for(i in 1..n) {
        result *= i
    }
    return "$n! = $result"
}

@Composable
@Preview // Preview requires enabled to be a boolean passed in
// To view the preview, click the Design view in the top right under app.
// It looks like a picture
fun ButtonDemo(enabled: Boolean = true) {
    Box{
        Button(
            onClick = {
                println("clicked")
            },
            enabled = enabled
        ) {
            Text("Click me!")
        }
    }
}

@Composable
@Preview
fun BoxDemo() {
    Box(contentAlignment = Alignment.Center){
        Box( modifier = Modifier
            .size(width = 100.dp, height = 100.dp)
            .background(Color.Green))
        Box( modifier = Modifier
            .size(width = 80.dp, height = 80.dp)
            .background(Color.Yellow))
        Text(
            text = "Hello, world!",
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center))
    }
}
package com.example.assignment03_counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.assignment03_counter.ui.theme.Assignment03_CounterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment03_CounterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CounterApp()
                }
            }
        }
    }
}

@Composable
fun CounterApp() {
    var count by rememberSaveable { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = if (count >= 0)
                        "\uD83D\uDE42"
                else "\uD83D\uDE2D",
            modifier = Modifier.padding(vertical = 24.dp))
        Counter(count) { newCount ->
            count = newCount
        }
    }
}

@Composable
fun Counter(count: Int, onCountChange: (Int) -> Unit) {
    Text(
        text = "Count: $count",
        color = if (count < 0)
                    Color.Red
                else Color.Black
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { onCountChange(count - 1) }) {
            Text(text = "-")
        }
        Button(onClick = { onCountChange(count + 1) }) {
            Text(text = "+")
        }
    }
    Spacer(modifier = Modifier.height(8.dp))
    Button(onClick = { onCountChange(0) }) {
        Text(text = stringResource(R.string.reset))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment03_CounterTheme {
        CounterApp()
    }
}
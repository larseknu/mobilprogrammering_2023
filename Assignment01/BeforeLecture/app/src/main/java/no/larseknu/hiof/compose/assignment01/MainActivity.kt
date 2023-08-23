package no.larseknu.hiof.compose.assignment01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import no.larseknu.hiof.compose.assignment01.ui.theme.Assignment01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment01Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // TODO: Change the text to something else
                    // TODO: Extract the text to a String-resource
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // TODO: Add some padding to the Text (can be done with the help of the modifier)
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
    // TODO: Add another Text-composable, what happens with it?
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment01Theme {
        // TODO: Change the text to something else
        // TODO: Extract the text to a String-resource
        Greeting("Android")
    }
}
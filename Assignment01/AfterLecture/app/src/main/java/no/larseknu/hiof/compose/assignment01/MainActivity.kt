package no.larseknu.hiof.compose.assignment01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    HelloWorld(stringResource(R.string.world), stringResource(R.string.exist_live))
                }
            }
        }
    }
}

@Composable
fun HelloWorld(name: String, quote: String, modifier: Modifier = Modifier) {
    // We haven't set any placement or alignment for these Text-composables,
    // so they appear on top of each other
    Text(
        text = "Hello $name!",
        modifier = modifier.padding(8.dp)
    )
    Text(
        text = quote
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Assignment01Theme {
        HelloWorld(stringResource(R.string.world),
            stringResource(R.string.exist_live))
    }
}
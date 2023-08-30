package no.larseknu.hiof.compose.colorbutton

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import no.larseknu.hiof.compose.colorbutton.ui.theme.ColorButtonTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColorButtonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ColorChangingScreen()
                }
            }
        }
    }
}

@Composable
fun ColorChangingButtonOwnState(modifier : Modifier = Modifier) {
    var backgroundColor by remember {mutableStateOf(Color.White)}

    Box(contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)) {
        Button(
            onClick = {
                backgroundColor = if(backgroundColor == Color.White) Color.Cyan else Color.White
            }
        ) {
            Text(text = stringResource(R.string.change_color))
        }
    }
}

@Composable
fun ColorChangingButton(currentColor : Color,
                        updateColor: (Color) -> Unit,
                        modifier : Modifier = Modifier) {
    Box(contentAlignment = Alignment.Center,
        modifier = modifier
            .background(currentColor)) {
        Button(
            onClick = {
                val newColor = when (currentColor) {
                    Color.White -> Color.Cyan
                    Color.Cyan -> Color.Magenta
                    else -> Color.White
                }

                updateColor(newColor)
            }
        ) {
            Text(text = stringResource(R.string.change_color))
        }
    }
}

@Composable
fun ColorChangingScreen(modifier: Modifier = Modifier) {
    var backgroundColor1 by remember {mutableStateOf(Color.Magenta)}
    var backgroundColor2 by remember {mutableStateOf(Color.Cyan)}

    Column(verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()) {
        ColorChangingButton(currentColor = backgroundColor1,
            updateColor = {newColor -> backgroundColor1 = newColor},
            Modifier.weight(1.0f).fillMaxWidth())

        ColorChangingButton(currentColor = backgroundColor2,
            updateColor = {newColor -> backgroundColor2 = newColor},
            Modifier.weight(1.0f).fillMaxWidth())
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ColorButtonTheme {
        ColorChangingScreen()
    }
}
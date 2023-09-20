package larseknu.hiof.playingwithmaterialdesign

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.CheckboxDefaults.colors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import larseknu.hiof.playingwithmaterialdesign.ui.theme.PlayingWithMaterialDesignTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayingWithMaterialDesignTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MaterialApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialApp() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Material Design",
                        textAlign = TextAlign.Left,
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /*TODO*/ },
                containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        MaterialScreen(Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MaterialScreen(modifier: Modifier = Modifier) {
    val textState = remember { mutableStateOf("") }
    val switchState = remember { mutableStateOf(false) }
    val sliderState = remember { mutableStateOf(0.5f) }
    val showCardDetails = remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Material Display",
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Button(onClick = {}) {
            Text(text = "Button")
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Row Text 1",
                style = MaterialTheme.typography.headlineSmall)
            Text(text = "Row Text 2")
        }

        TextField(
            value = textState.value,
            onValueChange = {
                textState.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Switch(
            checked = switchState.value,
            onCheckedChange = {
                switchState.value = it
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Checkbox(
                checked = true,
                onCheckedChange = null
            )

            Checkbox(
                checked = false,
                onCheckedChange = null
            )
        }

        Slider(
            value = sliderState.value,
            onValueChange = {
                sliderState.value = it
            },
            valueRange = 0f..1f,
            steps = 10
        )

        RadioButton(
            selected = !switchState.value,
            onClick = {
                switchState.value = !switchState.value
            }
        )

        CircularProgressIndicator(
            progress = sliderState.value,
            modifier = Modifier.size(50.dp)
        )


        Card(elevation = CardDefaults.cardElevation(4.dp),
            onClick = {showCardDetails.value = !showCardDetails.value}) {
            Column(Modifier.padding(8.dp)) {
                Text(text = "Hello, I'm Card",
                    style = MaterialTheme.typography.displayMedium)
                if (showCardDetails.value)
                    Text(text = "Some top secret details")
            }
        }
        Card(elevation = CardDefaults.cardElevation(4.dp),
            onClick = {showCardDetails.value = !showCardDetails.value}) {
            Column(Modifier.animateContentSize().padding(8.dp)) {
                Text(text = "Hello, I'm Card",
                    style = MaterialTheme.typography.displayMedium)
                if (showCardDetails.value)
                    Text(text = "Some top secret details")
            }
        }
        Card(elevation = CardDefaults.cardElevation(4.dp)) {
            Text(text = "Hello, I'm Card",
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MaterialScreenPreview() {
    PlayingWithMaterialDesignTheme {
        MaterialApp()
    }
}
package no.larseknu.hiof.compose.playingwithnavigation_test.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Text(text = "Welcome Home!",
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Center,
        modifier = modifier.fillMaxSize())
}
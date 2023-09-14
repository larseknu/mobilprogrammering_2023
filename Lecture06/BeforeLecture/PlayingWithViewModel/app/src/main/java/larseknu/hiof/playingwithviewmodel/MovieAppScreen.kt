package larseknu.hiof.playingwithviewmodel

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import larseknu.hiof.playingwithviewmodel.ui.MovieSelectionScreen

enum class Screen {
    MovieSelection
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieAppScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar( title = { Text(text = "Movies") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ))
        }
    ) { innerPadding ->
        NavHost(navController = navController,
            startDestination = Screen.MovieSelection.name,
            modifier = modifier.fillMaxSize().padding(innerPadding)) {
            composable(Screen.MovieSelection.name) {
                MovieSelectionScreen()
            }
        }
    }
}


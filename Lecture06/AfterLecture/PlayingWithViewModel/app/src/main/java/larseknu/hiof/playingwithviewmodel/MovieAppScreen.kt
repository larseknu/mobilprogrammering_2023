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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import larseknu.hiof.playingwithviewmodel.ui.MovieSelectionScreen
import larseknu.hiof.playingwithviewmodel.ui.MovieSummaryScreen

enum class Screen {
    MovieSelection,
    MovieSummary
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
                MovieSelectionScreen(onMovieSelectionDone = {movieSeen, numberAnswered ->
                    navController.navigate("${Screen.MovieSummary.name}/$movieSeen/$numberAnswered")
                })
            }
            composable(Screen.MovieSummary.name+ "/{moviesSeen}/{numberAnswered}",
                arguments = listOf(
                    navArgument("moviesSeen") {
                        type = NavType.IntType
                    },
                    navArgument("numberAnswered") {
                        type = NavType.IntType
                    }
                )) {
                val moviesSeen = it.arguments?.getInt("moviesSeen")?: 0
                val numberAnswered = it.arguments?.getInt("numberAnswered")?: 0
                MovieSummaryScreen(moviesSeen, numberAnswered)
            }
        }
    }
}


package no.larseknu.hiof.compose.playingwithfirebase

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import no.larseknu.hiof.compose.playingwithfirebase.screen.moviedetail.MovieDetailScreen
import no.larseknu.hiof.compose.playingwithfirebase.screen.movielist.MovieListScreen
import no.larseknu.hiof.compose.playingwithfirebase.ui.theme.PlayingWithFirebaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirebaseApp() {

    PlayingWithFirebaseTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val snackbarHostState = remember { SnackbarHostState() }
            val navController = rememberNavController()

            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = snackbarHostState,
                        modifier = Modifier.padding(8.dp),
                        snackbar = { snackbarData ->
                            Snackbar(snackbarData, contentColor = MaterialTheme.colorScheme.secondaryContainer)
                        }
                    )
                },
            ) { innerPaddingModifier ->
                NavHost(
                    navController = navController,
                    startDestination = MOVIE_LIST,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    composable(MOVIE_LIST) {
                        MovieListScreen(onMovieClick = {
                                movieId ->
                            val route = "${MOVIE_DETAIL}?$MOVIE_ID=$movieId"
                            navController.navigate(route)
                        })
                    }

                    composable(
                        route = "${MOVIE_DETAIL}$MOVIE_ID_ARG",
                        arguments = listOf(navArgument(MOVIE_ID) {
                            nullable = false
                        })
                    ) {
                        MovieDetailScreen()
                    }
                }
            }
        }
    }

}
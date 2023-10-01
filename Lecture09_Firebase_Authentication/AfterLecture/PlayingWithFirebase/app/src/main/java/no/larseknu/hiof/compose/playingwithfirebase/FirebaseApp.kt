package no.larseknu.hiof.compose.playingwithfirebase

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import no.larseknu.hiof.compose.playingwithfirebase.screen.sign_up.SignUpScreen
import no.larseknu.hiof.compose.playingwithfirebase.screen.moviedetail.MovieDetailScreen
import no.larseknu.hiof.compose.playingwithfirebase.screen.movielist.MovieListScreen
import no.larseknu.hiof.compose.playingwithfirebase.ui.theme.PlayingWithFirebaseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FirebaseApp() {

    PlayingWithFirebaseTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val navController = rememberNavController()

            Scaffold(
                topBar =  {
                    TopAppBar(
                        title = { Text(stringResource(R.string.movies)) },
                        actions = {
                            IconButton(onClick = { navController.navigate(SIGN_UP) }) {
                                Icon(imageVector = Icons.Default.Person, contentDescription = "Action")
                            }
                        }
                    )
                }
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

                    composable(SIGN_UP) {
                        SignUpScreen(loggedIn = { navController.navigate(MOVIE_LIST) })
                    }
                }
            }
        }
    }

}
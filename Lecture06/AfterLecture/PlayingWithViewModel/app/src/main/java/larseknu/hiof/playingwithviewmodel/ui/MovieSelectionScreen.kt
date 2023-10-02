package larseknu.hiof.playingwithviewmodel.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import larseknu.hiof.playingwithviewmodel.R
import java.lang.Integer.max


@Composable
fun MovieSelectionScreen(movieSelectionViewModel: MovieSelectionViewModel = viewModel(),
                         onMovieSelectionDone: (Int, Int) -> Unit,
                         modifier: Modifier = Modifier) {
    val movieUiState by movieSelectionViewModel.uiState.collectAsState()

    Column(verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            Image(painter = painterResource(movieUiState.moviePoster),
                contentDescription = stringResource(R.string.movie_poster),
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(400.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            LinearProgressIndicator(movieUiState.numberAnswered.toFloat() / movieUiState.totalMovies.toFloat())
            AnsweredMovieStatus(movieUiState.numberAnswered, movieUiState.totalMovies)
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { movieSelectionViewModel.seenMovie() }) {
                    Text(text = stringResource(R.string.seen))
                }
                Button(onClick = { movieSelectionViewModel.notSeenMovie() }) {
                    Text(text = stringResource(R.string.not_seen))
                }
            }
        }

        SeenMovieStatus(movieUiState.moviesSeen, movieUiState.numberAnswered)

        Button(onClick = { movieSelectionViewModel.selectionDone() }) {
            Text(text = stringResource(R.string.selection_done))
        }

        if (movieUiState.movieSelectionDone) {
            Toast.makeText(LocalContext.current,
                stringResource(R.string.seen_num_of_total_movies,
                    movieUiState.moviesSeen,
                    movieUiState.numberAnswered),
                Toast.LENGTH_SHORT).show()
            /*Toast.makeText(LocalContext.current,
                "Starting over...",
                Toast.LENGTH_SHORT).show()
            movieSelectionViewModel.resetSelection()*/
            onMovieSelectionDone(movieUiState.moviesSeen, movieUiState.numberAnswered)
        }
    }
}


@Composable
fun SeenMovieStatus(seen: Int, total: Int,  modifier: Modifier = Modifier) {
    Text(text = stringResource(R.string.seen_num_of_total_movies, seen, total),
        style = MaterialTheme.typography.displaySmall,
        modifier = modifier)
}

@Composable
fun AnsweredMovieStatus(answered: Int, total: Int,  modifier: Modifier = Modifier) {
    Text(text = stringResource(R.string.answered_number_of_total_movies, answered, total),
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier)
}

@Preview
@Composable
fun MovieSelectionScreenPreview() {
    MovieSelectionScreen(onMovieSelectionDone = {n1, n2 -> })
}
package larseknu.hiof.playingwithviewmodel.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import larseknu.hiof.playingwithviewmodel.data.Datasource.movieList
import larseknu.hiof.playingwithviewmodel.data.MovieSelectionUiState

class MovieSelectionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MovieSelectionUiState())
    val uiState: StateFlow<MovieSelectionUiState> = _uiState.asStateFlow()

    private var seenMovies: MutableSet<Int> = mutableSetOf()

    private var currentMovieIndex: Int = 0

    init {
        resetSelection()
    }

    fun seenMovie() {
        seenMovies.add(currentMovieIndex)

        nextMovie()
    }

    fun notSeenMovie() {
        nextMovie()
    }


    fun selectionDone() {
        _uiState.update { currentState ->
            currentState.copy(
                movieSelectionDone = true
            )
        }
        resetSelection()
    }

    private fun nextMovie() {
        if (currentMovieIndex != movieList.size-1) {
            currentMovieIndex = currentMovieIndex.inc()
            _uiState.update { movieSelectionUiState ->
                    movieSelectionUiState.copy(currentMovieIndex,
                        movieList[currentMovieIndex].posterUrl,
                        moviesSeen = seenMovies.size,
                        numberAnswered = movieSelectionUiState.numberAnswered.inc())
            }
        }
        else {
            _uiState.update { currentState ->
                currentState.copy(
                    moviesSeen = seenMovies.size,
                    movieSelectionDone = true
                )
            }
        }
    }

    fun resetSelection() {
        seenMovies.clear()
        currentMovieIndex = 0

        _uiState.value = MovieSelectionUiState(
            currentMovieIndex = currentMovieIndex,
            moviesSeen = 0,
            numberAnswered = 0,
            moviePoster =  movieList[currentMovieIndex].posterUrl,
            totalMovies = movieList.size,
            movieSelectionDone = false
            )
    }
}
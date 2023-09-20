package no.larseknu.hiof.compose.playingwithfirebase.screen.moviedetail

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import no.larseknu.hiof.compose.playingwithfirebase.MOVIE_ID
import no.larseknu.hiof.compose.playingwithfirebase.model.Movie
import no.larseknu.hiof.compose.playingwithfirebase.service.StorageService
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val storageService: StorageService
) : ViewModel() {
    val movie = mutableStateOf(Movie())

    init {
        val movieId = savedStateHandle.get<String>(MOVIE_ID)
        if (movieId != null) {
            viewModelScope.launch {
                movie.value = storageService.getMovie(movieId) ?: Movie()
            }
        }
    }
}

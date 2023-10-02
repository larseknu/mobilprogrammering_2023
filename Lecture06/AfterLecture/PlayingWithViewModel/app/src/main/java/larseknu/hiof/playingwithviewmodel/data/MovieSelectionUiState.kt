package larseknu.hiof.playingwithviewmodel.data

import androidx.annotation.DrawableRes

data class MovieSelectionUiState(
    val currentMovieIndex: Int = 0,
    @DrawableRes val moviePoster: Int = 0,
    val moviesSeen: Int = 0,
    val numberAnswered: Int = 0,
    val totalMovies: Int = 10,
    val movieSelectionDone: Boolean = false
)
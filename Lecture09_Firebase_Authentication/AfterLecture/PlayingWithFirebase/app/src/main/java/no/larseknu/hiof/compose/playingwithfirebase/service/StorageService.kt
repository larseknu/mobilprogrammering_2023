package no.larseknu.hiof.compose.playingwithfirebase.service

import kotlinx.coroutines.flow.Flow
import no.larseknu.hiof.compose.playingwithfirebase.model.Movie

interface StorageService {
    val movies: Flow<List<Movie>>
    suspend fun getMovie(movieId: String): Movie?
    suspend fun save(movie: Movie): String
}

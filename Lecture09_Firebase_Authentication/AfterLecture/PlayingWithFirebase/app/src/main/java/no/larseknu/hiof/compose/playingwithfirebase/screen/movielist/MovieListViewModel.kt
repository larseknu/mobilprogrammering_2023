package no.larseknu.hiof.compose.playingwithfirebase.screen.movielist

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuthException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.launch
import no.larseknu.hiof.compose.playingwithfirebase.dummydata.Datasource
import no.larseknu.hiof.compose.playingwithfirebase.model.Movie
import no.larseknu.hiof.compose.playingwithfirebase.service.AccountService
import no.larseknu.hiof.compose.playingwithfirebase.service.StorageService
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(private val storageService: StorageService,
                                             private val accountService: AccountService)
    : ViewModel() {


    val movies = storageService.movies

    init {
        createAnonymousAccount()

        viewModelScope.launch {
            if (movies.first().isEmpty()) {
                Datasource.movieList.forEach { movie ->
                    storageService.save(movie)
                }
            }
        }
    }

    fun createMovie(movieTitle: String) {
        viewModelScope.launch {
            storageService.save(Movie(title = movieTitle))
        }
    }

    private fun createAnonymousAccount() {
        viewModelScope.launch {
            if (!accountService.hasUser)
                accountService.createAnonymousAccount()
        }
    }
}

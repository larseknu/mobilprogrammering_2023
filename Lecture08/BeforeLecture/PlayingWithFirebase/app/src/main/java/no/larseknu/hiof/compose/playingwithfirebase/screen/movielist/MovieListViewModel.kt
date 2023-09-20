package no.larseknu.hiof.compose.playingwithfirebase.screen.movielist

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.flow.produceIn
import kotlinx.coroutines.launch
import no.larseknu.hiof.compose.playingwithfirebase.dummydata.Datasource
import no.larseknu.hiof.compose.playingwithfirebase.service.StorageService
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor()  : ViewModel() {
}

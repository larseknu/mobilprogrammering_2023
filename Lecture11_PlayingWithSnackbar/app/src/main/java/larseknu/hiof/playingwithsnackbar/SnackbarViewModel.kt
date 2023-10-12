package larseknu.hiof.playingwithsnackbar

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SnackbarViewModel : ViewModel() {
    private var counter = 0
    val uiState = mutableStateOf("Clicked ")

    fun countUp() {
        counter++
        uiState.value = "Clicked $counter"
    }

    fun cancel() {
        counter--
        uiState.value = "Clicked $counter"
    }
}
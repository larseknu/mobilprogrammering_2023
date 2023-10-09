package larseknu.hiof.playingwithmisc

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    private var counter = 0
    val uiState = mutableStateOf("Clicked $counter")

    fun doSomething() {
        counter += 1
        uiState.value = "Clicked $counter"
    }

    fun cancel() {
        counter -= 1
        uiState.value = "Clicked $counter"
    }
}

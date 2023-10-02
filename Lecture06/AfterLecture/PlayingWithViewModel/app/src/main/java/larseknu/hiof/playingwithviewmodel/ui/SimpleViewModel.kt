package larseknu.hiof.playingwithviewmodel.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SimpleViewModel : ViewModel() {
    private val _name = MutableStateFlow("John Doe")
    val name: StateFlow<String> = _name.asStateFlow()

    fun updateName(newName: String) {
        _name.value = newName
    }
}
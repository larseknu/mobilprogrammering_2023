package larseknu.hiof.playingwithviewmodel.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

@Composable
fun MovieSummaryScreen(moviesSeen: Int, numberAnswered: Int) {

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        SeenMovieStatus(seen = moviesSeen, total = numberAnswered)
    }

}
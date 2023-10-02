package no.larseknu.hiof.compose.playingwithfirebase.model

import com.google.firebase.firestore.DocumentId

data class Movie(
    @DocumentId val uid : String = "",
    val title : String = "",
    var description : String = "",
    var posterUrl : String = "",
    var userId: String = "")

package no.larseknu.hiof.compose.playingwithfirebase.service.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import no.larseknu.hiof.compose.playingwithfirebase.model.Movie
import no.larseknu.hiof.compose.playingwithfirebase.service.StorageService
import javax.inject.Inject

class StorageServiceImpl
@Inject
constructor(private val firestore: FirebaseFirestore) : StorageService {

    override val movies: Flow<List<Movie>>
        get() = firestore.collection(MOVIE_COLLECTION).dataObjects()

    override suspend fun getMovie(movieId: String): Movie? =
        firestore.collection(MOVIE_COLLECTION).document(movieId).get().await().toObject()


    override suspend fun save(movie: Movie): String =
        firestore.collection(MOVIE_COLLECTION).add(movie).await().id



    companion object {
        private const val MOVIE_COLLECTION = "movies"
    }
}
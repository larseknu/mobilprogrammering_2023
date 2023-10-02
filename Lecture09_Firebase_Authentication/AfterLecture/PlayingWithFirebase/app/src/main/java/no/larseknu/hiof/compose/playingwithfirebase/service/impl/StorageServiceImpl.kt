package no.larseknu.hiof.compose.playingwithfirebase.service.impl

import com.google.firebase.firestore.Filter
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import no.larseknu.hiof.compose.playingwithfirebase.model.Movie
import no.larseknu.hiof.compose.playingwithfirebase.service.AccountService
import no.larseknu.hiof.compose.playingwithfirebase.service.StorageService
import javax.inject.Inject

class StorageServiceImpl
@Inject
constructor(private val firestore: FirebaseFirestore,
            private val auth: AccountService) : StorageService {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val movies: Flow<List<Movie>>
        get() = auth.currentUser.flatMapLatest { user ->
            firestore.collection(MOVIE_COLLECTION)
                .where(
                    Filter.or(Filter.equalTo(USER_ID_FIELD, user.id),
                        Filter.equalTo(USER_ID_FIELD, "")))
                .dataObjects()
        }

    override suspend fun getMovie(movieId: String): Movie? =
        firestore.collection(MOVIE_COLLECTION).document(movieId).get().await().toObject()


    override suspend fun save(movie: Movie): String {
        val movieWithUserId = movie.copy(userId = auth.currentUserId)
        return firestore.collection(MOVIE_COLLECTION).add(movieWithUserId).await().id
    }



    companion object {
        private const val MOVIE_COLLECTION = "movies"
        private const val USER_ID_FIELD = "userId"
    }
}
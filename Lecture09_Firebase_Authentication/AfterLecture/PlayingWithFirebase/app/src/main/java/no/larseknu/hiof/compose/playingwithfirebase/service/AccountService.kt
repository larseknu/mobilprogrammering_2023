package no.larseknu.hiof.compose.playingwithfirebase.service

import kotlinx.coroutines.flow.Flow
import no.larseknu.hiof.compose.playingwithfirebase.model.User

interface AccountService {
    val currentUserId: String
    val hasUser: Boolean

    val currentUser: Flow<User>

    suspend fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit)
    suspend fun createAnonymousAccount()
    suspend fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
    suspend fun signOut()
}
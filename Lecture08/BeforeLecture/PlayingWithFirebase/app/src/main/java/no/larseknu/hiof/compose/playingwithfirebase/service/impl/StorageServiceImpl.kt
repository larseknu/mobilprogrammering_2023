package no.larseknu.hiof.compose.playingwithfirebase.service.impl

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import no.larseknu.hiof.compose.playingwithfirebase.model.Movie
import no.larseknu.hiof.compose.playingwithfirebase.service.StorageService
import javax.inject.Inject

class StorageServiceImpl @Inject constructor() : StorageService
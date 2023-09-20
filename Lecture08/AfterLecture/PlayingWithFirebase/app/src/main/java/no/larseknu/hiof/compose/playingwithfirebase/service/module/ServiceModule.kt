package no.larseknu.hiof.compose.playingwithfirebase.service.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import no.larseknu.hiof.compose.playingwithfirebase.service.StorageService
import no.larseknu.hiof.compose.playingwithfirebase.service.impl.StorageServiceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService
}
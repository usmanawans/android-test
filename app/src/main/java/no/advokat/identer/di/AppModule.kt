package no.advokat.identer.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import no.advokat.identer.data.repository.AuthRepository
import no.advokat.identer.utils.SecureStorage
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSecureStorage(application: Application): SecureStorage {
        return SecureStorage(application.applicationContext)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        secureStorage: SecureStorage
    ): AuthRepository {
        return AuthRepository(secureStorage)
    }
}
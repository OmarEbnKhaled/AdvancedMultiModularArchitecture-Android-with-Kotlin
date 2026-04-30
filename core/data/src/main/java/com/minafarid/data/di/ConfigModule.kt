package com.minafarid.data.di

import com.minafarid.data.constants.ACCESS_TOKEN_TAG
import com.minafarid.data.constants.CLIENT_ID_TAG
import com.minafarid.data.constants.LANGUAGE_TAG
import com.minafarid.data.constants.USER_ID_TAG
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Locale
import javax.inject.Named
import javax.inject.Singleton
import com.minafarid.protodatastore.manager.preferences.PreferencesDataStoreInterface
import com.minafarid.protodatastore.manager.session.SessionDataStoreInterface
import kotlinx.coroutines.runBlocking
import java.util.UUID

@Module
@InstallIn(SingletonComponent::class)
class ConfigModule {

    @Provides
    @Singleton
    @Named(USER_ID_TAG)
    fun provideUserId(sessionDataStoreInterface: SessionDataStoreInterface): () -> String? {
        val userId = runBlocking { sessionDataStoreInterface.getUserId() }
        return { userId }
    }

    @Provides
    @Singleton
    @Named(LANGUAGE_TAG)
    fun provideLanguage(preferencesDataStoreInterface: PreferencesDataStoreInterface): () -> Locale {
        val language = runBlocking { preferencesDataStoreInterface.getLanguage() }
        if (language.isNotEmpty()) {
            return { Locale(language) }
        } else {
            return { Locale.ENGLISH }
        }
    }

    @Provides
    @Singleton
    @Named(ACCESS_TOKEN_TAG)
    fun provideAccessToken(sessionDataStoreInterface: SessionDataStoreInterface): () -> String? {
        val accessToken = runBlocking { sessionDataStoreInterface.getAccessToken() }
        return { accessToken }
    }

    @Provides
    @Singleton
    @Named(CLIENT_ID_TAG)
    fun provideClientId(): String {
        return UUID.randomUUID().toString()
    }
}

package com.minafarid.protodatastore.manager

import androidx.datastore.core.DataStore
import com.minafarid.proto.Preferences
import kotlinx.coroutines.flow.Flow

class PreferencesDataStoreImplementer(
    private val preferencesDataStore: DataStore<Preferences>
) : PreferencesDataStoreInterface {
    override suspend fun setLanguage(language: String) {
        TODO("Not yet implemented")
    }

    override suspend fun setIsAppLockedEnabled(isAppLockedEnabled: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun setNotificationCount(notificationCount: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun setMoneyBalance(moneyBalance: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getLanguage(): String {
        TODO("Not yet implemented")
    }

    override fun getLanguageFlow(): Flow<String> {
        TODO("Not yet implemented")
    }

    override suspend fun getIsAppLockedEnabled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getIsAppLockedEnabledFlow(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun getNotificationCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getNotificationCountFlow(): Flow<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun getMoneyBalance(): Int {
        TODO("Not yet implemented")
    }

    override fun getMoneyBalanceFlow(): Flow<Int> {
        TODO("Not yet implemented")
    }
}
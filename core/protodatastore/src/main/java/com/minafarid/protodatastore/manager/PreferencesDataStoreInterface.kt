package com.minafarid.protodatastore.manager

import kotlinx.coroutines.flow.Flow

interface PreferencesDataStoreInterface {
    // Setter Functions
    suspend fun setLanguage(language: String)
    suspend fun setIsAppLockedEnabled(isAppLockedEnabled: Boolean)
    suspend fun setNotificationCount(notificationCount: Int)
    suspend fun setMoneyBalance(moneyBalance: Int)

    // Getter Functions

    suspend fun getLanguage(): String
    fun getLanguageFlow(): Flow<String>

    suspend fun getIsAppLockedEnabled(): Boolean
    fun getIsAppLockedEnabledFlow(): Flow<Boolean>

    suspend fun getNotificationCount(): Int
    fun getNotificationCountFlow(): Flow<Int>

    suspend fun getMoneyBalance(): Int
    fun getMoneyBalanceFlow(): Flow<Int>


}
package com.minafarid.login.domain.mapper

import com.minafarid.login.data.responses.LoginResponse
import com.minafarid.login.domain.models.UserModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LoginMapperImplementer(private val defaultDispatcher: CoroutineDispatcher) : LoginMapper {
    override suspend fun toDomain(loginResponse: LoginResponse): UserModel {
        return withContext(defaultDispatcher) {
            UserModel(
                id = loginResponse.id.orEmpty(),
                fullName = loginResponse.fullName.orEmpty(),
                email = loginResponse.email.orEmpty(),
                photo = loginResponse.photo.orEmpty()
            )
        }

    }
}
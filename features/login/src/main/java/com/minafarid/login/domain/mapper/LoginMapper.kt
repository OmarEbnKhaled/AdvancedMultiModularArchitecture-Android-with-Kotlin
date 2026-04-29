package com.minafarid.login.domain.mapper

import com.minafarid.login.data.responses.LoginResponse
import com.minafarid.login.domain.models.UserModel

interface LoginMapper {
    suspend fun toDomain(loginResponse: LoginResponse): UserModel
}
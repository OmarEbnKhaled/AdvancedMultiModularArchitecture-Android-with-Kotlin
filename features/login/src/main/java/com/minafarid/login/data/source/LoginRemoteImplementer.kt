package com.minafarid.login.data.source

import com.minafarid.data.error.toDomain
import com.minafarid.data.result.OutCome
import com.minafarid.data.source.NetworkDataSource
import com.minafarid.login.data.requests.LoginRequestBody
import com.minafarid.login.data.service.LoginService
import com.minafarid.login.domain.mapper.LoginMapper
import com.minafarid.login.domain.models.UserModel

class LoginRemoteImplementer(
    private val networkDataSource: NetworkDataSource<LoginService>,
    private val loginMapper: LoginMapper
) : LoginRemote {
    override suspend fun login(loginRequestBody: LoginRequestBody): OutCome<UserModel> {
        return networkDataSource.performRequest(
            request = { login(loginRequestBody).await() },
            onSuccess = { loginResponse, _ -> OutCome.success(loginMapper.toDomain(loginResponse)) },
            onError = { errorResponse, code -> OutCome.error(errorResponse.toDomain(code)) }
        )
    }
}
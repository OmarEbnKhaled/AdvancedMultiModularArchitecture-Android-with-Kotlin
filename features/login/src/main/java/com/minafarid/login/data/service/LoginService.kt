package com.minafarid.login.data.service

import com.minafarid.login.data.requests.LoginRequestBody
import com.minafarid.login.data.responses.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

const val BASE_URL = ""
const val EMAIL = "email"

interface LoginService {

  @POST("$BASE_URL/auth/login")
  fun login(
    @Body body: LoginRequestBody,
  ): Deferred<Response<LoginResponse>>

  @POST("$BASE_URL/auth/forgetPassword")
  fun forgetPassword(
    @Query(EMAIL) email: String,
  ): Deferred<Response<Unit>>
}

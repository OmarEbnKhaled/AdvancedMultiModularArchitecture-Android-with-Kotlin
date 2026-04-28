package com.minafarid.data.result

import com.minafarid.data.model.ErrorMessage

interface UseCase<R> {

    suspend fun onSuccess(success: OutCome.Success<R>)

    suspend fun omEmpty()

    suspend fun onError(errorMessage: ErrorMessage)
}
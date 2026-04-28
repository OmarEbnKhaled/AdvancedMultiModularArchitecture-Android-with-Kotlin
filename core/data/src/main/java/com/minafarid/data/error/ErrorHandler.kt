package com.minafarid.data.error

import com.google.gson.Gson
import com.minafarid.data.model.ErrorMessage
import com.minafarid.data.response.ErrorResponse

// Mapping errorResponse to ErrorMessage model
fun ErrorResponse.toDomain(code: Int): ErrorMessage {
    return ErrorMessage(
        code = code,
        message = errorMessage.orEmpty(),
        errorFieldList = errorFieldList ?: emptyList(),
    )
}

// Create default error response
fun getDefaultErrorResponse() = ErrorResponse(
    errorCode = "",
    errorMessage = "No internet connection",
    errorFieldList = emptyList()
)

// Getting error response from error body "string"
fun getErrorResponse(gson: Gson, errorBodyString: String): ErrorResponse =
    try {
        gson.fromJson(errorBodyString, ErrorResponse::class.java)
    } catch (e: Exception) {
        getDefaultErrorResponse()
    }
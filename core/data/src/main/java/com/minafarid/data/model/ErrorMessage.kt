package com.minafarid.data.model

data class ErrorMessage(
    val errorCode: Int,
    val errorMessage: String,
    val errorFieldList: List<String>,
)

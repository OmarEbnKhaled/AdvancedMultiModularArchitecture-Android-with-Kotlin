package com.minafarid.login.domain

import com.google.gson.annotations.SerializedName

data class UserModel (
    val id: String,
    val fullName: String,
    val email: String,
    val photo: String,
)
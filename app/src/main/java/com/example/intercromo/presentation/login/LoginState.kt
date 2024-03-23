package com.example.intercromo.presentation.login

import androidx.annotation.StringRes

data class LoginState(
    val succesLoginState: Boolean = false,
    val displayProgressBar: Boolean = false,
    @StringRes val mensajeError: Int? = null

)

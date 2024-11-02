package com.kiss.rbac

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class RegisterRequest(

    @field:NotBlank
    @field:Size(max = 50)
    val username: String,

    @field:NotBlank
    @field:Size(max = 50)
    val password: String
)

data class LoginRequest(

    @field:NotEmpty
    val username: String,

    @field:NotEmpty
    val password: String
)

package com.kiss.rbac

import com.kiss.API_PREFIX
import com.kiss.BusinessException
import jakarta.validation.Valid
import org.babyfish.jimmer.sql.exception.SaveException
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("${API_PREFIX}/user")
class UserService(
    val sql: KSqlClient,
    val passwordEncoder: PasswordEncoder
) {

    @PostMapping("/register")
    fun register(@RequestBody @Valid request: RegisterRequest) {

        val user = User {
            username = request.username
            password = passwordEncoder.encode(request.password)
        }

        try {
            sql.insert(user)
        }
        catch (ex: SaveException.NotUnique) {
            throw if (ex.isMatched(UserProps.USERNAME)) BusinessException("Username already exists") else ex
        }
    }
}

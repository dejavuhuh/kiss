package com.kiss.security

import com.kiss.API_PREFIX
import com.kiss.rbac.LoginRequest
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("${API_PREFIX}/auth")
class AuthService {

    @PostMapping("/login")
    fun login(@RequestBody body: LoginRequest, request: HttpServletRequest) {
        request.login(body.username, body.password)
    }

    @PostMapping("/logout")
    fun logout(request: HttpServletRequest) {
        request.logout()
    }

    @GetMapping("/me")
    fun me(request: HttpServletRequest): UserDetails {
        val authentication = request.userPrincipal as Authentication
        return authentication.principal as UserDetails
    }
}

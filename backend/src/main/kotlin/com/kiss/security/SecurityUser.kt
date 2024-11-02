package com.kiss.security

import com.fasterxml.jackson.annotation.JsonProperty
import com.kiss.rbac.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUser(private val user: User) : UserDetails {

    override fun getAuthorities(): List<GrantedAuthority> {
        return emptyList()
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    override fun getPassword(): String {
        return user.password
    }

    override fun getUsername(): String {
        return user.username
    }
}

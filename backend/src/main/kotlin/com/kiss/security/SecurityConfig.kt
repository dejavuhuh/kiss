package com.kiss.security

import com.kiss.API_PREFIX
import com.kiss.rbac.UserRepository
import org.babyfish.jimmer.spring.cfg.JimmerProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.core.env.Environment
import org.springframework.http.HttpMethod.GET
import org.springframework.http.HttpMethod.POST
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    fun api(path: String) = "${API_PREFIX}/$path"

    @Bean
    @Order(1)
    fun apiFilterChain(
        http: HttpSecurity,
        env: Environment,
        jimmerProperties: JimmerProperties,
    ): SecurityFilterChain {
        val jimmerTypeScriptPath = jimmerProperties.client.ts.path ?: throw IllegalStateException("jimmer.client.ts.path is not set")
        http {
            securityMatcher(api("**"), "/error")
            csrf { disable() }
            cors { }
            anonymous { disable() }
            authorizeHttpRequests {
                authorize(POST, api("auth/login"), permitAll)
                authorize(POST, api("user/register"), permitAll)
                authorize(POST, "/error", permitAll)
                authorize(GET, jimmerTypeScriptPath, permitAll)
                authorize(anyRequest, authenticated)
            }
        }

        return http.build()
    }

    @Bean
    fun formLoginFilterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeHttpRequests {
                authorize(anyRequest, authenticated)
            }
            formLogin { }
        }
        return http.build()
    }

    @Bean
    fun passwordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailsService(userRepository: UserRepository): UserDetailsService {
        return SecurityUserDetailsService(userRepository)
    }
}

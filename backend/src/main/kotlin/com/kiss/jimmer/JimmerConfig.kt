package com.kiss.jimmer

import org.babyfish.jimmer.spring.SqlClients
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JimmerConfig {

    @Bean
    fun sqlClient(ctx: ApplicationContext): KSqlClient {
        return SqlClients.kotlin(ctx) {

        }
    }
}

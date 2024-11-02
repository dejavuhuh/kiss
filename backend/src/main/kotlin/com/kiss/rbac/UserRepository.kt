package com.kiss.rbac

import org.babyfish.jimmer.spring.repo.support.AbstractKotlinRepository
import org.babyfish.jimmer.sql.kt.KSqlClient
import org.babyfish.jimmer.sql.kt.ast.expression.eq
import org.springframework.stereotype.Repository

@Repository
class UserRepository(sql: KSqlClient) : AbstractKotlinRepository<User, Long>(sql) {

    fun findByUsername(username: String): User? {
        return sql.createQuery(User::class) {
            where(table.username eq username)
            select(table)
        }.fetchOneOrNull()
    }
}

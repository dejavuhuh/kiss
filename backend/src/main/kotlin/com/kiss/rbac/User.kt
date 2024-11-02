package com.kiss.rbac

import com.kiss.BaseEntity
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.Key
import org.babyfish.jimmer.sql.Table

@Entity
@Table(name = "\"user\"")
interface User : BaseEntity {

    @Key
    val username: String

    val password: String
}

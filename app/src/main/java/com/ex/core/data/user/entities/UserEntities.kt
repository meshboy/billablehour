package com.ex.core.data.user.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ex.core.data.user.domain.UserModel

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-18
 */
@Entity(tableName = "user")
data class DatabaseUser constructor(
    @PrimaryKey
    val id: Long = 1,
    val email: String,
    val password: String
)

fun DatabaseUser.asUserModel(): UserModel {
    return UserModel(
        id = id,
        email = email,
        password = password
    )
}
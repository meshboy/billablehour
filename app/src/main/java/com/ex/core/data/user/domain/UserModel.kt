package com.ex.core.data.user.domain

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-18
 */
data class UserModel constructor(
    val id: Long,
    val email: String,
    val password: String
)
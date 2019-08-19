package com.ex.billablehours.user

import com.ex.core.data.user.entities.DatabaseUser
import com.ex.core.data.user.entities.UserDao
import com.ex.core.data.user.repository.UserRepositoryImpl
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotSame
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

/**
 *@author meshileya seun <mesh@kudi.ai/>
 *@date 2019-08-18
 */
@RunWith(MockitoJUnitRunner::class)
class UserRepositoryTest {

    @Mock
    lateinit var userDao: UserDao

    @Test
    fun user_is_returned_using_correct_email_and_password() {

        val testDatabaseUser = DatabaseUser(email = "test@email.com", password = "1234")

        `when`(userDao.getUserByEmailAndPassword(testDatabaseUser.email, testDatabaseUser.password))
            .thenReturn(testDatabaseUser)

        val mockUserRepository = UserRepositoryImpl(userDao)

        assertEquals(
            mockUserRepository.getUserByEmailAndPassword(email = "test@email.com", password = "1234"),
            testDatabaseUser
        )
    }

    @Test
    fun user_is_not_returned_using_invalid_email_and_password() {
        val testDatabaseUser = DatabaseUser(email = "test@email.com", password = "1234")

        `when`(userDao.getUserByEmailAndPassword(testDatabaseUser.email, testDatabaseUser.password))
            .thenReturn(testDatabaseUser)

        val mockUserRepository = UserRepositoryImpl(userDao)

        assertNotSame(
            mockUserRepository.getUserByEmailAndPassword(email = "user@email.com", password = "1234"),
            testDatabaseUser
        )
    }
}
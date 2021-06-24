package com.example.firstapplication

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(InstantTaskExecutorExtension::class)
class UserListViewModelTest {

    private val userListViewModel = UserListViewModel()

    @Test
    fun `should add user`() {
        //when
        val user = User("test-user-id", "test-user-name", "test-user-email")
        userListViewModel.add(user)

        //then
        assertTrue(userListViewModel.getUsers().value.orEmpty().contains(user))
    }

    @Test
    fun `should remove user`() {
        //given
        val userId = "test-user-id"
        val user = User(userId, "test-user-name", "test-user-email")
        userListViewModel.add(user)

        //when
        userListViewModel.remove(userId)

        //then
        assertFalse(userListViewModel.getUsers().value.orEmpty().contains(user))
    }

    @Test
    fun `should not throw exception when removing not existing user`() {
        //given
        val notExistingUserId = "test-user-id"

        //when
        assertDoesNotThrow { userListViewModel.remove(notExistingUserId) }
    }
}
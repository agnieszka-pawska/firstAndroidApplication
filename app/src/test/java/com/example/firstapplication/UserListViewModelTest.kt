package com.example.firstapplication

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
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
        assertTrue(userListViewModel.users.value?.contains(user) ?: false)
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
        assertFalse(userListViewModel.users.value?.contains(user) ?: false)
    }

    @Test
    fun `should not throw exception when removing not existing user`() {
        //given
        val notExistingUserId = "test-user-id"
        val notExistingUser = User(notExistingUserId, "test-user-name", "test-user-email")

        //when
        userListViewModel.remove(notExistingUserId)

        //then
        assertFalse(userListViewModel.users.value?.contains(notExistingUser) ?: false)
    }
}
package com.example.firstapplication

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModuleFactory {

   val appModule =  module {
       factory { UsersApiClient() }
       single { UserRepository(get()) }
       single { ToDoListRepository(get()) }
       viewModel { UserListViewModel(get()) }
       viewModel { ToDoListViewModel(get()) }
    }
}
package com.example.firstapplication

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModuleFactory {

   val appModule =  module {
       factory { UsersApiClient() }
       single { UserRepository(get()) }
       viewModel { UserListViewModel(get()) }
    }
}
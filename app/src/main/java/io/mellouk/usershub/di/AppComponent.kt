package io.mellouk.usershub.di

import dagger.Component
import io.mellouk.main.di.MainComponent
import io.mellouk.users_list.di.UsersListComponent

@Component
interface AppComponent {
    fun getMainComponent(): MainComponent
    fun getUsersListComponent(): UsersListComponent
}
package io.mellouk.usershub.di

import dagger.Component
import io.mellouk.common_android.di.ApplicationScope
import io.mellouk.main.di.MainComponent
import io.mellouk.users_list.di.UsersListComponent

@ApplicationScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getMainComponent(): MainComponent
    fun getUsersListComponent(): UsersListComponent
}
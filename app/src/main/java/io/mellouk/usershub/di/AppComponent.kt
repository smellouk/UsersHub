package io.mellouk.usershub.di

import dagger.Component
import io.mellouk.common_android.utils.UsersIdlingResource
import io.mellouk.main.di.MainComponent
import io.mellouk.user_profile.di.UserProfileComponent
import io.mellouk.users_list.di.UsersListComponent

@ApplicationScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getMainComponent(): MainComponent
    fun getUsersListComponent(): UsersListComponent
    fun getUserProfileComponent(): UserProfileComponent
    fun getIdlingResource(): UsersIdlingResource
}
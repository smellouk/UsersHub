package io.mellouk.user_profile.di

import dagger.Subcomponent
import io.mellouk.user_profile.UserProfileFragment

@UserProfileScope
@Subcomponent(modules = [ViewModelModule::class, RepositoryModule::class])
interface UserProfileComponent {
    fun inject(fragment: UserProfileFragment)
}

interface UserProfileComponentProvider {
    fun getProfileComponent(): UserProfileComponent
}
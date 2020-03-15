package io.mellouk.users_list.di

import dagger.Subcomponent
import io.mellouk.users_list.UserListFragment

@UsersListScope
@Subcomponent(modules = [ViewModelModule::class, RepositoryModule::class])
interface UsersListComponent {
    fun inject(fragment: UserListFragment)
}

interface UsersListComponentProvider {
    fun getUsersListComponent(): UsersListComponent
}
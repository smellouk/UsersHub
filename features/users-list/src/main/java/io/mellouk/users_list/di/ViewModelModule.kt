package io.mellouk.users_list.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.mellouk.common_android.di.ViewModelKey
import io.mellouk.users_list.UsersListViewModel

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UsersListViewModel::class)
    @UsersListScope
    fun bindMainViewModel(usersListViewModel: UsersListViewModel): ViewModel
}
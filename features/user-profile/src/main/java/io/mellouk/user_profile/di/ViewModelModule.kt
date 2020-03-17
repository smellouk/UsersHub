package io.mellouk.user_profile.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.mellouk.common_android.di.ViewModelKey
import io.mellouk.user_profile.UserProfileViewModel

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserProfileViewModel::class)
    @UserProfileScope
    fun bindMainViewModel(usersListViewModel: UserProfileViewModel): ViewModel
}
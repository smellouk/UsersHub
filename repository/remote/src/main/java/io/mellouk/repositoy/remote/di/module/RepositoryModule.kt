package io.mellouk.repositoy.remote.di.module

import dagger.Module
import dagger.Provides
import io.mellouk.repositoy.remote.network.repositoty.UsersRepository
import io.mellouk.repositoy.remote.network.service.UsersService
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideUsersRepository(usersService: UsersService): UsersRepository =
        UsersRepository(usersService)
}
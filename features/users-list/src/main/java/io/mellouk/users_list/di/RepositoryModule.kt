package io.mellouk.users_list.di

import dagger.Module
import dagger.Provides
import io.mellouk.common_android.domain.mapper.UserMapper
import io.mellouk.repositoy.remote.GitHubRepositories

@Module
class RepositoryModule {
    @UsersListScope
    @Provides
    fun provideUsersRepository(repositories: GitHubRepositories) = repositories.usersRepository

    @UsersListScope
    @Provides
    fun provideUserMapper() = UserMapper()
}
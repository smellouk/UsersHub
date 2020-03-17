package io.mellouk.user_profile.di

import dagger.Module
import dagger.Provides
import io.mellouk.common_android.domain.mapper.UserMapper
import io.mellouk.repositoy.remote.GitHubRepositories

@Module
class RepositoryModule {
    @UserProfileScope
    @Provides
    fun provideUsersRepository(repositories: GitHubRepositories) = repositories.usersRepository


    @UserProfileScope
    @Provides
    fun provideUserMapper() = UserMapper()
}
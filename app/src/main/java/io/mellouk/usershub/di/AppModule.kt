package io.mellouk.usershub.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.mellouk.common_android.di.ApplicationScope
import io.mellouk.repositoy.remote.GitHubRepositories

@Module
class AppModule(
    private val context: Context,
    private val isDebug: Boolean
) {
    @ApplicationScope
    @Provides
    fun provideContext() = context

    @ApplicationScope
    @Provides
    fun provideGitHubRepositories() = GitHubRepositories(isDebug = isDebug)
}
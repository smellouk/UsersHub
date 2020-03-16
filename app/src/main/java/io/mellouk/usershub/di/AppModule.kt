package io.mellouk.usershub.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import io.mellouk.common_android.utils.UsersIdlingResource
import io.mellouk.repositoy.remote.GitHubRepositories
import okhttp3.Interceptor

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
    fun provideChucker() = ChuckerInterceptor(context)

    @ApplicationScope
    @Provides
    fun provideGitHubRepositories(chuckerInterceptor: ChuckerInterceptor) =
        GitHubRepositories(
            isDebug = isDebug,
            debugInterceptors = listOf<Interceptor>(chuckerInterceptor)
        )

    @ApplicationScope
    @Provides
    fun provideIdlingResource(): UsersIdlingResource {
        return UsersIdlingResource()
    }
}
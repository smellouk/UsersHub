package io.mellouk.repositoy.remote

import io.mellouk.repositoy.remote.di.component.DaggerGithubComponent
import io.mellouk.repositoy.remote.di.module.ClientModule
import io.mellouk.repositoy.remote.network.repositoty.UsersRepository
import okhttp3.Interceptor
import javax.inject.Inject

class GitHubRepositories(
    isDebug: Boolean = true,
    debugInterceptors: List<Interceptor> = emptyList()
) {
    @Inject
    lateinit var usersRepository: UsersRepository

    init {
        DaggerGithubComponent.builder()
            .clientModule(ClientModule(isDebug, debugInterceptors))
            .build()
            .apply {
                inject(this@GitHubRepositories)
            }
    }
}
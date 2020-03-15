package io.mellouk.repositoy.remote.di.component

import dagger.Component
import io.mellouk.repositoy.remote.GitHubRepositories
import io.mellouk.repositoy.remote.di.module.ClientModule
import io.mellouk.repositoy.remote.di.module.RepositoryModule
import io.mellouk.repositoy.remote.di.module.ServiceModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ClientModule::class, ServiceModule::class, RepositoryModule::class])
interface GithubComponent {
    fun inject(repositories: GitHubRepositories)
}
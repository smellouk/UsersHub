package io.mellouk.repositoy.remote.di.module

import dagger.Module
import dagger.Provides
import io.mellouk.repositoy.remote.network.service.UsersService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class ServiceModule {
    @Provides
    @Singleton
    fun provideReposService(retrofit: Retrofit): UsersService =
        retrofit.create<UsersService>(UsersService::class.java)
}
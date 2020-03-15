package io.mellouk.usershub

import android.app.Application
import io.mellouk.main.di.MainComponent
import io.mellouk.main.di.MainComponentProvider
import io.mellouk.users_list.di.UsersListComponent
import io.mellouk.users_list.di.UsersListComponentProvider
import io.mellouk.usershub.di.AppComponent
import io.mellouk.usershub.di.DaggerAppComponent

class UsersHubApp : Application(), MainComponentProvider, UsersListComponentProvider {
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }

    override fun getMainComponent(): MainComponent = appComponent.getMainComponent()
    
    override fun getUsersListComponent(): UsersListComponent = appComponent.getUsersListComponent()
}
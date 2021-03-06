package io.mellouk.main.di

import dagger.Subcomponent
import io.mellouk.main.MainActivity

@MainScope
@Subcomponent(modules = [MainViewModelModule::class])
interface MainComponent {
    fun inject(activity: MainActivity)
}

interface MainComponentProvider {
    fun getMainComponent(): MainComponent
}
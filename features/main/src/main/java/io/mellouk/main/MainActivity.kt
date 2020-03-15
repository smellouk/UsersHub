package io.mellouk.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import io.mellouk.common_android.base.BaseActivity
import io.mellouk.main.ViewState.USER_LIST
import io.mellouk.main.ViewState.USER_PROFILE
import io.mellouk.main.di.MainComponentProvider

class MainActivity : BaseActivity<MainComponentProvider, ViewState, Command, MainViewModel>(
    R.layout.activity_main_screen
) {
    private val navController: NavController by lazy {
        Navigation.findNavController(
            this, R.id.navMain
        )
    }

    override fun getViewModelClass() = MainViewModel::class.java

    override fun inject() {
        componentProvider.getMainComponent().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        navController.setGraph(R.navigation.nav_main, savedInstanceState)
    }

    override fun renderViewState(state: ViewState) {
        when (state) {
            USER_LIST -> navigateTo()
            USER_PROFILE -> navigateTo()
        }
    }

    private fun navigateTo() {
        navController.navigate(R.id.user_list_fragment)
    }
}

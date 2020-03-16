package io.mellouk.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import io.mellouk.common_android.base.BaseActivity
import io.mellouk.common_android.exhaustive
import io.mellouk.common_android.utils.Keys.USER_NAME_KEY
import io.mellouk.common_android.utils.MainNavigator
import io.mellouk.main.Command.OpenUserList
import io.mellouk.main.Command.OpenUserProfile
import io.mellouk.main.ViewState.UserProfile
import io.mellouk.main.ViewState.UsersList
import io.mellouk.main.di.MainComponentProvider

class MainActivity : BaseActivity<MainComponentProvider, ViewState, MainViewModel>(
    R.layout.activity_main_screen
), MainNavigator {
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
            is UsersList -> navigateTo(state.destination)
            is UserProfile -> navigateTo(
                state.destination, bundleOf(
                    USER_NAME_KEY to state.userName
                )
            )
        }.exhaustive
    }

    private fun navigateTo(@IdRes destination: Int, bundle: Bundle? = null) {
        navController.navigate(destination, bundle)
    }

    override fun navigateToUsersList() {
        viewModel.onCommand(OpenUserList)
    }

    override fun navigateToUserProfile(userName: String) {
        viewModel.onCommand(OpenUserProfile(userName))
    }
}

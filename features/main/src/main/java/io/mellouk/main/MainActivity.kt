package io.mellouk.main

import android.os.Bundle
import android.os.PersistableBundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import io.mellouk.common_android.base.BaseActivity
import io.mellouk.main.MainViewState.*
import io.mellouk.main.di.MainComponentProvider

class MainActivity : BaseActivity<MainComponentProvider,
        MainViewState, MainViewModel>(R.layout.activity_main_screen) {
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
        viewModel = MainViewModel()
    }

    override fun renderViewState(state: MainViewState) {
        when (state) {
            USER_LIST -> navigateTo()
            USER_PROFILE -> navigateTo()
            NO_INTERNET -> renderNoInternetView()
            ERROR -> renderGeneralErrorView()
        }
    }

    private fun navigateTo() {
        navController.navigate(R.id.user_list_fragment)
    }

    private fun renderGeneralErrorView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun renderNoInternetView() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

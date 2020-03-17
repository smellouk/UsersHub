package io.mellouk.user_profile

import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.squareup.picasso.Picasso
import io.mellouk.common_android.base.BaseFragment
import io.mellouk.common_android.domain.model.User
import io.mellouk.common_android.exhaustive
import io.mellouk.common_android.hide
import io.mellouk.common_android.show
import io.mellouk.common_android.utils.Keys
import io.mellouk.common_android.utils.UsersIdlingResource
import io.mellouk.user_profile.Command.GetUserProfile
import io.mellouk.user_profile.ViewState.*
import io.mellouk.user_profile.di.UserProfileComponentProvider
import io.mellouk.user_profile.domain.GetUserProfileParams
import kotlinx.android.synthetic.main.fragment_user_pofile.*
import javax.inject.Inject

class UserProfileFragment :
    BaseFragment<UserProfileComponentProvider, ViewState, UserProfileViewModel>(
        R.layout.fragment_user_pofile
    ) {

    //Kotlin synthetic is not working as it should
    private lateinit var errorView: View
    private lateinit var btnRetry: View
    private lateinit var tvError: TextView

    @Inject
    lateinit var idlingResource: UsersIdlingResource

    override fun getViewModelClass() = UserProfileViewModel::class.java

    override fun inject() {
        componentProvider.getProfileComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        errorView = view.findViewById(R.id.errorView)
        btnRetry = errorView.findViewById(R.id.btnRetry)
        tvError = errorView.findViewById(R.id.tvError)

        btnRetry.setOnClickListener {
            loadUserProfile()
        }
    }

    override fun renderViewState(state: ViewState) {
        when (state) {
            Initial -> loadUserProfile()
            is UserReady -> renderUserProfile(state.user)
            Loading -> renderLoading()
            is Error -> renderError(state.message)
        }.exhaustive
    }

    private fun loadUserProfile() {
        errorView.hide()
        userViews.hide()
        val username: String =
            arguments?.getString(Keys.USER_NAME_KEY) ?: throw IllegalArgumentException(
                "to use this fragment you need to send username"
            )
        viewModel.onCommand(GetUserProfile(GetUserProfileParams(username)))
        idlingResource.setIdleState(false)
    }

    private fun renderUserProfile(user: User) {
        progress.hide()
        userViews.show()
        Picasso.get()
            .load(user.avatar)
            .placeholder(R.drawable.ic_account_circle_white_24dp)
            .error(R.drawable.ic_warning_white_24dp)
            .into(ivUser)

        tvName.text = "${user.name}\n@${user.username}"
        tvSummary.text = user.summary()
        
        idlingResource.setIdleState(true)
    }

    private fun renderLoading() {
        progress.show()
    }

    private fun renderError(message: String?) {
        progress.hide()
        tvError.text = message
        userViews.hide()
        errorView.show()
    }
}
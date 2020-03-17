package io.mellouk.user_profile

import io.mellouk.common_android.base.BaseCommand
import io.mellouk.user_profile.domain.GetUserProfileParams

sealed class Command : BaseCommand {
    class GetUserProfile(val params: GetUserProfileParams) : Command()
}
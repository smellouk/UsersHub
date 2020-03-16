package io.mellouk.main

import io.mellouk.common_android.base.BaseCommand

sealed class Command : BaseCommand {
    object OpenUserList : Command()
    class OpenUserProfile(val userName: String) : Command()
}
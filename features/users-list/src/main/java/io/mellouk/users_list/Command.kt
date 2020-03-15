package io.mellouk.users_list

import io.mellouk.common_android.base.BaseCommand

sealed class Command : BaseCommand {
    object GetUsers : Command()
}
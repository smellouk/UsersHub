package io.mellouk.users_list

import io.mellouk.common_android.base.BaseCommand
import io.mellouk.users_list.domain.GetUsersListParams

sealed class Command : BaseCommand {
    class GetUsers(val params: GetUsersListParams) : Command()
    class LoadMoreUsers(val params: GetUsersListParams) : Command()
}
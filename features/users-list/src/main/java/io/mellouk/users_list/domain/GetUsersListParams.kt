package io.mellouk.users_list.domain

import io.mellouk.common_android.domain.BaseParams

class GetUsersListParams(
    val page: Int,
    val pageSize: Int
) : BaseParams
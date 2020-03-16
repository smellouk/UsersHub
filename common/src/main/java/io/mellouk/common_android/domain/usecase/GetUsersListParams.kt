package io.mellouk.common_android.domain.usecase

import io.mellouk.common_android.domain.BaseParams

class GetUsersListParams(
    val page: Int,
    val pageSize: Int
) : BaseParams
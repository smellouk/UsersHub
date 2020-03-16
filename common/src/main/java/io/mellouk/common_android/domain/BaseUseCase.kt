package io.mellouk.common_android.domain

import io.reactivex.Observable

interface BaseUseCase<Params : BaseParams, DataState : BaseDataState> {
    fun buildObservable(params: Params): Observable<DataState>
}
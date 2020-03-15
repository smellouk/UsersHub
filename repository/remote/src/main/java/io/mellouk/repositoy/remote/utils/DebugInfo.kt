package io.mellouk.repositoy.remote.utils

import okhttp3.Interceptor

class DebugInfo(val isDebug: Boolean, debugInterceptors: List<Interceptor>)
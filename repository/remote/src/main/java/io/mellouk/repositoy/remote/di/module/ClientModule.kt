package io.mellouk.repositoy.remote.di.module

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.mellouk.repositoy.remote.di.qualifier.HostUrlInfo
import io.mellouk.repositoy.remote.di.qualifier.NetworkTimeOutInfo
import io.mellouk.repositoy.remote.di.qualifier.RetryCountInfo
import io.mellouk.repositoy.remote.utils.Constants
import io.mellouk.repositoy.remote.utils.DebugInfo
import io.mellouk.repositoy.remote.utils.RetryInterceptor
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ClientModule(private val isDebug: Boolean, private val debugInterceptors: List<Interceptor>) {
    @Provides
    @Singleton
    fun isDebug() = DebugInfo(
        isDebug,
        debugInterceptors
    )

    @Provides
    @NetworkTimeOutInfo
    @Singleton
    fun provideNetworkTimeoutInSeconds() = Constants.NETWORK_CONNECTION_TIMEOUT

    @Provides
    @HostUrlInfo
    @Singleton
    fun provideHostUrl() = Constants.HOST_URL

    @Provides
    @Singleton
    fun provideHttpUrl() = Constants.HOST_URL.toHttpUrlOrNull() ?: throw IllegalArgumentException(
        "Wrong url format"
    )

    @Provides
    @RetryCountInfo
    @Singleton
    fun provideApiRetryCount() = Constants.HTTP_RETRY_COUNT

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Singleton
    @Provides
    fun provideRetryInterceptor(@RetryCountInfo retryCount: Int) = RetryInterceptor(retryCount)

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        debugInfo: DebugInfo,
        @NetworkTimeOutInfo networkTimeoutInSeconds: Int,
        loggingInterceptor: HttpLoggingInterceptor,
        retryInterceptor: RetryInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(retryInterceptor)
        .connectTimeout(networkTimeoutInSeconds.toLong(), TimeUnit.SECONDS).apply {
            with(debugInfo) {
                if (isDebug) {
                    addInterceptor(loggingInterceptor)
                    debugInterceptors.forEach { interceptor ->
                        addInterceptor(interceptor)
                    }
                }
            }
        }.build()

    @Provides
    @Singleton
    fun provideRetrofit(
        httpUrl: HttpUrl,
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(httpUrl)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(rxJava2CallAdapterFactory)
        .client(okHttpClient)
        .build()
}
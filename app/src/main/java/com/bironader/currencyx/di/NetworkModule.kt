package com.bironader.currencyx.di

import android.app.Application
import android.content.Context
import com.bironader.currencyx.BuildConfig
import com.bironader.currencyx.Constants
import com.bironader.currencyx.Keys
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(Keys.baseUrl())
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        queryParamInterceptor: Interceptor,
        chuckInterceptor: ChuckInterceptor
    ): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpClientBuilder.addInterceptor(chuckInterceptor)

        }
        httpClientBuilder.addNetworkInterceptor(queryParamInterceptor)
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)

        return httpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideChuckInterceptor(@ApplicationContext appContext: Context): ChuckInterceptor =
        ChuckInterceptor(appContext)

    @Provides
    @Singleton
    fun provideQueryParamInterceptor() = Interceptor { chain ->

        val url = chain.request().url
            .newBuilder()
            .addQueryParameter(Constants.QueryKeys.ACCESS_KEY, Keys.apiKey())
            .build()

        val request = chain.request().newBuilder().url(url = url).build()
        Timber.d(request.url.query.toString())
        chain.proceed(request)
    }
}

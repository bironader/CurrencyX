package com.bironader.currencyx.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

//    @Provides
//    @Singleton
//    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson) = Retrofit.Builder()
//        .baseUrl(baseUrl)
//        .client(okHttpClient)
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .build()


    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }


//        @Provides
//    @Singleton
//    fun provideHeaderInterceptor() = Interceptor { chain ->
//        var request = chain.request()
//        request = request.newBuilder()
//            .addHeader(AUTHORIZATION, "$BEARER $token")
//            .addHeader(ACCEPT, APP_JSON)
//            .addHeader(CONTENT_TYPE, APPLICATION_X_WWWW_FORM)
//            .build()
//        Timber.d(request.headers.toString())
//        chain.proceed(request)
//    }
}

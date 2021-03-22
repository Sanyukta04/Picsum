package com.example.myapplication.di


import com.example.myapplication.util.API_ENDPOINT
import com.example.myapplication.api.ImageService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton

    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(10L, TimeUnit.SECONDS)
                .writeTimeout(10L, TimeUnit.SECONDS)
                .readTimeout(30L, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(API_ENDPOINT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(
                        GsonConverterFactory.create(
                                GsonBuilder()
                                        .setLenient()
                                        .create()
                        )
                )
                .build()
    }

    @Provides
    fun provideImageService(retrofit: Retrofit): ImageService {
        return retrofit.create(ImageService::class.java)
    }

}
package com.example.paging3.di

import android.content.Context
import androidx.room.Room
import com.example.paging3.common.Constant.BASE_URL
import com.example.paging3.common.Constant.BASE_URL_IMG
import com.example.paging3.common.Constant.UNSPLASH_DATABASE
import com.example.paging3.data.local.UnsplashDatabase
import com.example.paging3.data.remote.UnsplashApi
import com.example.paging3.data.remote.UnsplashApiImg
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            // .readTimeout(15, TimeUnit.SECONDS)
            //.connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideUnsplashApi(okHttpClient: OkHttpClient): UnsplashApi {
        // for ignore unspec key
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
        }
        return Retrofit
            .Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()
            .create(UnsplashApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUnsplashApiImages(okHttpClient: OkHttpClient): UnsplashApiImg {
        // for ignore unspec key
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
        }
        return Retrofit
            .Builder()
            .addConverterFactory(json.asConverterFactory(contentType))
           // .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL_IMG)
            .client(okHttpClient)
            .build()
            .create(UnsplashApiImg::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): UnsplashDatabase {
        return Room
            .databaseBuilder(context, UnsplashDatabase::class.java, UNSPLASH_DATABASE)
            .build()
    }
}
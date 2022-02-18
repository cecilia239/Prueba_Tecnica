package com.example.pruebatecnica.data.Repositories

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Repository {
    fun provideOkhttpClient(): OkHttpClient.Builder {
        try {
            return OkHttpClient.Builder()
                .connectTimeout(90, TimeUnit.SECONDS)
                .readTimeout(80, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }


    inline fun <reified T> getRetrofit(
        url: String

    ): T {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .client(provideOkhttpClient().build())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create(T::class.java)
    }

    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }
}
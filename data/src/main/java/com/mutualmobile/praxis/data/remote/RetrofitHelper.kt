package com.mutualmobile.praxis.data.remote

import com.mutualmobile.praxis.data.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

  private val okHttpLoggingInterceptor by lazy {
    HttpLoggingInterceptor().apply {
      level =
        if (BuildConfig.DEBUG)
          HttpLoggingInterceptor.Level.BODY
        else
          HttpLoggingInterceptor.Level.NONE
    }
  }

  fun createOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(okHttpLoggingInterceptor)
        .retryOnConnectionFailure(true)
        .build()
  }

  fun createRetrofitClient(
    okHttpClient: OkHttpClient,
    baseUrl: String
  ): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
  }
}
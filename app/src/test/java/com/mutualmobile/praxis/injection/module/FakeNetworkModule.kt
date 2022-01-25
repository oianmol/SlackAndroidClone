package com.mutualmobile.praxis.injection.module

import com.mutualmobile.praxis.data.remote.JokeApiService
import com.mutualmobile.praxis.data.remote.RetrofitHelper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class FakeNetworkModule {

  @Provides
  @Singleton
  internal fun provideMockWebServer(): MockWebServer {
    var mockWebServer: MockWebServer? = null
    val thread = Thread {
      mockWebServer = MockWebServer()
      mockWebServer?.start()
    }
    thread.start()
    thread.join()
    return mockWebServer!!
  }

  @Provides
  @Singleton
  @Named("mockRootUrl")
  internal fun provideBaseUrl(mockWebServer: MockWebServer): String {
    return mockWebServer.url("/")
        .toString()
  }

  @Provides
  @Singleton
  internal fun provideHttpClient(): OkHttpClient {
    return RetrofitHelper.createOkHttpClient()
  }

  @Provides
  @Singleton
  internal fun provideRetrofit(
    okHttpClient: OkHttpClient,
    @Named("mockRootUrl") rootUrl: String
  ): Retrofit {
    return RetrofitHelper.createRetrofitClient(okHttpClient, rootUrl)
  }

  @Provides
  @Singleton
  internal fun provideJokesApiService(retrofit: Retrofit): JokeApiService {
    return JokeApiService.createRetrofitService(retrofit)
  }

}
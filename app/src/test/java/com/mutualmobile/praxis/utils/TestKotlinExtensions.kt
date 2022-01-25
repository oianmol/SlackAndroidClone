package com.mutualmobile.praxis.utils

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertThrows

fun MockWebServer.enqueueResponse(
  responsePath: String? = null,
  responseCode: Int = 200
) {
  val mockResponse = MockResponse()
      .setBody(FileUtil.loadText("responses/$responsePath"))
      .setResponseCode(responseCode)
  enqueue(mockResponse)
}

inline fun <reified T : Throwable> assertThrows(
  noinline executable: suspend () -> Unit
) {
  assertThrows(T::class.java) {
    runBlocking {
      executable()
    }
  }
}

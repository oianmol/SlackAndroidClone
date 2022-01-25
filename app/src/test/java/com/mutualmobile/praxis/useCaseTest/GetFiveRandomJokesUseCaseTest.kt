package dev.baseio.slackclone.useCaseTest

import dev.baseio.slackclone.base.BaseTest
import dev.baseio.slackclone.data.SafeResult
import dev.baseio.slackclone.domain.usecases.GetFiveRandomJokesUseCase
import dev.baseio.slackclone.injection.component.TestAppComponent
import dev.baseio.slackclone.utils.enqueueResponse
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import javax.inject.Inject

class GetFiveRandomJokesUseCaseTest : BaseTest() {

  override fun injectIntoDagger(testAppComponent: TestAppComponent) {
    testAppComponent.inject(this)
  }

  @Inject
  lateinit var getFiveRandomJokesUseCase: GetFiveRandomJokesUseCase

  @Test
  fun `when api returns success- assert result data contains Jokes`() =
    runBlocking {
      mockWebServer.enqueueResponse("jokes_response.json")

      val result = getFiveRandomJokesUseCase.perform()
      assertEquals(1, mockWebServer.requestCount)
      assert(result is SafeResult.Success)
      assert((result as SafeResult.Success).data.isNotEmpty())
    }

}
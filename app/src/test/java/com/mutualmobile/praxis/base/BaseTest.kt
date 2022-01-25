package dev.baseio.slackclone.base

import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.baseio.slackclone.TestApplication
import dev.baseio.slackclone.injection.component.TestAppComponent
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject

/**
 * Created by Ashish Suman on 09/03/21
 */

@RunWith(AndroidJUnit4::class)
@Config(application = TestApplication::class, sdk = [Build.VERSION_CODES.O_MR1])
abstract class BaseTest {

  @Inject
  lateinit var mockWebServer: MockWebServer

  private lateinit var application: TestApplication

  @Before
  open fun setup() {
    application = ApplicationProvider.getApplicationContext() as TestApplication
    injectIntoDagger(application.provideComponent())
  }

  @After
  open fun tearDown() {
    mockWebServer.shutdown()
  }

  abstract fun injectIntoDagger(testAppComponent: TestAppComponent)

}
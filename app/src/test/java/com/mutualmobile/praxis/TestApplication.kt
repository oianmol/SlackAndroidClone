package dev.baseio.slackclone

import dev.baseio.slackclone.injection.component.DaggerTestAppComponent
import dev.baseio.slackclone.injection.component.TestAppComponent
import dagger.android.DaggerApplication

class TestApplication : DaggerApplication() {

  private val component: TestAppComponent by lazy {
    DaggerTestAppComponent.factory()
        .create(this) as TestAppComponent
  }

  override fun applicationInjector() = component

  fun provideComponent() = component

}
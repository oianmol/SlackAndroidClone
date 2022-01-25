package com.mutualmobile.praxis

import com.mutualmobile.praxis.injection.component.DaggerTestAppComponent
import com.mutualmobile.praxis.injection.component.TestAppComponent
import dagger.android.DaggerApplication

class TestApplication : DaggerApplication() {

  private val component: TestAppComponent by lazy {
    DaggerTestAppComponent.factory()
        .create(this) as TestAppComponent
  }

  override fun applicationInjector() = component

  fun provideComponent() = component

}
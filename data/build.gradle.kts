plugins {
  id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
  id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
  id(BuildPlugins.KOTLIN_KAPT)
  id(BuildPlugins.DAGGER_HILT)
}

android {
  compileSdk = ProjectProperties.COMPILE_SDK

  defaultConfig {
    minSdk = (ProjectProperties.MIN_SDK)
    targetSdk = (ProjectProperties.TARGET_SDK)
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
}

// Required for annotation processing plugins like Dagger
kapt {
  generateStubs = true
  correctErrorTypes = true
}

dependencies {

  implementation("com.github.vatbub:randomusers:1.3"){
    exclude("com.google.guava")
  }
  implementation(project(":common"))
  implementation(project(":domain"))
  /*Kotlin*/
  implementation(Lib.Kotlin.KT_STD)
  implementation(Lib.Async.COROUTINES)

  /* Paging */
  implementation(Lib.Paging.PAGING_3)
  /* Room */
  implementation(Lib.Room.roomRuntime)
  kapt(Lib.Room.roomCompiler)
  implementation(Lib.Room.roomKtx)
  implementation(Lib.Room.roomPaging)

  /* Networking */
  implementation(Lib.Networking.RETROFIT)
  implementation(Lib.Networking.RETROFIT_GSON)
  implementation(Lib.Networking.LOGGING)

  implementation(Lib.Serialization.GSON)

  /* Dependency Injection */
  implementation(Lib.Di.hiltAndroid)
  kapt(Lib.Di.hiltAndroidCompiler)
}
plugins {
  id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
  id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
  id(BuildPlugins.KOTLIN_KAPT)
  id(BuildPlugins.DAGGER_HILT)
  id(BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
  id(BuildPlugins.SAFE_ARGS_KOTLIN)
  id("org.jlleitschuh.gradle.ktlint")
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

  packagingOptions {
    resources.excludes.add("META-INF/LICENSE.txt")
    resources.excludes.add("META-INF/NOTICE.txt")
    resources.excludes.add("LICENSE.txt")
    resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  buildFeatures {
    dataBinding = true
    compose = true
  }

  composeOptions {
    kotlinCompilerExtensionVersion = Lib.Android.COMPOSE_COMPILER_VERSION
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }
}

// Required for annotation processing plugins like Dagger
kapt {
  generateStubs = true
  correctErrorTypes = true
}

dependencies {

  api(project(":ui-authentication"))
  implementation(project(":data"))
  implementation(project(":domain"))
  implementation(project(":common"))
  implementation(project(":commonui"))
  implementation(project(":navigator"))

  api(Lib.Android.APP_COMPAT)
  api(Lib.Kotlin.KTX_CORE)
  api(Lib.Android.FRAGMENT_KTX)
  api(Lib.Android.NAVIGATION_FRAGMENT)
  api(Lib.Kotlin.DATE_TIME)

  /* Compose */
  implementation(Lib.Android.COMPOSE_NAVIGATION)
  implementation(Lib.Android.CONSTRAINT_LAYOUT_COMPOSE)
  implementation(Lib.Android.ACCOMPANIST_INSETS)

  /* DI */
  api(Lib.Di.hilt)
  api(Lib.Di.viewmodel)

  kapt(Lib.Di.hiltCompiler)
  kapt(Lib.Di.hiltAndroidCompiler)

  /* Logger */
  api(Lib.Logger.TIMBER)

  /* Async */
  api(Lib.Async.COROUTINES)
  api(Lib.Async.COROUTINES_ANDROID)

  /* PAGING */
  implementation(Lib.Paging.PAGING_3)

  /* GLIDE */
  implementation(Lib.Glide.GLIDE)
  kapt(Lib.Glide.GLIDE_COMPILER)

  /* Testing */
  testImplementation(TestLib.JUNIT)
  testImplementation(TestLib.CORE_TEST)
  testImplementation(TestLib.ANDROID_JUNIT)
  testImplementation(TestLib.ARCH_CORE)
  testImplementation(TestLib.MOCK_WEB_SERVER)
  testImplementation(TestLib.ROBO_ELECTRIC)
  testImplementation(TestLib.COROUTINES)
  testImplementation(TestLib.MOCKK)
}
plugins {
  id(BuildPlugins.ANDROID_APPLICATION_PLUGIN)
}
subprojects {
  apply {
    from("signing.gradle.kts")
  }
}

android {
  buildTypes {
    getByName("release") {
      isDebuggable = false
      versionNameSuffix = "-release"

      isMinifyEnabled = false
      isShrinkResources = true

      proguardFiles(
        getDefaultProguardFile("proguard-android.txt"), "proguard-common.txt",
        "proguard-specific.txt"
      )
      signingConfig = signingConfigs.getByName("release")
    }
    getByName("debug") {
      isDebuggable = true
      versionNameSuffix = "-debug"
      applicationIdSuffix = ".debug"
      signingConfig = signingConfigs.getByName("debug")
    }
  }
}

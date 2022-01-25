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
      debuggable = false
      versionNameSuffix = "-release"

      isMinifyEnabled = false
      shrinkResources = true

      proguardFiles(
          getDefaultProguardFile("proguard-android.txt"), "proguard-common.txt",
          "proguard-specific.txt"
      )
      signingConfig(
          signingConfigs.release
              // buildConfigField "boolean", "ENABLE_LOGGING", "false"
      )
    }
    getByName("debug") {
      debuggable = true
      versionNameSuffix = "-debug"
      applicationIdSuffix = ".debug"
      signingConfig(
          signingConfigs.debug
              // buildConfigField "boolean", "ENABLE_LOGGING", "true"
      )
    }
  }
}

plugins {
  id(BuildPlugins.ANDROID_APPLICATION_PLUGIN)
}

  android {
    signingConfigs {
      getByName("release") {
        keyAlias = "praxis-release"
        keyPassword = "ITHOmptI"
        storeFile(file("keystore/praxis-release.jks"))
        storePassword = "PoTHatHR"
      }

      getByName("debug") {
        keyAlias = "praxis-debug"
        keyPassword = "utherNiC"
        storeFile(file("keystore/praxis-debug.jks"))
        storePassword = "uRgeSCIt"
      }
    }
  }
}
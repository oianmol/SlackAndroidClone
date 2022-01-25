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
    /*Kotlin*/
    api(Lib.Kotlin.KT_STD)

    /* Dependency Injection */
    api(Lib.Di.hilt)
    api(Lib.Di.hiltNavigationCompose)
    api(Lib.Di.viewmodel)

    kapt(Lib.Di.hiltCompiler)
    kaptTest(Lib.Di.hiltCompiler)
    kapt(Lib.Di.hiltAndroidCompiler)
    kaptTest(Lib.Di.hiltAndroidCompiler)
}
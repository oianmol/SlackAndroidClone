plugins {
    id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
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
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Lib.Android.COMPOSE_COMPILER_VERSION
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
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
    api(Lib.Kotlin.KTX_CORE)
    /* Android Designing and layout */
    api(Lib.Android.MATERIAL_DESIGN)
    api(Lib.Android.COMPOSE_UI)
    api(Lib.Android.COIL_COMPOSE)
    api(Lib.Android.COMPOSE_MATERIAL)
    api(Lib.Android.COMPOSE_TOOLING)
    debugApi(Lib.Android.COMPOSE_DEBUG_TOOLING)
    api(Lib.Android.ACTIVITY_COMPOSE)

    /* Dependency Injection */
    api(Lib.Di.hilt)
    kapt(Lib.Di.hiltAndroidCompiler)
}
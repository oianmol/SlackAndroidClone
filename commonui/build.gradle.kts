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
    implementation(Lib.Kotlin.KT_STD)
    implementation(Lib.Kotlin.KTX_CORE)
    /* Android Designing and layout */
    implementation(Lib.Android.MATERIAL_DESIGN)
    implementation(Lib.Android.COMPOSE_UI)
    implementation(Lib.Android.CONSTRAINT_LAYOUT_COMPOSE)
    implementation(Lib.Android.ACCOMPANIST_SYSTEM_UI_CONTROLLER)
    implementation(Lib.Android.COIL_COMPOSE)
    implementation(Lib.Android.COMPOSE_MATERIAL)
    implementation(Lib.Android.COMPOSE_TOOLING)
    debugImplementation(Lib.Android.COMPOSE_DEBUG_TOOLING)
    implementation(Lib.Android.ACTIVITY_COMPOSE)

    /* Dependency Injection */
    implementation(Lib.Di.hiltAndroid)
    kapt(Lib.Di.hiltAndroidCompiler)
}
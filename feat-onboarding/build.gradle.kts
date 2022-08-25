plugins {
    id(BuildPlugins.ANDROID_LIBRARY_PLUGIN)
    id(BuildPlugins.KOTLIN_ANDROID_PLUGIN)
    id(BuildPlugins.KOTLIN_KAPT)
    id(BuildPlugins.DAGGER_HILT)
    id(BuildPlugins.KOTLIN_PARCELABLE_PLUGIN)
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Lib.Android.COMPOSE_COMPILER_VERSION
    }
    packagingOptions {
        resources.excludes.add("META-INF/LICENSE.txt")
        resources.excludes.add("META-INF/NOTICE.txt")
        resources.excludes.add("LICENSE.txt")
        resources.excludes.add( "/META-INF/{AL2.0,LGPL2.1}")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
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
    /*Kotlin*/
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation(project(":navigator"))
    implementation(project(":commonui"))

    implementation(Lib.Android.COMPOSE_UI)
    implementation(Lib.Android.COIL_COMPOSE)
    implementation(Lib.Android.COMPOSE_MATERIAL)
    implementation(Lib.Android.RUNTIME_COMPOSE)
    implementation(Lib.Android.ACCOMPANIST_SYSTEM_UI_CONTROLLER)
    implementation(Lib.Android.COMPOSE_UI)
    implementation(Lib.Android.COMPOSE_TOOLING)
    debugImplementation(Lib.Android.COMPOSE_DEBUG_TOOLING)
    implementation(Lib.Android.ACTIVITY_COMPOSE)
    implementation(Lib.Android.CONSTRAINT_LAYOUT_COMPOSE)

    implementation(Lib.Android.APP_COMPAT)
    implementation(Lib.Kotlin.KTX_CORE)

    /*DI*/
    implementation(Lib.Di.hiltAndroid)
    implementation(Lib.Di.hiltNavigationCompose)

    kapt(Lib.Di.hiltCompiler)
    kapt(Lib.Di.hiltAndroidCompiler)

    /* Logger */
    implementation(Lib.Logger.TIMBER)
    /* Async */
    implementation(Lib.Async.COROUTINES)
    implementation(Lib.Async.COROUTINES_ANDROID)

    testImplementation(TestLib.JUNIT)
    testImplementation(TestLib.CORE_TEST)
    testImplementation(TestLib.ANDROID_JUNIT)
    testImplementation(TestLib.ARCH_CORE)
    testImplementation(TestLib.MOCK_WEB_SERVER)
    testImplementation(TestLib.ROBO_ELECTRIC)
    testImplementation(TestLib.COROUTINES)
    testImplementation(TestLib.MOCKK)
    testImplementation(TestLib.TURBINE)
}
plugins {
  id("java-library")
  id("org.jetbrains.kotlin.jvm")
}

java {
  sourceCompatibility = JavaVersion.VERSION_1_8
  targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
  implementation(Lib.Kotlin.KT_STD)
  implementation(Lib.Async.COROUTINES)
  implementation("androidx.paging:paging-common-ktx:3.1.0")
}


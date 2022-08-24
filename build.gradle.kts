// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
  repositories {
    google()
    maven("https://plugins.gradle.org/m2/")
  }
  dependencies {
    classpath(BuildPlugins.TOOLS_BUILD_GRADLE)
    classpath(BuildPlugins.DAGGER_HILT_PLUGIN)
    classpath(BuildPlugins.KOTLIN_GRADLE_PLUGIN)
    classpath(kotlin("serialization", version = Lib.Kotlin.KOTLIN_VERSION))
    classpath(BuildPlugins.KTLINT_GRADLE_PLUGIN)
  }
}

allprojects {
  repositories {
    google()
    mavenCentral()
  }

  tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
    kotlinOptions.freeCompilerArgs += listOf( "-Xopt-in=kotlin.RequiresOptIn",
      "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi")
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
  }
}

tasks.register("clean")
    .configure {
      delete(rootProject.buildDir)
    }

apply(from = teamPropsFile("git-hooks.gradle.kts"))

fun teamPropsFile(propsFile: String): File {
  val teamPropsDir = file("team-props")
  return File(teamPropsDir, propsFile)
}
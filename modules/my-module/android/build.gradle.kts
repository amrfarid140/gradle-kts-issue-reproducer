apply plugin: 'com.android.library'

group = 'expo.modules.mymodule'
version = '0.6.0'

def expoModulesCorePlugin = new File(project(":expo-modules-core").projectDir.absolutePath, "ExpoModulesCorePlugin.gradle")
apply from: expoModulesCorePlugin
applyKotlinExpoModulesCorePlugin()
useCoreDependencies()
useExpoPublishing()

// If you want to use the managed Android SDK versions from expo-modules-core, set this to true.
// The Android SDK versions will be bumped from time to time in SDK releases and may introduce breaking changes in your module code.
// Most of the time, you may like to manage the Android SDK versions yourself.
def useManagedAndroidSdkVersions = false
if (useManagedAndroidSdkVersions) {
  useDefaultAndroidSdkVersions()
} else {
  buildscript {
    // Simple helper that allows the root project to override versions declared by this library.
    ext.safeExtGet = { prop, fallback ->
      rootProject.ext.has(prop) ? rootProject.ext.get(prop) : fallback
    }
  }
  project.android {
    compileSdkVersion safeExtGet("compileSdkVersion", 34)
    defaultConfig {
      minSdkVersion safeExtGet("minSdkVersion", 21)
      targetSdkVersion safeExtGet("targetSdkVersion", 34)
    }
  }
}

android {
  namespace "expo.modules.mymodule"
  defaultConfig {
    versionCode 1
    versionName "0.6.0"
  }
  lintOptions {
    abortOnError false
  }
}

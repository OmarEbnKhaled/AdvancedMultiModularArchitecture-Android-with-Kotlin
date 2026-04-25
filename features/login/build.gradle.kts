import dependencies.androidTestImplementationDependencies
import dependencies.androidx
import dependencies.debugImplementationDependencies
import dependencies.hilt
import dependencies.room
import dependencies.testImplementationDependencies
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.minafarid.login"
}

dependencies {
  androidx()
  hilt()
  room()

  testImplementationDependencies()
  androidTestImplementationDependencies()
  debugImplementationDependencies()
}

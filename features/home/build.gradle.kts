import deps.androidTestImplementationDependencies
import deps.androidx
import deps.debugImplementationDependencies
import deps.hilt
import deps.room
import deps.testImplementationDependencies
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.minafarid.home"
}

dependencies {
  androidx()
  hilt()
  room()

  testImplementationDependencies()
  androidTestImplementationDependencies()
  debugImplementationDependencies()
}

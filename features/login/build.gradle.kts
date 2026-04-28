import deps.androidTestImplementationDependencies
import deps.androidx
import deps.debugImplementationDependencies
import deps.hilt
import deps.retrofit
import deps.room
import deps.testImplementationDependencies
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

  retrofit()

  testImplementationDependencies()
  androidTestImplementationDependencies()
  debugImplementationDependencies()
}

import dependencies.androidTestImplementationDependencies
import dependencies.debugImplementationDependencies
import dependencies.testImplementationDependencies
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.minafarid.data"
}

dependencies {
  testImplementationDependencies()
  androidTestImplementationDependencies()
  debugImplementationDependencies()
}

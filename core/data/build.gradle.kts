import deps.androidTestImplementationDependencies
import deps.debugImplementationDependencies
import deps.testImplementationDependencies
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

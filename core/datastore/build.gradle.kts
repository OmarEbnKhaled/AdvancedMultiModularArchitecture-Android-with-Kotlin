import deps.androidTestImplementationDependencies
import deps.dataStore
import deps.debugImplementationDependencies
import deps.testImplementationDependencies
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.minafarid.datastore"
}

dependencies {
  dataStore()

  testImplementationDependencies()
  androidTestImplementationDependencies()
  debugImplementationDependencies()
}

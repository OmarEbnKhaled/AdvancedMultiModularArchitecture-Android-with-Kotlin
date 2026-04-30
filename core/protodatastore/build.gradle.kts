import deps.androidTestImplementationDependencies
import deps.debugImplementationDependencies
import deps.protoDataStore
import deps.testImplementationDependencies
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.minafarid.protodatastore"
}

dependencies {
  protoDataStore()

  testImplementationDependencies()
  androidTestImplementationDependencies()
  debugImplementationDependencies()
}

import deps.androidTestImplementationDependencies
import deps.dataStore
import deps.debugImplementationDependencies
import deps.hilt
import deps.okHttp
import deps.protoDataStore
import deps.protoDatastoreModule
import deps.retrofit
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
  protoDatastoreModule()

  okHttp()
  retrofit()
  hilt()
  dataStore()

  testImplementationDependencies()
  androidTestImplementationDependencies()
  debugImplementationDependencies()
}

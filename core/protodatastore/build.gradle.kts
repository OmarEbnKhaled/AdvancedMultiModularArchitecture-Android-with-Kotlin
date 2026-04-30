import deps.Dependencies.protoBuffArtifact
import deps.androidTestImplementationDependencies
import deps.debugImplementationDependencies
import deps.hilt
import deps.protoDataStore
import deps.testImplementationDependencies
import plugs.SharedLibraryGradlePlugin

plugins {
  id(plugs.BuildPlugins.ANDROID_LIBRARY)
  id(plugs.BuildPlugins.GOOGLE_PROTOBUF)
}

apply<SharedLibraryGradlePlugin>()

android {
  namespace = "com.minafarid.protodatastore"

  protobuf {
    protoc {
      artifact = protoBuffArtifact
    }
    generateProtoTasks {
      all().forEach { task ->
        task.plugins {
          create("kotlin").apply {
            option("lite")
          }
        }
        task.plugins {
          create("java").apply {
            option("lite")
          }
        }
      }
    }
  }
}

dependencies {
  protoDataStore()
  hilt()

  testImplementationDependencies()
  androidTestImplementationDependencies()
  debugImplementationDependencies()
}

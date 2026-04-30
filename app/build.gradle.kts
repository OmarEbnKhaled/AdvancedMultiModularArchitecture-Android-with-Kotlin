import build.BuildConfig
import build.BuildCreator
import build.BuildDimensions
import deps.androidTestImplementationDependencies
import deps.androidx
import deps.dataModule
import deps.dataStore
import deps.datastoreModule
import deps.debugImplementationDependencies
import deps.domainModule
import deps.hilt
import deps.homeModule
import deps.loginModule
import deps.okHttp
import deps.presentationModule
import deps.protoDatastoreModule
import deps.retrofit
import deps.room
import deps.testImplementationDependencies
import flavors.BuildFlavor
import release.ReleaseConfig
import signing.BuildSigning
import signing.SigningTypes
import test.TestBuildConfig

plugins {
  id(plugs.BuildPlugins.ANDROID_APPLICATION)
  id(plugs.BuildPlugins.KOTLIN_ANDROID)
  id(plugs.BuildPlugins.ANDROID)
  id(plugs.BuildPlugins.KAPT)
  id(plugs.BuildPlugins.HILT) version deps.DependenciesVersions.HILT

  id(plugs.BuildPlugins.KTLINT)
  id(plugs.BuildPlugins.SPOTLESS)
  id(plugs.BuildPlugins.DETEKT)
  id(plugs.BuildPlugins.UPDATE_DEPENDENCIES_VERSIONS)
  id(plugs.BuildPlugins.DOKKA)
}

android {
  namespace = BuildConfig.APP_ID
  compileSdk = BuildConfig.COMPILE_SDK_VERSION

  defaultConfig {
    applicationId = BuildConfig.APP_ID
    minSdk = BuildConfig.MIN_SDK_VERSION
    targetSdk = BuildConfig.TARGET_SDK_VERSION
    versionCode = ReleaseConfig.VERSION_CODE
    versionName = ReleaseConfig.VERSION_NAME

    testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
    vectorDrawables {
      useSupportLibrary = true
    }
  }

  signingConfigs {
    BuildSigning.Release(project).create(this)
    BuildSigning.ReleaseExternalQA(project).create(this)
    BuildSigning.Debug(project).create(this)
  }

  buildTypes {
    BuildCreator.Release(project).create(this).apply {
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro",
      )
      signingConfig = signingConfigs.getByName(SigningTypes.RELEASE)
    }
    BuildCreator.Debug(project).create(this).apply {
      signingConfig = signingConfigs.getByName(SigningTypes.DEBUG)
    }
    BuildCreator.ReleaseExternalQA(project).create(this).apply {
      signingConfig = signingConfigs.getByName(SigningTypes.RELEASE_EXTERNAL_QA)
    }
  }

  flavorDimensions.add(BuildDimensions.APP)
  flavorDimensions.add(BuildDimensions.STORE)

  productFlavors {
    BuildFlavor.Google.create(this)
    BuildFlavor.Huawei.create(this)
    BuildFlavor.Driver.create(this)
    BuildFlavor.Client.create(this)
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }
  buildFeatures {
    compose = true
    buildConfig = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.1"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  dataModule()
  domainModule()
  presentationModule()
  datastoreModule()
  protoDatastoreModule()

  loginModule()
  homeModule()

  androidx()
  hilt()
  room()
  okHttp()
  retrofit()
  dataStore()

  testImplementationDependencies()
  androidTestImplementationDependencies()
  debugImplementationDependencies()
}

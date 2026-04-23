import dependencies.androidTestImplementationDependencies
import dependencies.androidx
import dependencies.debugImplementationDependencies
import dependencies.hilt
import dependencies.room
import dependencies.testImplementationDependencies

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
    id(plugs.BuildPlugins.KOTLIN_ANDROID)
    kotlin(plugs.BuildPlugins.KAPT)
}

android {
    namespace = "com.minafarid.login"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    androidx()
    hilt()
    room()

    testImplementationDependencies()
    androidTestImplementationDependencies()
    debugImplementationDependencies()
}
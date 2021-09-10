val kotlin_version: String by extra
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("kotlin-android")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
}
apply {
    plugin("kotlin-android")
}

android {
    compileSdkVersion(Apps.compileSdk)
    buildToolsVersion = Apps.buildToolsVersion

    defaultConfig {
        minSdkVersion(Apps.minSdk)
        targetSdkVersion(Apps.targetSdk)
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = true
            consumerProguardFiles("education-proguard-rules.pro")
        }

        getByName("release") {
            isMinifyEnabled = true
            consumerProguardFiles("education-proguard-rules.pro")
        }
    }
    buildFeatures {
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
    implementation(Libs.kotlin)
    implementation(Libs.appcompat)
    implementation(Libs.coreKtx)
    implementation(Libs.constraintLayout)

    // Hilt
    implementation(Libs.hiltCore)
    implementation(Libs.hiltCommon)
    implementation(Libs.hiltViewModelLifecycle)
    kapt(Libs.hiltDaggerAndroidCompiler)
    kapt(Libs.hiltCompiler)

    // Navigation
    implementation(Libs.navComponentFragment)
    implementation(Libs.navComponentUi)

    // Lifecycle
    implementation(Libs.lifecycleViewModel)
    implementation(Libs.lifecycleLiveDataKtx)
    implementation(Libs.lifecycleCommon)
    implementation(Libs.lifecycleExtension)

    implementation(Libs.gson)
    implementation(Libs.lottie)

    implementation(project(Modules.central))
}
repositories {
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    mavenCentral()
}
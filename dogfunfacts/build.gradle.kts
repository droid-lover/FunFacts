val kotlin_version: String by extra
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = true
            buildConfigField(BuildConfigType.boolean, BuildConfigFields.isLogIn, true.toString())
            buildConfigField(
                    BuildConfigType.string,
                    BuildConfigFields.dogApiBase,
                    BuildConfigValues.dogApiBaseUrl
            )

            buildConfigField(
                    BuildConfigType.string,
                    BuildConfigFields.catApiBase,
                    BuildConfigValues.catApiBaseUrl
            )
        }
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

    // Okhttp
    api(Libs.okHttp)
    api(Libs.okHttpLogging)

    // Hilt
    implementation(Libs.hiltCore)
    implementation(Libs.hiltCommon)
    implementation(Libs.hiltViewModelLifecycle)
    kapt(Libs.hiltDaggerAndroidCompiler)
    kapt(Libs.hiltCompiler)


    // Retrofit
    api(Libs.retrofit)

    // Gson
    implementation(Libs.gson)
    implementation(Libs.gsonConverter)

    // Lifecycle
    implementation(Libs.lifecycleViewModel)
    implementation(Libs.lifecycleLiveDataKtx)
    implementation(Libs.lifecycleCommon)
    implementation(Libs.lifecycleExtension)

}
repositories {
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    mavenCentral()
}
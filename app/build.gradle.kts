plugins {
    id("com.android.application")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

android {
    compileSdk = configs.ProjectConfig.compileSdk

    defaultConfig {
        applicationId = configs.ProjectConfig.appId
        minSdk = configs.ProjectConfig.minSdk
        targetSdk = configs.ProjectConfig.targetSdk
        versionCode = configs.ProjectConfig.versionCode
        versionName = configs.ProjectConfig.versionName

        testInstrumentationRunner = "com.plcoding.calorytracker.HiltTestRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        compose = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = dependencies.Compose.composeCompilerVersion
    }
    packagingOptions {
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
        exclude("**/attach_hotspot_windows.dll")
        exclude("META-INF/licenses/ASM")
    }
}

dependencies {
    implementation(dependencies.Compose.compiler)
    implementation(dependencies.Compose.ui)
    implementation(dependencies.Compose.uiToolingPreview)
    implementation(dependencies.Compose.hiltNavigationCompose)
    implementation(dependencies.Compose.material)
    implementation(dependencies.Compose.runtime)
    implementation(dependencies.Compose.navigation)
    implementation(dependencies.Compose.viewModelCompose)
    implementation(dependencies.Compose.activityCompose)

    implementation(dependencies.DaggerHilt.hiltAndroid)
    kapt(dependencies.DaggerHilt.hiltCompiler)

    implementation(project(configs.Modules.core))
    implementation(project(configs.Modules.coreUi))
    implementation(project(configs.Modules.onboardingPresentation))
    implementation(project(configs.Modules.onboardingDomain))
    implementation(project(configs.Modules.trackerPresentation))
    implementation(project(configs.Modules.trackerDomain))
    implementation(project(configs.Modules.trackerData))

    implementation(AndroidX.coreKtx)
    implementation(AndroidX.appCompat)

    implementation(dependencies.Coil.coilCompose)

    implementation(dependencies.Google.material)

    implementation(dependencies.Retrofit.okHttp)
    implementation(dependencies.Retrofit.retrofit)
    implementation(dependencies.Retrofit.okHttpLoggingInterceptor)
    implementation(dependencies.Retrofit.moshiConverter)

    kapt(dependencies.Room.roomCompiler)
    implementation(dependencies.Room.roomKtx)
    implementation(dependencies.Room.roomRuntime)

    testImplementation(dependencies.Testing.junit4)
    testImplementation(dependencies.Testing.junitAndroidExt)
    testImplementation(dependencies.Testing.truth)
    testImplementation(dependencies.Testing.coroutines)
    testImplementation(dependencies.Testing.turbine)
    testImplementation(dependencies.Testing.composeUiTest)
    testImplementation(dependencies.Testing.mockk)
    testImplementation(dependencies.Testing.mockWebServer)

    androidTestImplementation(dependencies.Testing.junit4)
    androidTestImplementation(dependencies.Testing.junitAndroidExt)
    androidTestImplementation(dependencies.Testing.truth)
    androidTestImplementation(dependencies.Testing.coroutines)
    androidTestImplementation(dependencies.Testing.turbine)
    androidTestImplementation(dependencies.Testing.composeUiTest)
    androidTestImplementation(dependencies.Testing.mockkAndroid)
    androidTestImplementation(dependencies.Testing.mockWebServer)
    androidTestImplementation(dependencies.Testing.hiltTesting)
    kaptAndroidTest(dependencies.DaggerHilt.hiltCompiler)
    androidTestImplementation(dependencies.Testing.testRunner)
}

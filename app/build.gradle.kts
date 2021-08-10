
plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Android.COMPILE_SDK

    defaultConfig {
        applicationId = Android.APPLICATION_ID
        minSdk = Android.MIN_SDK
        targetSdk = Android.TARGET_SDK
        versionCode = Android.VERSION_CODE
        versionName = Android.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        compose = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    externalNativeBuild {
        cmake {
            path = file("CMakeLists.txt")
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(Module.DOMAIN))

    implementation(AndroidX.APP_COMPAT)
    implementation(AndroidX.CORE_KTX)
    implementation(AndroidX.LIFECYCLE)
    implementation (AndroidX.WORKER)

    implementation(ThirdParty.RETROFIT)
    implementation(ThirdParty.GSON)
    implementation(ThirdParty.OKHTTP)
    implementation(ThirdParty.TIMBER)
    implementation(ThirdParty.GSON_CONVERTER)


    implementation(Google.MATERIAL)

    implementation(Hilt.HILT)
    kapt(Hilt.HILT_COMPILER)
    implementation("androidx.hilt:hilt-work:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    
    implementation(Compose.ACTIVITY)
    implementation(Compose.FOUNDATION)
    implementation(Compose.VIEW_MODEL)
    implementation(Compose.NAVIGATION)
    implementation(Compose.UI)
    implementation(Compose.MATERIAL)
    implementation(Compose.LIFE_CYCLE)
    implementation(Compose.PREVIEW)
    implementation(Compose.TOOLING)
    implementation(Compose.HILT_NAVIGATION)

    implementation(Room.KTX)
    implementation(Room.ANDROID)
    implementation(Room.COROUTINES)
    annotationProcessor(Room.COMPILER)
    kapt(Room.COMPILER)



//    androidTestImplementation("androidx.compose.ui:ui-test-junit4:${rootProject.extra["compose_version"]}")
    debugImplementation (ThirdParty.CHUCK)
    releaseImplementation (ThirdParty.CHUCK_NO_OP)
    testImplementation ("junit:junit:4.+")
    androidTestImplementation ("androidx.test.ext:junit:1.1.2")


}
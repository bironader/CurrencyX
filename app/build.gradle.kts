plugins {
    id ("com.android.application")
    id ("kotlin-android")
}

android {
    compileSdk =  Android.COMPILE_SDK

    defaultConfig {
        applicationId = Android.APPLICATION_ID
        minSdk = Android.MIN_SDK
        targetSdk = Android.TARGET_SDK
        versionCode = Android.VERSION_CODE
        versionName  = Android.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled  =  false
        }
    }
    compileOptions {
        sourceCompatibility =  JavaVersion.VERSION_1_8
        targetCompatibility  = JavaVersion.VERSION_1_8
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
}

dependencies {

    implementation(project(Module.DOMAIN))
    implementation (AndroidX.APP_COMPAT)
    implementation (AndroidX.CORE_KTX)
    implementation (AndroidX.LIFECYCLE)
    implementation (ThirdParty.RETROFIT)
    implementation (ThirdParty.GSON)
    implementation (ThirdParty.OKHTTP)
    implementation (ThirdParty.TIMBER)
    implementation (ThirdParty.GSON_CONVERTER)
    implementation (Hilt.HILT)






    implementation ("com.google.android.material:material:1.3.0")
    testImplementation ("junit:junit:4.+")
    androidTestImplementation ("androidx.test.ext:junit:1.1.2")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.3.0")
}
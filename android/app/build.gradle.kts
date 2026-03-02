import java.io.File

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dev.flutter.flutter-gradle-plugin")
}

android {
    namespace = "com.example.devops_app"
    compileSdk = flutter.compileSdkVersion

    defaultConfig {
        applicationId = "com.example.devops_app"
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName
    }

    signingConfigs {
        create("release") {
            val keystorePath = System.getenv("KEYSTORE_PATH")
            val keystorePassword = System.getenv("KEYSTORE_PASSWORD")
            val keyAliasEnv = System.getenv("KEY_ALIAS")
            val keyPasswordEnv = System.getenv("KEY_PASSWORD")

            if (
                !keystorePath.isNullOrEmpty() &&
                !keystorePassword.isNullOrEmpty() &&
                !keyAliasEnv.isNullOrEmpty() &&
                !keyPasswordEnv.isNullOrEmpty()
            ) {
                storeFile = file(keystorePath)
                storePassword = keystorePassword
                keyAlias = keyAliasEnv
                keyPassword = keyPasswordEnv
            }
        }
    }

    buildTypes {
    release {
        // REQUIRED pairing
        isMinifyEnabled = true
        isShrinkResources = true

        // Default Flutter Proguard rules
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )

        signingConfig = signingConfigs.getByName("release")
    }
}

flutter {
    source = "../.."
}
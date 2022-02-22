plugins {
    id("com.android.library")
    id("com.aliucord.gradle")
}

aliucord {
    projectType.set(com.aliucord.gradle.ProjectType.INJECTOR)
}

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 21
        targetSdk = 30

        // added
        buildConfigField("boolean", "ENABLE_UPDATER", "false")
        buildConfigField("boolean", "ENABLE_CRASH_HANDLER", "false")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation("androidx.appcompat:appcompat:1.3.1")
    implementation("com.google.android.material:material:1.4.0")
    implementation("androidx.browser:browser:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
    implementation("androidx.exifinterface:exifinterface:1.3.3")

    discord("com.discord:discord:113009")
}

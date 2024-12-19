plugins {
    id("com.android.application") // Menggunakan plugin Android
    id("com.google.gms.google-services") // Menggunakan plugin Google Services untuk Firebase
}

android {
    namespace = "com.example.enroll_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.enroll_app"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    // Firebase Authentication SDK
    implementation("com.google.firebase:firebase-auth:21.0.5")

    // Firebase Firestore SDK (jika dibutuhkan)
    implementation("com.google.firebase:firebase-firestore:24.0.3")

    // Firebase Analytics SDK (jika dibutuhkan)
    implementation("com.google.firebase:firebase-analytics:21.0.0")

    // Library lainnya
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.activity:activity:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.espresso:espresso-core:3.5.1")
}

apply(plugin = "com.google.gms.google-services") // Pastikan ini ada

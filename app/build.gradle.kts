plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.topfivelibs"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.topfivelibs"
        minSdk = 33
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = true
                packageScope = setOf("androidx.**", "kotlin.**", "kotlinx.**")
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)

    // 1. MPAndroidChart (Графіки)
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    // 2. Toasty (Красиві сповіщення)
    implementation("com.github.GrenderG:Toasty:1.5.2")
    // 3. Lottie (Векторні анімації)
    implementation("com.airbnb.android:lottie:6.4.0")
    // 4. Media3 ExoPlayer (Професійний відеоплеєр від Google)
    implementation("androidx.media3:media3-exoplayer:1.3.0")
    implementation("androidx.media3:media3-ui:1.3.0")
    // 5. PhotoView (Масштабування зображень жестами)
    implementation("com.github.Baseflow:PhotoView:2.3.0")

}
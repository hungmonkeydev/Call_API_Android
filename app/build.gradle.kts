plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.callapipart1"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.callapipart1"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.google.code.gson:gson:2.13.1")
    // Retrofit core (dùng để gọi API)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Retrofit + Gson (để tự động parse JSON thành object)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

}
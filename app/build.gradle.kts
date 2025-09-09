import org.jetbrains.kotlin.gradle.utils.API
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.androidx.navigation.safe.args)

    kotlin("plugin.serialization")
//    id("org.jetbrains.kotlin.kapt")
//    id("kotlin-parcelize")
//    alias(libs.plugins.dagger.hilt.android)
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}





android {
    namespace = "com.example.weatherapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.weatherapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"


        testInstrumentationRunner = "com.example.weatherapp.HiltTestRunner"
    }

    buildTypes {
        debug {
            enableAndroidTestCoverage=true
        }
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
        isCoreLibraryDesugaringEnabled = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    //
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

}
val ktorVersion = "2.3.5"

dependencies {

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
    implementation(libs.material)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.hilt.navigation.compose)
    implementation("com.jakewharton.threetenabp:threetenabp:1.4.6")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
    //implementation(libs.lifecycle.livedata.compose)
    implementation("androidx.compose.material:material:1.5.0")

    implementation("io.ktor:ktor-client-core:${ktorVersion}")
    implementation("io.ktor:ktor-client-cio:${ktorVersion}")

    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
    implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")

    implementation("io.ktor:ktor-client-okhttp:${ktorVersion}")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("io.ktor:ktor-client-android:${ktorVersion}")
    // Hilt
    implementation("com.google.dagger:hilt-android:2.52")
    kapt("com.google.dagger:hilt-compiler:2.52")




    implementation("androidx.core:core-splashscreen:1.0.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.4")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")

    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.7") // Use the latest stable Compose version
    // or for specific lifecycle-aware obs
    androidTestImplementation(project(":app"))
    androidTestImplementation(project(":app"))
    androidTestImplementation(project(":app"))
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("com.github.bumptech.glide:glide:4.15.0")
    kapt("com.github.bumptech.glide:compiler:4.15.0")

    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Hilt testing
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.51")
    kaptAndroidTest("com.google.dagger:hilt-compiler:2.51")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-fragment:1.2.0")
    kapt("androidx.hilt:hilt-compiler:1.2.0")


    androidTestImplementation("com.google.dagger:hilt-android-testing:2.51")
    testImplementation("com.google.truth:truth:1.4.2")
    androidTestImplementation("com.google.truth:truth:1.4.2")
    // Hilt core
    implementation("com.google.dagger:hilt-android:2.51")

// Hilt compiler (KAPT)
    kapt("com.google.dagger:hilt-compiler:2.51")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")
    testImplementation("androidx.arch.core:core-testing:2.2.0")


    testImplementation ("org.mockito:mockito-core:5.11.0")


    testImplementation ("org.mockito.kotlin:mockito-kotlin:5.2.1")
    testImplementation ("org.robolectric:robolectric:4.12.2")// or latest


    // Compose
    implementation(platform("androidx.compose:compose-bom:2024.09.02"))
    implementation("androidx.compose.ui:ui") // Core UI
    implementation("androidx.compose.ui:ui-graphics") // Graphics
    implementation("androidx.compose.ui:ui-tooling-preview") // Preview
    implementation("androidx.compose.foundation:foundation:1.9.0") // Foundation (layouts, scroll, etc)
    implementation("androidx.compose.material3:material3") // Material3 Components
    implementation("androidx.activity:activity-compose:1.10.1") // Compose in Activity
    implementation("androidx.navigation:navigation-compose:2.8.0") // Compose Navigation
    implementation("io.coil-kt:coil-compose:2.6.0") // Coil for images
    implementation("io.coil-kt:coil-gif:2.6.0") // Coil for GIFs

// Debug tooling
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

// Testing Compose
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")



}


kapt {
    correctErrorTypes = true
}
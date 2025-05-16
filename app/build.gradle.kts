import com.google.protobuf.gradle.*
import org.gradle.internal.extensions.stdlib.capitalized
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)


    id("org.jetbrains.kotlin.kapt")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.kotlin.serialization)
    id("com.google.protobuf") version "0.9.1"
}

android {
    namespace = "com.peter.truassessment"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.peter.truassessment"
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    sourceSets {
        getByName("main"){
            java.srcDirs("build/generated/source/proto/debug/java")
        }
    }
}

dependencies {

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

    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    implementation("com.google.dagger:hilt-android:2.55")
    kapt("com.google.dagger:hilt-compiler:2.56.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
    implementation ("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")


    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:5.5.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.56.1")
    kaptAndroidTest("com.google.dagger:hilt-compiler:2.56.1")

    implementation("io.coil-kt.coil3:coil-compose:3.2.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.2.0")
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    implementation("androidx.datastore:datastore:1.1.6")
    implementation("com.google.protobuf:protobuf-javalite:3.24.1")
}
kapt {
    correctErrorTypes=true
}

sourceSets {

}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.24.1"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins{
                id("java"){
                    option("lite")
                }
            }
        }
    }
}
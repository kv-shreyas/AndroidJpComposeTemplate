import java.io.FileInputStream
import java.util.Properties
import kotlin.apply

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.example.jetpackComposeTemplate"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.jetpackComposeTemplate"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    lint {
        abortOnError = false // Temporary: Allows build to proceed despite lint errors
        disable += "Instantiatable"
    }

    // Load keystore properties
    val keystorePropertiesFile = rootProject.file("keystore.properties")
    val keystoreProperties = Properties().apply {
        load(FileInputStream(keystorePropertiesFile))
    }


    signingConfigs {
        /*     create("debugConfig") {
                 storeFile = file("NabhmitraKeyStore.jks")
                 storePassword = "nabhmitra_insta#560071"
                 keyAlias = "key0"
                 keyPassword = "nabhmitra_insta#560071"

             }*/
        create("releaseConfig") {
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
        }
    }


    // Define flavor dimensions and product flavors
    flavorDimensions ("smartnav-app") // Group flavors under a "version" dimension
    productFlavors {
        create("dev") {
            dimension = "smartnav-app"
            applicationIdSuffix = ".dev" // Results in com.accord.nabhmitraInstaller.lite
            versionNameSuffix = "-dev"
            buildConfigField("boolean" ,"IS_DEV", "true")
            buildConfigField("boolean" ,"IS_STAGE", "false")
            buildConfigField ("String", "FLAVOR_TYPE", "\"Dev\"")
        }
        create("stage") {
            dimension = "smartnav-app"
            applicationIdSuffix = ".stage" // Results in com.accord.nabhmitraInstaller.pro
            versionNameSuffix = "-stage"
            buildConfigField("boolean" ,"IS_DEV", "false")
            buildConfigField("boolean" ,"IS_STAGE", "true")
            buildConfigField ("String", "FLAVOR_TYPE", "\"Stage\"")
        }
    }

    buildTypes {
        release {
            applicationIdSuffix = ".release" // Optional: Differentiates release builds
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("releaseConfig")
        }

        debug {
            applicationIdSuffix = ".debug" // Optional: Differentiates debug builds
            isDebuggable = true // Default, but explicitly set for clarity
//            signingConfig = signingConfigs.getByName("debugConfig")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
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

    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    implementation(libs.dagger.hilt.compose)
    implementation(libs.coil)
    implementation(libs.androidx.livedata)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.lifecycle.viewmodel.compose)

    implementation(libs.retrofit)
    implementation (libs.converter.gson)
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation ("androidx.security:security-crypto:1.0.0")

    // Room
    implementation( libs.androidx.room.ktx)
    implementation (libs.androidx.room.runtime)
    kapt (libs.androidx.room.compiler)

    implementation (libs.lottie.compose)






}
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.localgo.artelabspa"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.localgo.artelabspa"
        minSdk = 24
        targetSdk = 36
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
}

dependencies {

    implementation("com.google.android.material:material:1.12.0")
    //escribir codigo mas cercano al humano
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    //conecta activities de android con jetpack compose
    implementation(libs.androidx.activity.compose)

    //aplica el bill of materials en el compose
    implementation(platform(libs.androidx.compose.bom))

    //capa base del toolkit de jetpack compose
    implementation(libs.androidx.compose.ui)

    //graficas primitivas
    implementation(libs.androidx.compose.ui.graphics)

    //habilitar previews
    implementation(libs.androidx.compose.ui.tooling.preview)

    //añade los componentes y el sistema de diseño Material 3 para Jetpack Compose
    implementation(libs.androidx.compose.material3)

    //añade extensiones de JUnit para tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

    //para pruebas ui implementadas
    androidTestImplementation(libs.androidx.espresso.core)

    //alinea versiones de compose en el scope androidTest usando el bom
    androidTestImplementation(platform(libs.androidx.compose.bom))

    //añade el framework de pruebas de ui de Compose para tests instrumentados con JUnit4
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    //activa las herramientas de depuracion de Compose solo en debug
    debugImplementation(libs.androidx.compose.ui.tooling)

    // agrega componentes necesarios para tests ui en compose
    debugImplementation(libs.androidx.compose.ui.test.manifest)

    // iconos de material design para compose
    implementation("androidx.compose.material:material-icons-extended")

    // accompanist permissions (para pedir permisos de la cámara y la galería)
    implementation("com.google.accompanist:accompanist-permissions:0.33.2-alpha")

    // hace la conexion a internet (envia y recibe requests HTTP).
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    // muestra en la consola lo que se envía y recibe (para depurar)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // permite definir funciones como getUsers() y él hace llamada HTTP por uno usando OkHttp.
    implementation("com.squareup.retrofit2:retrofit:2.11.0")

    //convertidor de json y a kotlin y viceversa
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // para las corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0")

    // para guardar tokens
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // para cargar imágenes desde URLs (opcional)
    implementation("io.coil-kt:coil-compose:2.6.0")

    // integra viewmodel a compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")

    // declarar y moverte en diferentes pantallas
    implementation("androidx.navigation:navigation-compose:2.7.7")
}
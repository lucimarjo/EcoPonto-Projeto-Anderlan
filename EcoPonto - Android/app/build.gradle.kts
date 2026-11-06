// Plugins que habilitam a construção do aplicativo Android e o suporte ao Kotlin com Jetpack Compose.
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

// Configurações gerais do módulo Android, incluindo identificador, versões e compatibilidade.
android {
    // Namespace usado para organizar os recursos e as classes geradas do aplicativo.
    namespace = "br.com.ecoponto"
    // Versão do SDK Android usada para compilar o projeto.
    compileSdk = 36

    // Identificação do aplicativo e versões mínimas/alvo do Android.
    defaultConfig {
        applicationId = "br.com.ecoponto"
        // Versão mínima do Android permitida para instalar o aplicativo.
        minSdk = 26
        // Versão do Android para a qual o aplicativo declara compatibilidade de comportamento.
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.1"
    }

    // Ativa recursos adicionais de compilação; aqui habilita o Compose.
    buildFeatures {
        compose = true
    }

    // Define a versão Java usada na compilação.
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    // Define a versão da JVM para o bytecode Kotlin.
    kotlinOptions {
        jvmTarget = "17"
    }
}

// Bibliotecas usadas pelo aplicativo e ferramentas utilizadas apenas em testes ou depuração.
dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2025.08.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.core:core-ktx:1.16.0")
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
}

// Define onde o Gradle procura os plugins e as dependências do projeto.
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
// Nome atribuído ao projeto no Android Studio.
rootProject.name = "EcoPonto"
// Inclui o módulo Android que gera o aplicativo.
include(":app")

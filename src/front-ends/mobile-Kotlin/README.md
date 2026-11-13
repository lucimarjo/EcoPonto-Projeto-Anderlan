# 📱 Kotlin / Mobile

Será a interface do usuário final, dando acesso aos serviços de coleta e descarte.

## 🎯 Responsabilidades do Painel:

- **Geolocalização e mapas**
- **Envio de denúncias**
- **Consulta de coleta**
- **Pontos de descarte**

## 🤔 Por que escolhemos

O **Kotlin** foi escolhido por ser a linguagem oficial e recomendada pelo Google para o desenvolvimento Android nativo, sendo excelente e desempenho por isso.
Já o Framework **Jetpack Compose** foi escolhido por reduzir a quantidade de XML de código necessário e por apenas descrever como a tela deve ser o Compose cuidará da atualização da interface, agilizando, assim, a entrega do projeto.


# 📁 Estrutura da pasta

A estrutura principal do projeto Android está organizada da seguinte maneira:

```text
EcoPonto - Android/
├── app/
├── gradle/
├── .gitignore
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── README.md
└── settings.gradle.kts
```

- **app:** contém o módulo principal da aplicação Android, onde ficam os arquivos do aplicativo.
- **gradle:** reúne arquivos relacionados ao Gradle Wrapper e à configuração da ferramenta de compilação.
- **.gitignore:** define arquivos e pastas que não devem ser enviados para o controle de versão.
- **build.gradle.kts:** contém configurações de compilação do projeto utilizando Kotlin DSL.
- **gradle.properties:** armazena propriedades utilizadas pelo Gradle.
- **gradlew e gradlew.bat:** scripts para executar o Gradle Wrapper em sistemas Unix e Windows, respectivamente.
- **settings.gradle.kts:** define as configurações gerais e os módulos incluídos no projeto.
- **README.md:** apresenta as informações e orientações do projeto.

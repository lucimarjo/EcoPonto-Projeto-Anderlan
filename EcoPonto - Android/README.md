# EcoPonto --- Aplicativo Android

O EcoPonto é um projeto de aplicativo Android desenvolvido em Kotlin com
Jetpack Compose. Esta versão contempla a interface do aplicativo
(Front-End), com telas e dados demonstrativos para apresentar as
principais funcionalidades.

## Organização do código

O código foi dividido em pacotes e arquivos conforme a responsabilidade
de cada parte. Assim, as telas, os componentes visuais, os modelos e os
dados ficam em locais próprios, facilitando a localização e a
manutenção.

``` text
br.com.ecoponto/
├── MainActivity.kt
├── navigation/
│   └── EcoPontoApp.kt
├── screens/
│   ├── home/
│   │   └── HomeScreen.kt
│   ├── map/
│   │   └── MapScreen.kt
│   ├── reports/
│   │   └── ReportsScreen.kt
│   ├── profile/
│   │   └── ProfileScreen.kt
│   └── splash/
│       └── SplashScreen.kt
├── components/
│   └── CommonComponents.kt
├── models/
│   └── DemoModels.kt
├── data/
│   └── DemoData.kt
└── theme/
    └── EcoPontoTheme.kt
```

### Responsabilidade de cada parte

-   **`MainActivity.kt`** --- ponto de entrada Android. Inicializa a
    interface Compose e controla a apresentação da tela de abertura.
-   **`navigation/`** --- reúne a estrutura principal do aplicativo e o
    controle da aba/tela selecionada.
-   **`screens/`** --- contém uma pasta para cada tela: Início, Mapa,
    Denúncias, Perfil e Splash.
-   **`components/`** --- reúne componentes visuais compartilhados, como
    cartões, cabeçalhos, indicadores de situação e botões.
-   **`models/`** --- define as estruturas que representam os dados
    utilizados pela interface, como pontos de coleta e denúncias.
-   **`data/`** --- mantém os registros fictícios utilizados para
    preencher as telas durante a demonstração.
-   **`theme/`** --- centraliza as cores e a configuração do tema
    visual.

## Documentação do código

Os arquivos Kotlin contêm comentários explicativos para ajudar na
leitura e na manutenção. Eles descrevem a finalidade das classes e
funções, os parâmetros utilizados, os estados da interface e conceitos
do Kotlin e do Jetpack Compose, como `@Composable`, `remember`,
`mutableStateOf`, `Modifier` e `LaunchedEffect`.

Os comentários servem como apoio para entender o código e podem ser
ajustados pela equipe conforme o padrão de documentação adotado na
entrega. É recomendável manter as explicações que forem úteis para quem
dará continuidade.

## Tecnologias utilizadas

-   Kotlin
-   Android Studio
-   Jetpack Compose
-   Material 3
-   Gradle com Kotlin DSL

## Escopo desta versão

Esta entrega corresponde ao Front-End Android. As telas utilizam dados
demonstrativos mantidos em memória. Nesta versão:

-   Os pontos de coleta e as denúncias exibidos são fictícios.
-   O mapa é ilustrativo e não consulta serviços de mapas nem
    localização GPS.
-   As preferências e ações de perfil são demonstrativas.
-   Não há integração com API, banco de dados ou serviço de
    autenticação.

Essas integrações podem ser desenvolvidas em etapas posteriores,
conforme o escopo geral do projeto.

## Como abrir e executar

1.  Extraia o projeto para uma pasta local.
2.  Abra o Android Studio.
3.  Selecione **Open** e escolha a pasta raiz que contém
    `settings.gradle.kts`.
4.  Aguarde a sincronização do Gradle.
5.  Selecione a configuração de execução `app`.
6.  Execute em um emulador ou dispositivo Android compatível.

## Observação

Antes da entrega, confira se o projeto abre e compila no ambiente
utilizado pela equipe e se os comentários e informações deste README
continuam correspondendo à versão final do código.

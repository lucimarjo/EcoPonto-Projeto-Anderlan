package br.com.ecoponto.theme

// Define a localização deste arquivo dentro da estrutura de pacotes do aplicativo.

// Centraliza as cores e a aparência visual do EcoPonto.

// Conceitos usados neste arquivo:
// @Composable marca uma função de interface: ela descreve elementos visuais e pode chamar outros composables.
// remember conserva um valor entre recomposições enquanto o elemento permanece na composição.
// mutableStateOf cria um estado observável; quando seu valor muda, o Compose atualiza as partes da interface que dependem dele.
// by usa os operadores getValue e setValue para acessar o estado como se fosse uma variável comum.
// Modifier encadeia configurações visuais e de interação, como tamanho, alinhamento, espaçamento, fundo e clique.
// dp representa uma unidade de tamanho independente da densidade da tela; sp é usada principalmente para tamanhos de texto.
// Uma lambda { } é uma função passada como valor; aqui ela representa conteúdo visual ou uma ação executada depois.

// Importa bibliotecas e componentes usados neste arquivo.
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Background = Color(0xFF07110C)
val SurfaceDark = Color(0xFF0D1B13)
val CardDark = Color(0xFF12251A)
val CardElevated = Color(0xFF183222)
val PrimaryGreen = Color(0xFF18A957)
val BrightGreen = Color(0xFF2CCB70)
val PaleGreen = Color(0xFFB9E47C)
val TextPrimary = Color(0xFFF0F6F1)
val TextSecondary = Color(0xFF9BAFA1)
val LineColor = Color(0xFF244331)
val Amber = Color(0xFFE4B34F)
val RedSoft = Color(0xFFE17C70)


// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Aplica o tema visual do Material 3 e disponibiliza as cores definidas para as telas.
fun EcoPontoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = BrightGreen,
            onPrimary = Color.White,
            secondary = PaleGreen,
            background = Background,
            onBackground = TextPrimary,
            surface = SurfaceDark,
            onSurface = TextPrimary,
            surfaceVariant = CardDark,
            onSurfaceVariant = TextSecondary
        ),
        content = content
    )
}

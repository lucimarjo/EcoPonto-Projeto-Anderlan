package br.com.ecoponto.screens.splash

// Define a localização deste arquivo dentro da estrutura de pacotes do aplicativo.

// Exibe a imagem de abertura e o indicador de carregamento.

// Conceitos usados neste arquivo:
// @Composable marca uma função de interface: ela descreve elementos visuais e pode chamar outros composables.
// remember conserva um valor entre recomposições enquanto o elemento permanece na composição.
// mutableStateOf cria um estado observável; quando seu valor muda, o Compose atualiza as partes da interface que dependem dele.
// by usa os operadores getValue e setValue para acessar o estado como se fosse uma variável comum.
// Modifier encadeia configurações visuais e de interação, como tamanho, alinhamento, espaçamento, fundo e clique.
// dp representa uma unidade de tamanho independente da densidade da tela; sp é usada principalmente para tamanhos de texto.
// Uma lambda { } é uma função passada como valor; aqui ela representa conteúdo visual ou uma ação executada depois.

// Importa bibliotecas e componentes usados neste arquivo.
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.CircularProgressIndicator
import br.com.ecoponto.*

// No Jetpack Compose, Modifier configura como um elemento ocupa espaço, recebe cliques e é desenhado.
// Parâmetros entre parênteses permitem que a tela receba dados e ações de outros componentes.
// Lambdas entre chaves { } representam ações ou conteúdo que será executado/mostrado pelo componente.

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Exibe a arte de abertura do EcoPonto e o indicador visual de carregamento.
fun SplashScreen() {
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.splash_ecoponto),
            contentDescription = "Tela de abertura EcoPonto",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 45.dp)
                .size(34.dp),
            color = Color(0xFFB9E47C),
            strokeWidth = 3.dp
        )
    }
}

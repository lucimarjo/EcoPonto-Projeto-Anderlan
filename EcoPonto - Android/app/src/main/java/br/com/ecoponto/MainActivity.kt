package br.com.ecoponto

// Define a localização deste arquivo dentro da estrutura de pacotes do aplicativo.

// Inicializa o aplicativo e aplica o tema principal.

// Conceitos usados neste arquivo:
// @Composable marca uma função de interface: ela descreve elementos visuais e pode chamar outros composables.
// remember conserva um valor entre recomposições enquanto o elemento permanece na composição.
// mutableStateOf cria um estado observável; quando seu valor muda, o Compose atualiza as partes da interface que dependem dele.
// by usa os operadores getValue e setValue para acessar o estado como se fosse uma variável comum.
// Modifier encadeia configurações visuais e de interação, como tamanho, alinhamento, espaçamento, fundo e clique.
// dp representa uma unidade de tamanho independente da densidade da tela; sp é usada principalmente para tamanhos de texto.
// Uma lambda { } é uma função passada como valor; aqui ela representa conteúdo visual ou uma ação executada depois.

// Importa bibliotecas e componentes usados neste arquivo.
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.tween
import br.com.ecoponto.navigation.*
import br.com.ecoponto.screens.splash.*
import br.com.ecoponto.theme.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// Classe Android que inicia a interface Compose quando o aplicativo é aberto.
class MainActivity : ComponentActivity() {
    // onCreate é chamado pelo Android quando esta Activity está sendo criada.
    // savedInstanceState pode conter informações para restaurar um estado anterior; nesta tela ele não é utilizado.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContent conecta a Activity à interface construída com Jetpack Compose.
        setContent {
            EcoPontoTheme {
                // Estado da interface: showSplash guarda um valor que pode mudar durante o uso da tela.
                // remember mantém esse estado durante as recomposições; mutableStateOf avisa o Compose quando ele muda.
                // by permite ler e atualizar o estado diretamente pela variável.
                var showSplash by remember { mutableStateOf(true) }

                // Executa uma tarefa ligada ao ciclo de vida do componente; reinicia quando a chave informada muda.
                // Aguarda cinco segundos antes de iniciar a saída da tela de abertura.
                LaunchedEffect(Unit) {
                    delay(5000)
                    showSplash = false
                }

                Box(modifier = Modifier.fillMaxSize()) {

                    EcoPontoApp()

                    // Controla a entrada e a saída gradual da SplashScreen.
                    AnimatedVisibility(
                        visible = showSplash,
                        enter = fadeIn(
                            animationSpec = tween(300)
                        ),
                        exit = fadeOut(
                            animationSpec = tween(900)
                        )
                    ) {
                        SplashScreen()
                    }
                }
            }
        }
    }
}

package br.com.ecoponto.navigation

// Define a localização deste arquivo dentro da estrutura de pacotes do aplicativo.

// Controla a tela selecionada e os diálogos principais do aplicativo.

// Conceitos usados neste arquivo:
// @Composable marca uma função de interface: ela descreve elementos visuais e pode chamar outros composables.
// remember conserva um valor entre recomposições enquanto o elemento permanece na composição.
// mutableStateOf cria um estado observável; quando seu valor muda, o Compose atualiza as partes da interface que dependem dele.
// by usa os operadores getValue e setValue para acessar o estado como se fosse uma variável comum.
// Modifier encadeia configurações visuais e de interação, como tamanho, alinhamento, espaçamento, fundo e clique.
// dp representa uma unidade de tamanho independente da densidade da tela; sp é usada principalmente para tamanhos de texto.
// Uma lambda { } é uma função passada como valor; aqui ela representa conteúdo visual ou uma ação executada depois.

// Importa bibliotecas e componentes usados neste arquivo.
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ReportProblem
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import br.com.ecoponto.data.*
import br.com.ecoponto.models.*
import br.com.ecoponto.screens.auth.*
import br.com.ecoponto.screens.home.*
import br.com.ecoponto.screens.map.*
import br.com.ecoponto.screens.profile.*
import br.com.ecoponto.screens.reports.*
import br.com.ecoponto.theme.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// No Jetpack Compose, Modifier configura como um elemento ocupa espaço, recebe cliques e é desenhado.
// Parâmetros entre parênteses permitem que a tela receba dados e ações de outros componentes.
// Lambdas entre chaves { } representam ações ou conteúdo que será executado/mostrado pelo componente.

private enum class AuthScreen { LOGIN, REGISTER, RECOVER }

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Componente principal que mantém a aba selecionada, os dados temporários de denúncias e os diálogos, além de montar a navegação inferior.
fun EcoPontoApp() {
    // Estados temporários de autenticação. Serão substituídos pela integração com a API.
    var authScreen by remember { mutableStateOf(AuthScreen.LOGIN) }
    var isAuthenticated by remember { mutableStateOf(false) }
    var registeredName by remember { mutableStateOf("") }
    var registeredEmail by remember { mutableStateOf("") }
    var registeredPassword by remember { mutableStateOf("") }
    var authMessage by remember { mutableStateOf<String?>(null) }

    // Antes de acessar as abas principais, o usuário precisa entrar ou criar uma conta.
    if (!isAuthenticated) {
        when (authScreen) {
            AuthScreen.LOGIN -> LoginScreen(
                onLogin = { email, password ->
                    authMessage = when {
                        registeredEmail.isBlank() -> "Cadastre uma conta antes de entrar."
                        email.equals(registeredEmail, ignoreCase = true) && password == registeredPassword -> {
                            isAuthenticated = true
                            null
                        }
                        else -> "E-mail ou senha incorretos."
                    }
                },
                onRegisterClick = {
                    authMessage = null
                    authScreen = AuthScreen.REGISTER
                },
                onRecoverClick = {
                    authMessage = null
                    authScreen = AuthScreen.RECOVER
                },
                message = authMessage
            )
            AuthScreen.REGISTER -> RegisterScreen(
                onRegister = { name, email, password, confirmPassword ->
                    authMessage = when {
                        name.isBlank() -> "Informe seu nome."
                        !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> "Informe um e-mail válido."
                        password.length < 6 -> "A senha deve ter pelo menos 6 caracteres."
                        password != confirmPassword -> "As senhas não coincidem."
                        email.equals(registeredEmail, ignoreCase = true) -> "Este e-mail já foi cadastrado."
                        else -> {
                            registeredName = name
                            registeredEmail = email
                            registeredPassword = password
                            authScreen = AuthScreen.LOGIN
                            "Cadastro concluído. Agora entre com seus dados."
                        }
                    }
                },
                onLoginClick = {
                    authMessage = null
                    authScreen = AuthScreen.LOGIN
                },
                message = authMessage
            )
            AuthScreen.RECOVER -> RecoverPasswordScreen(
                onSubmit = { email ->
                    authMessage = if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        "A recuperação será habilitada quando a API estiver integrada."
                    } else {
                        "Informe um e-mail válido."
                    }
                },
                onBackClick = {
                    authMessage = null
                    authScreen = AuthScreen.LOGIN
                },
                message = authMessage
            )
        }
        return
    }

    // Estado da interface: selectedTab guarda um valor que pode mudar durante o uso da tela.
    // remember mantém esse estado durante as recomposições; mutableStateOf avisa o Compose quando ele muda.
    // by permite ler e atualizar o estado diretamente pela variável.
    var selectedTab by remember { mutableStateOf(AppTab.HOME) }
    // Estado da interface: reports guarda um valor que pode mudar durante o uso da tela.
    // remember mantém esse estado durante as recomposições; mutableStateOf avisa o Compose quando ele muda.
    // by permite ler e atualizar o estado diretamente pela variável.
    var reports by remember { mutableStateOf(initialReports) }
    // Estado da interface: showCreateReport guarda um valor que pode mudar durante o uso da tela.
    // remember mantém esse estado durante as recomposições; mutableStateOf avisa o Compose quando ele muda.
    // by permite ler e atualizar o estado diretamente pela variável.
    var showCreateReport by remember { mutableStateOf(false) }
    // Estado da interface: selectedReport guarda uma informação que pode mudar durante a interação.
    // remember preserva o valor durante recomposições e mutableStateOf notifica o Compose quando ele é alterado.
    // by permite ler e atualizar esse estado diretamente pela variável.
    var selectedReport by remember { mutableStateOf<DemoReport?>(null) }
    // Estado da interface: notice guarda uma informação que pode mudar durante a interação.
    // remember preserva o valor durante recomposições e mutableStateOf notifica o Compose quando ele é alterado.
    // by permite ler e atualizar esse estado diretamente pela variável.
    var notice by remember { mutableStateOf<String?>(null) }

    // A tela principal aparece gradualmente depois que o login é concluído.
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(animationSpec = tween(durationMillis = 900))
    ) {
    // Scaffold organiza a estrutura visual da tela, incluindo a barra inferior e o conteúdo principal.
    Scaffold(
        containerColor = Background,
        bottomBar = {
            NavigationBar(
                containerColor = SurfaceDark,
                contentColor = TextPrimary
            ) {
                // Percorre todas as abas definidas em AppTab para criar os itens do menu.
                AppTab.values().forEach { tab ->
                    // Escolhe um valor de acordo com a condição correspondente.
                    val icon = when (tab) {
                        AppTab.HOME -> Icons.Default.Home
                        AppTab.MAP -> Icons.Default.Map
                        AppTab.REPORTS -> Icons.Default.ReportProblem
                        AppTab.PROFILE -> Icons.Default.Person
                    }
                    NavigationBarItem(
                        selected = selectedTab == tab,
                        onClick = { selectedTab = tab },
                        icon = { Icon(icon, contentDescription = tab.label) },
                        label = { Text(tab.label, fontSize = 10.sp) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.White,
                            selectedTextColor = BrightGreen,
                            indicatorColor = Color(0xFF1A4B30),
                            unselectedIconColor = TextSecondary,
                            unselectedTextColor = TextSecondary
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(Modifier.fillMaxSize().padding(innerPadding)) {
            // Define qual tela será montada conforme a aba atualmente selecionada.
            // Exibe somente a tela correspondente à aba selecionada.
            when (selectedTab) {
                AppTab.HOME -> HomePage(
                    userName = registeredName.substringBefore(" ").ifBlank { registeredName },
                    reports = reports,
                    onOpenMap = { selectedTab = AppTab.MAP },
                    onOpenReports = { selectedTab = AppTab.REPORTS },
                    onOpenProfile = { selectedTab = AppTab.PROFILE },
                    onSelectReport = { selectedReport = it },
                    onNotificationClick = { notice = "Você não tem novas notificações nesta demonstração." }
                )
                AppTab.MAP -> MapPage()
                AppTab.REPORTS -> ReportsPage(
                    reports = reports,
                    onCreate = { showCreateReport = true },
                    onSelectReport = { selectedReport = it }
                )
                AppTab.PROFILE -> ProfilePage(
                    userName = registeredName,
                    userEmail = registeredEmail,
                    onAction = { notice = it },
                    onLogout = {
                        // Encerra apenas a sessão. Os dados registrados continuam em memória,
                        // permitindo que a pessoa entre novamente sem refazer o cadastro.
                        authMessage = null
                        authScreen = AuthScreen.LOGIN
                        isAuthenticated = false
                    }
                )
            }
        }
    }
    }

    // O formulário só é montado enquanto o estado showCreateReport estiver ativo.
    if (showCreateReport) {
        CreateReportDialog(
            onDismiss = { showCreateReport = false },
            onCreate = { title, description, category, address ->
                // Cria um novo objeto de denúncia com os dados enviados pelo formulário.
                val newReport = DemoReport(
                    id = (reports.maxOfOrNull { it.id } ?: 1042) + 1,
                    title = title,
                    description = description,
                    category = category,
                    address = address.ifBlank { "Local não informado" },
                    status = "Pendente",
                    date = "Agora"
                )
                reports = listOf(newReport) + reports
                showCreateReport = false
                selectedTab = AppTab.REPORTS
                notice = "Denúncia adicionada à demonstração. Nenhum dado foi enviado."
            }
        )
    }

    // let executa o bloco apenas quando existe uma denúncia selecionada.
    selectedReport?.let { report ->
        ReportDetailsDialog(report = report, onDismiss = { selectedReport = null })
    }

    // Exibe um aviso somente quando a variável notice contém uma mensagem.
    notice?.let { message ->
        AlertDialog(
            onDismissRequest = { notice = null },
            title = { Text("EcoPonto", color = TextPrimary, fontWeight = FontWeight.Bold) },
            text = { Text(message, color = TextSecondary) },
            confirmButton = { TextButton(onClick = { notice = null }) { Text("Entendi", color = BrightGreen) } },
            containerColor = CardDark
        )
    }
}

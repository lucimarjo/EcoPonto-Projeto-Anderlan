package br.com.ecoponto.screens.profile

// Define a localização deste arquivo dentro da estrutura de pacotes do aplicativo.

// Exibe as informações demonstrativas e preferências do perfil.

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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.ecoponto.components.*
import br.com.ecoponto.theme.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// No Jetpack Compose, Modifier configura como um elemento ocupa espaço, recebe cliques e é desenhado.
// Parâmetros entre parênteses permitem que a tela receba dados e ações de outros componentes.
// Lambdas entre chaves { } representam ações ou conteúdo que será executado/mostrado pelo componente.

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Monta a tela de perfil com dados demonstrativos, preferências e opções de conta e suporte.
// Parâmetros de ProfilePage:
// - onAction: Recebe a mensagem da opção escolhida para ser apresentada pelo componente principal.
fun ProfilePage(
    userName: String,
    userEmail: String,
    onAction: (String) -> Unit,
    onLogout: () -> Unit
) {
    // Estado da interface: notificationsEnabled guarda um valor que pode mudar durante o uso da tela.
    // remember mantém esse estado durante as recomposições; mutableStateOf avisa o Compose quando ele muda.
    // by permite ler e atualizar o estado diretamente pela variável.
    var notificationsEnabled by remember { mutableStateOf(true) }
    // Estado da interface: locationEnabled guarda um valor que pode mudar durante o uso da tela.
    // remember mantém esse estado durante as recomposições; mutableStateOf avisa o Compose quando ele muda.
    // by permite ler e atualizar o estado diretamente pela variável.
    var locationEnabled by remember { mutableStateOf(false) }

    // LazyColumn cria uma lista rolável que compõe os itens conforme eles aparecem na tela.
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 18.dp, end = 18.dp, top = 20.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        item { PageHeader("Minha conta", "Gerencie suas informações e preferências.") }
        item {
            Card(shape = RoundedCornerShape(19.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF123D27))) {
                Row(Modifier.fillMaxWidth().padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(58.dp).clip(CircleShape).background(Color(0xFFB9E47C)), contentAlignment = Alignment.Center) {
                        Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFF123D27), modifier = Modifier.size(32.dp))
                    }
                    Spacer(Modifier.width(13.dp))
                    Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                        Text(userName, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Text(userEmail, color = Color(0xFFC9DCCB), fontSize = 11.sp)
                        Text("Conta demonstrativa", color = PaleGreen, fontSize = 9.sp)
                    }
                    Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.White)
                }
            }
        }
        item { SectionHeading("Preferências", "Personalize sua experiência") }
        item {
            Card(shape = RoundedCornerShape(17.dp), colors = CardDefaults.cardColors(containerColor = CardDark)) {
                Column(Modifier.padding(horizontal = 15.dp)) {
                    PreferenceSwitch("Notificações", "Avisos sobre suas denúncias", notificationsEnabled) { notificationsEnabled = it }
                    HorizontalDivider(color = LineColor)
                    PreferenceSwitch("Usar minha localização", "Exibir pontos próximos no mapa", locationEnabled) { locationEnabled = it }
                }
            }
        }
        item { SectionHeading("Conta e suporte", "Outras opções") }
        item {
            Card(shape = RoundedCornerShape(17.dp), colors = CardDefaults.cardColors(containerColor = CardDark)) {
                Column(Modifier.padding(horizontal = 15.dp)) {
                    ProfileActionRow("Dados pessoais", "Consulte as informações da conta", Icons.Default.Person) { onAction("A tela de edição de dados será implementada em versões futuras do app.") }
                    HorizontalDivider(color = LineColor)
                    ProfileActionRow("Configurações", "Preferências do aplicativo", Icons.Default.Settings) { onAction("A área de configurações está representada visualmente nesta versão.") }
                    HorizontalDivider(color = LineColor)
                    ProfileActionRow("Ajuda e informações", "Saiba mais sobre o EcoPonto", Icons.Default.CheckCircle) { onAction("EcoPonto: pequenas ações podem transformar grandes espaços.") }
                    HorizontalDivider(color = LineColor)
                    // Encerra a sessão atual e retorna à tela de login, sem apagar o cadastro temporário.
                    ProfileActionRow("Sair da conta", "Encerrar sessão neste dispositivo", Icons.Default.DeleteOutline, tint = RedSoft) {
                        onLogout()
                    }
                }
            }
        }
        item {
            Text("EcoPonto · Versão de interface 1.0", color = TextSecondary, fontSize = 9.sp, modifier = Modifier.fillMaxWidth(), textAlign = androidx.compose.ui.text.style.TextAlign.Center)
        }
    }
}

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Exibe uma opção com chave liga/desliga e informa ao componente pai quando o usuário altera seu estado.
// Parâmetros de PreferenceSwitch:
// - title: Texto principal da preferência.
// - subtitle: Descrição complementar da preferência.
// - checked: Indica se a opção está ligada ou desligada.
// - onCheckedChange: Recebe o novo estado quando o usuário altera a chave.
fun PreferenceSwitch(title: String, subtitle: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(Modifier.fillMaxWidth().padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(title, color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            Text(subtitle, color = TextSecondary, fontSize = 9.sp)
        }
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Monta uma linha clicável de ação do perfil com ícone, título e descrição.
// Parâmetros de ProfileActionRow:
// - title: Nome da ação exibida.
// - subtitle: Explicação curta sobre a ação.
// - icon: Ícone que representa a ação.
// - tint: Cor aplicada ao ícone.
// - onClick: Ação executada quando a linha é tocada.
fun ProfileActionRow(title: String, subtitle: String, icon: androidx.compose.ui.graphics.vector.ImageVector, tint: Color = PaleGreen, onClick: () -> Unit) {
    Row(Modifier.fillMaxWidth().clickable(onClick = onClick).padding(vertical = 13.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(icon, contentDescription = null, tint = tint, modifier = Modifier.size(19.dp))
        Spacer(Modifier.width(12.dp))
        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(3.dp)) {
            Text(title, color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            Text(subtitle, color = TextSecondary, fontSize = 9.sp)
        }
        Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextSecondary)
    }
}
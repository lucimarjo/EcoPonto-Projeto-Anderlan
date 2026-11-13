package br.com.ecoponto.screens.home

// Define a localização deste arquivo dentro da estrutura de pacotes do aplicativo.

// Monta a tela inicial com indicadores e atalhos.

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.ecoponto.components.*
import br.com.ecoponto.data.*
import br.com.ecoponto.models.*
import br.com.ecoponto.theme.*

// No Jetpack Compose, Modifier configura como um elemento ocupa espaço, recebe cliques e é desenhado.
// Parâmetros entre parênteses permitem que a tela receba dados e ações de outros componentes.
// Lambdas entre chaves { } representam ações ou conteúdo que será executado/mostrado pelo componente.

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Monta a tela inicial com resumo, indicadores e atalhos para as demais áreas do aplicativo.
// Parâmetros de HomePage:
// - reports: Denúncias disponíveis para os indicadores e a atividade recente.
// - onOpenMap: Ação para abrir a aba do mapa.
// - onOpenReports: Ação para abrir a aba de denúncias.
// - onOpenProfile: Ação para abrir a aba de perfil.
// - onSelectReport: Ação chamada quando uma denúncia é selecionada.
// - onNotificationClick: Ação chamada ao tocar no botão de notificações.
fun HomePage(
    userName: String,
    reports: List<DemoReport>,
    onOpenMap: () -> Unit,
    onOpenReports: () -> Unit,
    onOpenProfile: () -> Unit,
    onSelectReport: (DemoReport) -> Unit,
    onNotificationClick: () -> Unit
) {
    // LazyColumn cria uma lista rolável que compõe os itens conforme eles aparecem na tela.
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 18.dp, end = 18.dp, top = 18.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Row(Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(42.dp).clip(RoundedCornerShape(13.dp)).background(Color(0xFF174D2F)), contentAlignment = Alignment.Center) {
                        Text("♻", fontSize = 25.sp, color = PaleGreen)
                    }
                    Spacer(Modifier.width(10.dp))
                    Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                        Text("EcoPonto", color = TextPrimary, fontWeight = FontWeight.ExtraBold, fontSize = 21.sp)
                        Text("Cuidar da cidade começa aqui", color = TextSecondary, fontSize = 10.sp)
                    }
                }
                RoundIconButton(icon = { Icon(Icons.Default.NotificationsNone, contentDescription = "Notificações", tint = TextPrimary) }, onClick = onNotificationClick)
                Spacer(Modifier.width(8.dp))
                RoundIconButton(icon = { Icon(Icons.Default.Person, contentDescription = "Perfil", tint = TextPrimary) }, onClick = onOpenProfile)
            }
        }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(7.dp)) {
                Text("OLÁ, ${userName.uppercase()}! 👋", color = PaleGreen, fontSize = 10.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.4.sp)
                Text("Vamos deixar a cidade\nmais limpa?", color = TextPrimary, fontSize = 25.sp, lineHeight = 30.sp, fontWeight = FontWeight.Bold)
                Text("Encontre pontos de descarte e acompanhe os serviços de coleta.", color = TextSecondary, fontSize = 12.sp, lineHeight = 18.sp)
            }
        }
        item {
            Card(shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF123D27))) {
                Column(Modifier.fillMaxWidth().padding(17.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                            Text("Tem algo fora do lugar?", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Text("Registre um problema na sua região.", color = Color(0xFFC4D9C9), fontSize = 11.sp)
                        }
                        Text("🌱", fontSize = 31.sp)
                    }
                    Button(
                        onClick = onOpenReports,
                        colors = ButtonDefaults.buttonColors(containerColor = BrightGreen, contentColor = Color.White),
                        shape = RoundedCornerShape(11.dp),
                        contentPadding = PaddingValues(horizontal = 15.dp, vertical = 9.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = null, modifier = Modifier.size(17.dp))
                        Spacer(Modifier.width(6.dp))
                        Text("Fazer denúncia", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                MetricCard("Minhas denúncias", reports.size.toString(), "registros", Modifier.weight(1f))
                MetricCard("Pontos próximos", demoPoints.size.toString(), "locais demonstrativos", Modifier.weight(1f))
                MetricCard("Resolvidas", reports.count { it.status == "Resolvida" }.toString(), "concluídas", Modifier.weight(1f))
            }
        }
        item {
            SectionHeading("Acesso rápido", "O que você deseja fazer?")
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                QuickActionCard("Mapa", "Encontre pontos", "⌖", Modifier.weight(1f), onOpenMap)
                QuickActionCard("Denúncias", "Acompanhe registros", "⚑", Modifier.weight(1f), onOpenReports)
            }
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                SectionHeading("Atividade recente", "Acompanhe suas solicitações")
                Spacer(Modifier.weight(1f))
                TextButton(onClick = onOpenReports) { Text("Ver todas", color = BrightGreen, fontSize = 11.sp) }
            }
        }
        // Cria os itens da lista a partir dos elementos da coleção.
        // Mostra apenas os dois registros mais recentes da lista recebida.
        items(reports.take(2), key = { it.id }) { report ->
            ReportCard(report = report, onClick = { onSelectReport(report) })
        }
        item {
            Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = CardDark)) {
                Row(Modifier.fillMaxWidth().clickable(onClick = onOpenProfile).padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Person, contentDescription = null, tint = PaleGreen)
                    Spacer(Modifier.width(10.dp))
                    Column(Modifier.weight(1f)) {
                        Text("Minha conta", color = TextPrimary, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
                        Text("Veja seus dados e preferências", color = TextSecondary, fontSize = 10.sp)
                    }
                    Icon(Icons.Default.ChevronRight, contentDescription = null, tint = TextSecondary)
                }
            }
        }
    }
}

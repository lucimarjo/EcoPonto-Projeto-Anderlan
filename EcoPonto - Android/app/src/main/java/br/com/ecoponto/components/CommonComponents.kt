package br.com.ecoponto.components

// Define a localização deste arquivo dentro da estrutura de pacotes do aplicativo.

// Reúne componentes visuais compartilhados entre as telas.

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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.ecoponto.models.*
import br.com.ecoponto.theme.*

// No Jetpack Compose, Modifier configura como um elemento ocupa espaço, recebe cliques e é desenhado.
// Parâmetros entre parênteses permitem que a tela receba dados e ações de outros componentes.
// Lambdas entre chaves { } representam ações ou conteúdo que será executado/mostrado pelo componente.

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Alinha um rótulo e seu valor em uma linha, usada para apresentar detalhes de uma denúncia.
// Parâmetros de DetailLine:
// - label: Rótulo da informação.
// - value: Valor apresentado ao lado do rótulo.
fun DetailLine(label: String, value: String) {
    Row(verticalAlignment = Alignment.Top) {
        Text(label, color = TextSecondary, fontSize = 10.sp, modifier = Modifier.width(75.dp))
        Text(value, color = TextPrimary, fontSize = 10.sp, fontWeight = FontWeight.Medium)
    }
}

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Apresenta o título e o subtítulo principal de uma tela.
// Parâmetros de PageHeader:
// - title: Título principal da tela.
// - subtitle: Texto que complementa o título.
fun PageHeader(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Text(title, color = TextPrimary, fontSize = 25.sp, fontWeight = FontWeight.Bold)
        Text(subtitle, color = TextSecondary, fontSize = 11.sp, lineHeight = 16.sp)
    }
}

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Apresenta o título e o subtítulo de uma seção dentro de uma tela.
// Parâmetros de SectionHeading:
// - title: Título da seção.
// - subtitle: Descrição complementar da seção.
fun SectionHeading(title: String, subtitle: String) {
    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Text(title, color = TextPrimary, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Text(subtitle, color = TextSecondary, fontSize = 10.sp)
    }
}

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Exibe um indicador resumido com título, valor e informação complementar.
// Parâmetros de MetricCard:
// - title: Nome do indicador.
// - value: Valor principal do indicador.
// - detail: Texto complementar do indicador.
// - modifier: Permite ajustar o layout do cartão.
fun MetricCard(title: String, value: String, detail: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier, shape = RoundedCornerShape(15.dp), colors = CardDefaults.cardColors(containerColor = CardDark)) {
        Column(Modifier.fillMaxWidth().padding(11.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(title, color = TextSecondary, fontSize = 9.sp, minLines = 2, lineHeight = 12.sp)
            Text(value, color = TextPrimary, fontSize = 23.sp, fontWeight = FontWeight.ExtraBold)
            Text(detail, color = PaleGreen, fontSize = 8.sp)
        }
    }
}

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Exibe um cartão de atalho com símbolo, título e descrição e executa uma ação ao ser tocado.
// Parâmetros de QuickActionCard:
// - title: Nome do atalho.
// - subtitle: Descrição da ação.
// - symbol: Símbolo exibido no cartão.
// - modifier: Permite ajustar o layout do cartão.
// - onClick: Ação executada ao tocar no cartão.
fun QuickActionCard(title: String, subtitle: String, symbol: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(Modifier.fillMaxWidth().padding(13.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(Modifier.size(34.dp).clip(RoundedCornerShape(10.dp)).background(Color(0xFF1A4A2E)), contentAlignment = Alignment.Center) {
                Text(symbol, color = PaleGreen, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            }
            Text(title, color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Text(subtitle, color = TextSecondary, fontSize = 9.sp)
        }
    }
}

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Apresenta um resumo de denúncia em formato de cartão e informa quando o usuário o seleciona.
// Parâmetros de ReportCard:
// - report: Denúncia resumida no cartão.
// - onClick: Ação executada quando o cartão é selecionado.
fun ReportCard(report: DemoReport, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Column(Modifier.fillMaxWidth().padding(14.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(report.title, color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f), maxLines = 2, overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.width(6.dp))
                StatusBadge(report.status)
            }
            Text(report.description, color = TextSecondary, fontSize = 10.sp, lineHeight = 15.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(report.category, color = PaleGreen, fontSize = 9.sp, modifier = Modifier.background(Color(0xFF1B3B27), RoundedCornerShape(6.dp)).padding(horizontal = 7.dp, vertical = 5.dp))
                Spacer(Modifier.width(7.dp))
                Text(report.address, color = TextSecondary, fontSize = 9.sp, maxLines = 1, overflow = TextOverflow.Ellipsis, modifier = Modifier.weight(1f))
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(report.date, color = TextSecondary, fontSize = 8.sp, modifier = Modifier.weight(1f))
                Text("Ver detalhes", color = BrightGreen, fontSize = 9.sp, fontWeight = FontWeight.SemiBold)
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = BrightGreen, modifier = Modifier.size(14.dp))
            }
        }
    }
}

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Mostra a situação de uma denúncia com uma cor correspondente ao status.
// Parâmetros de StatusBadge:
// - status: Situação usada para definir o texto e a cor do indicador.
fun StatusBadge(status: String) {
    // Escolhe um valor de acordo com a condição correspondente.
    val tint = when (status) {
        "Resolvida" -> BrightGreen
        "Em andamento" -> Color(0xFF70B8E8)
        "Pendente", "Em análise" -> Amber
        else -> TextSecondary
    }
    Text(status, color = tint, fontSize = 8.sp, fontWeight = FontWeight.Bold, modifier = Modifier.background(tint.copy(alpha = .13f), RoundedCornerShape(6.dp)).padding(horizontal = 7.dp, vertical = 5.dp))
}

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Apresenta os dados de um ponto de coleta e destaca o cartão quando ele está selecionado.
// Parâmetros de PointCard:
// - point: Ponto de coleta exibido no cartão.
// - selected: Indica se o cartão está selecionado.
// - onClick: Ação executada ao selecionar o ponto.
fun PointCard(point: DemoPoint, selected: Boolean, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick).then(if (selected) Modifier.border(1.dp, BrightGreen, RoundedCornerShape(15.dp)) else Modifier),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(containerColor = CardDark)
    ) {
        Row(Modifier.fillMaxWidth().padding(13.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(40.dp).clip(RoundedCornerShape(11.dp)).background(Color(0xFF1A4A2E)), contentAlignment = Alignment.Center) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = PaleGreen)
            }
            Spacer(Modifier.width(11.dp))
            Column(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(point.name, color = TextPrimary, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(point.type, color = PaleGreen, fontSize = 9.sp)
                Text(point.address, color = TextSecondary, fontSize = 9.sp, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Text(point.hours, color = TextSecondary, fontSize = 8.sp)
            }
            Icon(Icons.Default.ChevronRight, contentDescription = "Ver ponto", tint = if (selected) BrightGreen else TextSecondary)
        }
    }
}

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Cria uma superfície circular clicável para receber um ícone e executar uma ação.
// Parâmetros de RoundIconButton:
// - icon: Conteúdo visual do ícone exibido no botão.
// - onClick: Ação executada ao tocar no botão.
fun RoundIconButton(icon: @Composable () -> Unit, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.size(39.dp).clickable(onClick = onClick),
        shape = CircleShape,
        color = CardDark,
        border = androidx.compose.foundation.BorderStroke(1.dp, LineColor)
    ) {
        Box(contentAlignment = Alignment.Center) { icon() }
    }
}

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Apresenta uma mensagem quando uma lista ou busca não possui resultados.
// Parâmetros de EmptyState:
// - title: Mensagem principal exibida quando não há resultados.
// - subtitle: Orientação complementar para o usuário.
fun EmptyState(title: String, subtitle: String) {
    Column(Modifier.fillMaxWidth().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(7.dp)) {
        Text("🌱", fontSize = 30.sp)
        Text(title, color = TextPrimary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
        Text(subtitle, color = TextSecondary, fontSize = 10.sp, lineHeight = 15.sp)
    }
}

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Centraliza as cores dos campos de texto para manter o mesmo padrão visual.
fun darkFieldColors() = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
    focusedTextColor = TextPrimary,
    unfocusedTextColor = TextPrimary,
    focusedContainerColor = SurfaceDark,
    unfocusedContainerColor = SurfaceDark,
    focusedBorderColor = BrightGreen,
    unfocusedBorderColor = LineColor,
    focusedLabelColor = BrightGreen,
    unfocusedLabelColor = TextSecondary,
    cursorColor = BrightGreen
)

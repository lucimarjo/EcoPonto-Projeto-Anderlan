package br.com.ecoponto.screens.map

// Define a localização deste arquivo dentro da estrutura de pacotes do aplicativo.

// Exibe o mapa ilustrativo, os filtros e os pontos de coleta.

// Conceitos usados neste arquivo:
// @Composable marca uma função de interface: ela descreve elementos visuais e pode chamar outros composables.
// remember conserva um valor entre recomposições enquanto o elemento permanece na composição.
// mutableStateOf cria um estado observável; quando seu valor muda, o Compose atualiza as partes da interface que dependem dele.
// by usa os operadores getValue e setValue para acessar o estado como se fosse uma variável comum.
// Modifier encadeia configurações visuais e de interação, como tamanho, alinhamento, espaçamento, fundo e clique.
// dp representa uma unidade de tamanho independente da densidade da tela; sp é usada principalmente para tamanhos de texto.
// Uma lambda { } é uma função passada como valor; aqui ela representa conteúdo visual ou uma ação executada depois.

// Importa bibliotecas e componentes usados neste arquivo.
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.ecoponto.components.*
import br.com.ecoponto.data.*
import br.com.ecoponto.models.*
import br.com.ecoponto.theme.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// No Jetpack Compose, Modifier configura como um elemento ocupa espaço, recebe cliques e é desenhado.
// Parâmetros entre parênteses permitem que a tela receba dados e ações de outros componentes.
// Lambdas entre chaves { } representam ações ou conteúdo que será executado/mostrado pelo componente.

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Monta a tela de mapa demonstrativo, permite pesquisar e filtrar pontos e selecionar um local para ver seus dados.
fun MapPage() {
    // Estado da interface: selectedPoint guarda um valor que pode mudar durante o uso da tela.
    // remember mantém esse estado durante as recomposições; mutableStateOf avisa o Compose quando ele muda.
    // by permite ler e atualizar o estado diretamente pela variável.
    var selectedPoint by remember { mutableStateOf(demoPoints.first()) }
    // Estado da interface: activeFilter guarda um valor que pode mudar durante o uso da tela.
    // remember mantém esse estado durante as recomposições; mutableStateOf avisa o Compose quando ele muda.
    // by permite ler e atualizar o estado diretamente pela variável.
    var activeFilter by remember { mutableStateOf("Todos") }
    // Estado da interface: search guarda um valor que pode mudar durante o uso da tela.
    // remember mantém esse estado durante as recomposições; mutableStateOf avisa o Compose quando ele muda.
    // by permite ler e atualizar o estado diretamente pela variável.
    var search by remember { mutableStateOf("") }
    // Estado da interface: showRouteMessage guarda um valor que pode mudar durante o uso da tela.
    // remember mantém esse estado durante as recomposições; mutableStateOf avisa o Compose quando ele muda.
    // by permite ler e atualizar o estado diretamente pela variável.
    var showRouteMessage by remember { mutableStateOf(false) }

    // Lista calculada a partir dos dados originais e dos filtros selecionados.
    // Aplica simultaneamente o filtro de categoria e o texto digitado na busca.
    val filteredPoints = demoPoints.filter { point ->
        (activeFilter == "Todos" || point.category == activeFilter) &&
            (search.isBlank() || point.name.contains(search, ignoreCase = true) || point.address.contains(search, ignoreCase = true))
    }
    // Executa uma tarefa ligada ao ciclo de vida do componente; reinicia quando a chave informada muda.
    // Se a seleção deixar de aparecer após um filtro, seleciona o primeiro resultado disponível.
    LaunchedEffect(filteredPoints) {
        if (filteredPoints.isNotEmpty() && filteredPoints.none { it.id == selectedPoint.id }) {
            selectedPoint = filteredPoints.first()
        }
    }

    // LazyColumn cria uma lista rolável que compõe os itens conforme eles aparecem na tela.
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 18.dp, end = 18.dp, top = 20.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item { PageHeader("Mapa", "Encontre pontos de descarte e locais de coleta.") }
        item {
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Buscar bairro ou ponto...", color = TextSecondary, fontSize = 12.sp) },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = TextSecondary) },
                singleLine = true,
                shape = RoundedCornerShape(14.dp),
                colors = darkFieldColors()
            )
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                listOf("Todos", "Reciclagem", "Coleta").forEach { filter ->
                    FilterChip(
                        selected = activeFilter == filter,
                        onClick = { activeFilter = filter },
                        label = { Text(filter, fontSize = 10.sp) },
                        colors = androidx.compose.material3.FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFF195632),
                            selectedLabelColor = Color.White,
                            labelColor = TextSecondary
                        ),
                        border = androidx.compose.material3.FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = activeFilter == filter,
                            borderColor = LineColor,
                            selectedBorderColor = BrightGreen
                        )
                    )
                }
            }
        }
        item {
            Box(Modifier.fillMaxWidth().height(310.dp).clip(RoundedCornerShape(20.dp)).border(1.dp, LineColor, RoundedCornerShape(20.dp))) {
                MapArtwork()
                Text("MAPA DEMONSTRATIVO", Modifier.align(Alignment.TopStart).padding(13.dp).background(Color(0xCC0B1C12), RoundedCornerShape(7.dp)).padding(horizontal = 9.dp, vertical = 6.dp), color = Color(0xFFD5E5D5), fontSize = 9.sp, fontWeight = FontWeight.Bold, letterSpacing = 1.sp)
                if (filteredPoints.isNotEmpty()) {
                    MapMarker(filteredPoints[0], Modifier.align(Alignment.TopStart).offset(x = 55.dp, y = 78.dp), selectedPoint.id == filteredPoints[0].id) { selectedPoint = filteredPoints[0] }
                }
                if (filteredPoints.size > 1) {
                    MapMarker(filteredPoints[1], Modifier.align(Alignment.CenterEnd).offset(x = (-64).dp, y = (-10).dp), selectedPoint.id == filteredPoints[1].id) { selectedPoint = filteredPoints[1] }
                }
                if (filteredPoints.size > 2) {
                    MapMarker(filteredPoints[2], Modifier.align(Alignment.BottomStart).offset(x = 104.dp, y = (-70).dp), selectedPoint.id == filteredPoints[2].id) { selectedPoint = filteredPoints[2] }
                }
                if (filteredPoints.size > 3) {
                    MapMarker(filteredPoints[3], Modifier.align(Alignment.Center).offset(x = 30.dp, y = 55.dp), selectedPoint.id == filteredPoints[3].id) { selectedPoint = filteredPoints[3] }
                }
                Column(Modifier.align(Alignment.BottomEnd).padding(12.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                    RoundIconButton(icon = { Icon(Icons.Default.Add, contentDescription = "Aproximar", tint = TextPrimary) }, onClick = { showRouteMessage = true })
                    RoundIconButton(icon = { Icon(Icons.Default.MyLocation, contentDescription = "Minha localização", tint = TextPrimary) }, onClick = { showRouteMessage = true })
                }
                Text("Mapa ilustrativo · dados fictícios", Modifier.align(Alignment.BottomStart).padding(12.dp).background(Color(0xCC0B1C12), RoundedCornerShape(6.dp)).padding(horizontal = 8.dp, vertical = 5.dp), color = TextSecondary, fontSize = 8.sp)
            }
        }
        if (filteredPoints.isEmpty()) {
            item { EmptyState("Nenhum ponto encontrado", "Tente outro termo ou selecione outro filtro.") }
        } else {
            item { SectionHeading("Pontos próximos", "Toque em um marcador para ver os detalhes") }
            // Cria os itens da lista a partir dos elementos da coleção.
            items(filteredPoints, key = { it.id }) { point ->
                PointCard(point = point, selected = selectedPoint.id == point.id, onClick = { selectedPoint = point })
            }
            item {
                Card(shape = RoundedCornerShape(17.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFF133D27))) {
                    Column(Modifier.fillMaxWidth().padding(15.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Text("${selectedPoint.name}", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
                        Text(selectedPoint.address, color = Color(0xFFC9DCCB), fontSize = 11.sp)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Schedule, contentDescription = null, tint = PaleGreen, modifier = Modifier.size(15.dp))
                            Spacer(Modifier.width(6.dp))
                            Text(selectedPoint.hours, color = PaleGreen, fontSize = 10.sp)
                        }
                        Button(
                            onClick = { showRouteMessage = true },
                            colors = ButtonDefaults.buttonColors(containerColor = BrightGreen),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(horizontal = 14.dp, vertical = 8.dp)
                        ) {
                            Icon(Icons.Default.ArrowForward, contentDescription = null, modifier = Modifier.size(15.dp))
                            Spacer(Modifier.width(7.dp))
                            Text("Ver rota", fontSize = 11.sp)
                        }
                    }
                }
            }
        }
    }

    if (showRouteMessage) {
        AlertDialog(
            onDismissRequest = { showRouteMessage = false },
            title = { Text("Recurso demonstrativo", color = TextPrimary) },
            text = { Text("A visualização do mapa e da rota está representada nesta versão de Front-End. A integração com os dados cartográficos será feita posteriormente.", color = TextSecondary) },
            confirmButton = { TextButton(onClick = { showRouteMessage = false }) { Text("Fechar", color = BrightGreen) } },
            containerColor = CardDark
        )
    }
}

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Desenha com Canvas um mapa ilustrativo usando quadras e linhas de ruas, sem consultar um serviço externo.
fun MapArtwork() {
    // Canvas permite desenhar formas e linhas diretamente na área disponível.
    Canvas(Modifier.fillMaxSize().background(Color(0xFF17271D))) {
        drawRect(Color(0xFF17271D))
        val minorRoad = Color(0xFF2B4031)
        val majorRoad = Color(0xFF50624A)
        val thin = 8.dp.toPx()
        val thick = 15.dp.toPx()
        val w = size.width
        val h = size.height
        // Cada par de coordenadas define o início e o fim de uma rua desenhada no mapa.
        val roads = listOf(
            Offset(-20f, h * .18f) to Offset(w * 1.08f, h * .38f),
            Offset(-20f, h * .72f) to Offset(w * 1.05f, h * .52f),
            Offset(w * .15f, -30f) to Offset(w * .37f, h * 1.1f),
            Offset(w * .66f, -30f) to Offset(w * .53f, h * 1.1f),
            Offset(w * .98f, -30f) to Offset(w * .78f, h * 1.1f),
            Offset(-20f, h * .48f) to Offset(w * .75f, h * .05f),
            Offset(w * .2f, h * .95f) to Offset(w * .95f, h * .73f)
        )
        // Quadras estilizadas para dar contexto visual sem depender de um serviço externo.
        for (row in 0..3) {
            for (column in 0..3) {
                val x = w * (0.07f + column * 0.23f)
                val y = h * (0.08f + row * 0.23f)
                drawRect(Color(0xFF203628), topLeft = Offset(x, y), size = androidx.compose.ui.geometry.Size(w * .10f, h * .07f))
            }
        }
        roads.forEachIndexed { index, road ->
            drawLine(if (index == 1 || index == 4) majorRoad else minorRoad, road.first, road.second, strokeWidth = if (index == 1 || index == 4) thick else thin, cap = StrokeCap.Round)
        }
    }
}

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Desenha um marcador clicável para um ponto de coleta e destaca visualmente o marcador selecionado.
// Parâmetros de MapMarker:
// - point: Ponto de coleta representado pelo marcador.
// - modifier: Configura a posição e o tamanho do marcador no mapa.
// - selected: Indica se o marcador corresponde ao ponto selecionado.
// - onClick: Ação executada ao tocar no marcador.
fun MapMarker(point: DemoPoint, modifier: Modifier, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = modifier.size(if (selected) 43.dp else 36.dp).clip(CircleShape)
            .background(if (selected) BrightGreen else Color(0xFF174D30))
            .border(2.dp, Color.White.copy(alpha = .9f), CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(Icons.Default.Place, contentDescription = point.name, tint = Color.White, modifier = Modifier.size(if (selected) 25.dp else 21.dp))
    }
}

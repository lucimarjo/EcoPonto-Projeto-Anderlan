package br.com.ecoponto.screens.reports

// Define a localização deste arquivo dentro da estrutura de pacotes do aplicativo.

// Exibe denúncias, detalhes e o formulário de criação.

// Conceitos usados neste arquivo:
// @Composable marca uma função de interface: ela descreve elementos visuais e pode chamar outros composables.
// remember conserva um valor entre recomposições enquanto o elemento permanece na composição.
// mutableStateOf cria um estado observável; quando seu valor muda, o Compose atualiza as partes da interface que dependem dele.
// by usa os operadores getValue e setValue para acessar o estado como se fosse uma variável comum.
// Modifier encadeia configurações visuais e de interação, como tamanho, alinhamento, espaçamento, fundo e clique.
// dp representa uma unidade de tamanho independente da densidade da tela; sp é usada principalmente para tamanhos de texto.
// Uma lambda { } é uma função passada como valor; aqui ela representa conteúdo visual ou uma ação executada depois.

// Importa bibliotecas e componentes usados neste arquivo.
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.ecoponto.components.*
import br.com.ecoponto.models.*
import br.com.ecoponto.theme.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// No Jetpack Compose, Modifier configura como um elemento ocupa espaço, recebe cliques e é desenhado.
// Parâmetros entre parênteses permitem que a tela receba dados e ações de outros componentes.
// Lambdas entre chaves { } representam ações ou conteúdo que será executado/mostrado pelo componente.

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Exibe a lista de denúncias e permite filtrar os registros por situação ou iniciar uma nova denúncia.
// Parâmetros de ReportsPage:
// - reports: Registros que serão apresentados na lista.
// - onCreate: Ação chamada para abrir o formulário de nova denúncia.
// - onSelectReport: Ação chamada quando o usuário escolhe uma denúncia.
fun ReportsPage(reports: List<DemoReport>, onCreate: () -> Unit, onSelectReport: (DemoReport) -> Unit) {
    // Estado da interface: statusFilter guarda um valor que pode mudar durante o uso da tela.
    // remember mantém esse estado durante as recomposições; mutableStateOf avisa o Compose quando ele muda.
    // by permite ler e atualizar o estado diretamente pela variável.
    var statusFilter by remember { mutableStateOf("Todas") }
    // Lista calculada a partir dos dados originais e dos filtros selecionados.
    // Mantém todos os registros ou somente aqueles com o status escolhido.
    val visibleReports = reports.filter { statusFilter == "Todas" || it.status == statusFilter }

    // LazyColumn cria uma lista rolável que compõe os itens conforme eles aparecem na tela.
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(start = 18.dp, end = 18.dp, top = 20.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(13.dp)
    ) {
        item { PageHeader("Minhas denúncias", "Acompanhe os problemas registrados e seus status.") }
        item {
            Button(
                onClick = onCreate,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = BrightGreen, contentColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                contentPadding = PaddingValues(vertical = 13.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(7.dp))
                Text("Criar nova denúncia", fontWeight = FontWeight.Bold, fontSize = 13.sp)
            }
        }
        item {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                listOf("Todas", "Pendente", "Em andamento", "Resolvida").forEach { filter ->
                    FilterChip(
                        selected = statusFilter == filter,
                        onClick = { statusFilter = filter },
                        label = { Text(filter, fontSize = 9.sp) },
                        colors = androidx.compose.material3.FilterChipDefaults.filterChipColors(
                            selectedContainerColor = Color(0xFF195632),
                            selectedLabelColor = Color.White,
                            labelColor = TextSecondary
                        ),
                        border = androidx.compose.material3.FilterChipDefaults.filterChipBorder(
                            enabled = true,
                            selected = statusFilter == filter,
                            borderColor = LineColor,
                            selectedBorderColor = BrightGreen
                        )
                    )
                }
            }
        }
        item { SectionHeading("Registros", "${visibleReports.size} denúncia(s) exibida(s)") }
        if (visibleReports.isEmpty()) {
            item { EmptyState("Nenhuma denúncia nesta categoria", "Quando houver registros, eles aparecerão aqui.") }
        } else {
            // Cria os itens da lista a partir dos elementos da coleção.
            items(visibleReports, key = { it.id }) { report ->
                ReportCard(report = report, onClick = { onSelectReport(report) })
            }
        }
        item {
            Text("Demonstração visual: as denúncias ficam apenas na memória enquanto o aplicativo está aberto.", color = TextSecondary, fontSize = 9.sp, lineHeight = 14.sp)
        }
    }
}

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Exibe o formulário de criação de denúncia, valida os campos obrigatórios e entrega os dados preenchidos ao componente que o chamou.
// Parâmetros de CreateReportDialog:
// - onDismiss: Ação para fechar o formulário sem criar o registro.
// - onCreate: Ação que envia título, descrição, categoria e endereço preenchidos.
fun CreateReportDialog(
    onDismiss: () -> Unit,
    onCreate: (String, String, String, String) -> Unit
) {
    // Estado da interface: title guarda um valor que pode mudar durante o uso da tela.
    // remember mantém esse estado durante as recomposições; mutableStateOf avisa o Compose quando ele muda.
    // by permite ler e atualizar o estado diretamente pela variável.
    var title by remember { mutableStateOf("") }
    // Estado da interface: description guarda um valor que pode mudar durante o uso da tela.
    // remember mantém esse estado durante as recomposições; mutableStateOf avisa o Compose quando ele muda.
    // by permite ler e atualizar o estado diretamente pela variável.
    var description by remember { mutableStateOf("") }
    // Estado da interface: category guarda um valor que pode mudar durante o uso da tela.
    // remember mantém esse estado durante as recomposições; mutableStateOf avisa o Compose quando ele muda.
    // by permite ler e atualizar o estado diretamente pela variável.
    var category by remember { mutableStateOf("Descarte irregular") }
    // Estado da interface: address guarda um valor que pode mudar durante o uso da tela.
    // remember mantém esse estado durante as recomposições; mutableStateOf avisa o Compose quando ele muda.
    // by permite ler e atualizar o estado diretamente pela variável.
    var address by remember { mutableStateOf("") }
    // Estado da interface: error guarda uma informação que pode mudar durante a interação.
    // remember preserva o valor durante recomposições e mutableStateOf notifica o Compose quando ele é alterado.
    // by permite ler e atualizar esse estado diretamente pela variável.
    var error by remember { mutableStateOf<String?>(null) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nova denúncia", color = TextPrimary, fontWeight = FontWeight.Bold) },
        text = {
            Column(
                modifier = Modifier.heightIn(max = 500.dp).verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text("Descreva o problema encontrado. Os dados serão usados apenas nesta demonstração.", color = TextSecondary, fontSize = 11.sp, lineHeight = 16.sp)
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it; error = null },
                    label = { Text("Título da denúncia") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    colors = darkFieldColors()
                )
                OutlinedTextField(
                    value = category,
                    onValueChange = { category = it; error = null },
                    label = { Text("Categoria") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = darkFieldColors()
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it; error = null },
                    label = { Text("Descrição") },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3,
                    maxLines = 5,
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences),
                    colors = darkFieldColors()
                )
                OutlinedTextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Endereço ou referência (opcional)") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = darkFieldColors()
                )
                // Verifica se há uma condição de erro antes de continuar.
                if (error != null) Text(error!!, color = RedSoft, fontSize = 10.sp)
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    // Impede o envio quando algum dos campos obrigatórios está vazio.
                    if (title.isBlank() || description.isBlank() || category.isBlank()) {
                        error = "Preencha título, categoria e descrição."
                    } else {
                        onCreate(title.trim(), description.trim(), category.trim(), address.trim())
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = BrightGreen),
                shape = RoundedCornerShape(10.dp)
            ) { Text("Adicionar", color = Color.White) }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Cancelar", color = TextSecondary) } },
        containerColor = CardDark,
        titleContentColor = TextPrimary,
        textContentColor = TextSecondary
    )
}

// @Composable identifica uma função que descreve parte da interface usando Jetpack Compose.
@Composable
// Apresenta os dados completos de uma denúncia selecionada em uma janela de diálogo.
// Parâmetros de ReportDetailsDialog:
// - report: Denúncia cujos detalhes serão exibidos.
// - onDismiss: Ação para fechar a janela de detalhes.
fun ReportDetailsDialog(report: DemoReport, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(report.title, color = TextPrimary, fontWeight = FontWeight.Bold) },
        text = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                StatusBadge(report.status)
                Text(report.description, color = TextSecondary, fontSize = 12.sp, lineHeight = 18.sp)
                HorizontalDivider(color = LineColor)
                DetailLine("Código", "#${report.id}")
                DetailLine("Categoria", report.category)
                DetailLine("Local", report.address)
                DetailLine("Data", report.date)
                Text("Histórico de atualizações", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                Text("• Denúncia registrada (demonstração)\n• Status atual: ${report.status}", color = TextSecondary, fontSize = 10.sp, lineHeight = 17.sp)
            }
        },
        confirmButton = { TextButton(onClick = onDismiss) { Text("Fechar", color = BrightGreen) } },
        containerColor = CardDark
    )
}

package br.com.ecoponto.models

// Define a localização deste arquivo dentro da estrutura de pacotes do aplicativo.

// Define os modelos usados para representar pontos e denúncias.

// Classe de dados que representa um ponto de coleta. O Kotlin gera automaticamente recursos como equals, hashCode e copy.
// Representa um ponto de coleta com identificação, endereço, horário e categoria.
data class DemoPoint(
    // id armazena o valor do campo id no modelo.
    // Identificador numérico usado para diferenciar cada registro.
    val id: Int,
    // name armazena o valor do campo name no modelo.
    // Nome pelo qual o ponto de coleta é identificado.
    val name: String,
    // type armazena o valor do campo type no modelo.
    // Tipo de local, como ponto de descarte ou ponto de coleta.
    val type: String,
    // address armazena o valor do campo address no modelo.
    // Endereço ou referência do local.
    val address: String,
    // hours armazena o valor do campo hours no modelo.
    // Dias e horários demonstrativos de funcionamento.
    val hours: String,
    // category armazena o valor do campo category no modelo.
    // Categoria usada para classificar e filtrar o ponto.
    val category: String
)

// Classe de dados que representa uma denúncia e suas informações de acompanhamento.
// Representa uma denúncia com descrição, localização, situação e data de registro.
data class DemoReport(
    // id armazena o valor do campo id no modelo.
    // Identificador numérico usado para diferenciar cada registro.
    val id: Int,
    // title armazena o valor do campo title no modelo.
    // Título que resume a denúncia.
    val title: String,
    // description armazena o valor do campo description no modelo.
    // Relato que explica o problema registrado.
    val description: String,
    // category armazena o valor do campo category no modelo.
    // Categoria usada para classificar a denúncia.
    val category: String,
    // address armazena o valor do campo address no modelo.
    // Endereço ou referência do local.
    val address: String,
    // status armazena o valor do campo status no modelo.
    // Situação atual da denúncia na demonstração.
    val status: String,
    // date armazena o valor do campo date no modelo.
    // Data ou horário demonstrativo em que a denúncia foi registrada.
    val date: String
)

// Enumeração que limita a navegação às abas existentes no aplicativo.
// label guarda o texto que será mostrado no menu inferior.
// Define as opções possíveis da navegação inferior.
// label guarda o nome apresentado ao usuário em cada item do menu.
enum class AppTab(val label: String) {
    // Cada opção representa uma aba fixa da navegação.
    HOME("Início"), // Abre a tela inicial.
    MAP("Mapa"), // Abre a tela de mapa.
    REPORTS("Denúncias"), // Abre a área de denúncias.
    PROFILE("Perfil") // Abre o perfil do usuário.
}

private val demoPoints = listOf(
    DemoPoint(1, "Ecoponto Parque das Flores", "Ponto de descarte", "Rua das Acácias, Jardim Verde", "Seg–Sáb · 08h às 17h", "Reciclagem"),
    DemoPoint(2, "Coleta seletiva Centro", "Ponto de coleta", "Praça Central, Centro", "Ter e Qui · 08h às 12h", "Coleta"),
    DemoPoint(3, "Ecoponto Vila Nova", "Ponto de descarte", "Avenida Principal, Vila Nova", "Seg–Sex · 08h às 16h", "Reciclagem"),
    DemoPoint(4, "Coleta seletiva Bela Vista", "Ponto de coleta", "Rua do Sol, Bela Vista", "Qua e Sáb · 07h às 11h", "Coleta")
)

private val initialReports = listOf(
    DemoReport(1042, "Descarte irregular de entulho", "Há resíduos acumulados próximos à calçada, dificultando a passagem de pedestres.", "Descarte irregular", "Rua das Palmeiras, Jardim Verde", "Pendente", "Hoje · 09:42"),
    DemoReport(1038, "Coleta não realizada", "O caminhão não passou no horário previsto nesta rua.", "Coleta atrasada", "Rua Santa Amélia, Centro", "Em andamento", "Ontem · 16:18"),
    DemoReport(1029, "Acúmulo de resíduos", "Foi observado acúmulo de resíduos próximo ao ponto de ônibus.", "Acúmulo de lixo", "Avenida Principal, Vila Nova", "Resolvida", "22/10/2026 · 11:05")
)

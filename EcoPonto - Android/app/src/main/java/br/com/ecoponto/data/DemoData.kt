package br.com.ecoponto.data

// Define a localização deste arquivo dentro da estrutura de pacotes do aplicativo.

// Mantém os dados fictícios usados nas telas de demonstração.

// Importa bibliotecas e componentes usados neste arquivo.
import br.com.ecoponto.models.*

// Lista de pontos fictícios utilizada pela tela de mapa e pelos indicadores da tela inicial.
// listOf cria uma coleção somente de leitura com os registros informados.
val demoPoints = listOf(

    // Registro fictício de um ponto de coleta usado para preencher o mapa e as listas.
    DemoPoint(1, "Ecoponto Parque das Flores", "Ponto de descarte", "Rua das Acácias, Jardim Verde", "Seg–Sáb · 08h às 17h", "Reciclagem"),

    // Registro fictício de um ponto de coleta usado para preencher o mapa e as listas.
    DemoPoint(2, "Coleta seletiva Centro", "Ponto de coleta", "Praça Central, Centro", "Ter e Qui · 08h às 12h", "Coleta"),

    // Registro fictício de um ponto de coleta usado para preencher o mapa e as listas.
    DemoPoint(3, "Ecoponto Vila Nova", "Ponto de descarte", "Avenida Principal, Vila Nova", "Seg–Sex · 08h às 16h", "Reciclagem"),

    // Registro fictício de um ponto de coleta usado para preencher o mapa e as listas.
    DemoPoint(4, "Coleta seletiva Bela Vista", "Ponto de coleta", "Rua do Sol, Bela Vista", "Qua e Sáb · 07h às 11h", "Coleta")

)



// Lista inicial de denúncias demonstrativas exibida quando o aplicativo é aberto.
// Os registros são mantidos em memória e não são salvos em banco de dados.
val initialReports = listOf(

    // Denúncia fictícia usada como exemplo na interface.
    DemoReport(1042, "Descarte irregular de entulho", "Há resíduos acumulados próximos à calçada, dificultando a passagem de pedestres.", "Descarte irregular", "Rua das Palmeiras, Jardim Verde", "Pendente", "Hoje · 09:42"),

    // Denúncia fictícia usada como exemplo na interface.
    DemoReport(1038, "Coleta não realizada", "O caminhão não passou no horário previsto nesta rua.", "Coleta atrasada", "Rua Santa Amélia, Centro", "Em andamento", "Ontem · 16:18"),

    // Denúncia fictícia usada como exemplo na interface.
    DemoReport(1029, "Acúmulo de resíduos", "Foi observado acúmulo de resíduos próximo ao ponto de ônibus.", "Acúmulo de lixo", "Avenida Principal, Vila Nova", "Resolvida", "22/10/2026 · 11:05")

)


// 1. INICIALIZAÇÃO DO MAPA

const mapa = L.map('mapa').setView(
    [-9.6498, -35.7089],
    12
);

// Adiciona o OpenStreetMap
L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    attribution: '&copy; OpenStreetMap contributors'
}).addTo(mapa);


// 2. CAMADAS E MARCADORES

const marcadores = L.layerGroup().addTo(mapa);

let marcadorDenuncia = null;
let marcadorDestino = null;
let camadaRota = null;


// 3. CARREGAR PONTOS DE COLETA

async function carregarPontosColeta() {

    try {

        const resposta = await fetch(
            '/geoprocessamento/pontos-coleta'
        );

        if (!resposta.ok) {
            throw new Error('Erro ao buscar pontos de coleta.');
        }

        const dados = await resposta.json();

        console.log('Pontos encontrados:', dados);

        dados.pontos_coleta.forEach(ponto => {

            if (
                ponto.latitude == null ||
                ponto.longitude == null
            ) {
                return;
            }

            const marcador = L.marker([
                ponto.latitude,
                ponto.longitude
            ]).addTo(marcadores);

            marcador.bindPopup(`
                <h3>${ponto.nome}</h3>
                <p><strong>Bairro:</strong> ${ponto.bairro}</p>
                <p><strong>Endereço:</strong> ${ponto.endereco}</p>
                <p><strong>Tipo:</strong> ${ponto.tipo}</p>
                <p><strong>Status:</strong> ${ponto.status}</p>
            `);

        });

    } catch (erro) {

        console.error('Erro ao carregar pontos:', erro);

    }

}


// 4. CONSULTAR API

async function consultarPontoMaisProximo(url) {

    const resposta = await fetch(url);

    const dados = await resposta.json();

    if (!resposta.ok) {
        throw new Error(
            dados.detail || 'Não foi possível encontrar o ponto de coleta.'
        );
    }

    return dados;

}


// 5. REMOVER PESQUISA ANTERIOR

function limparPesquisaAnterior() {

    if (camadaRota) {
        mapa.removeLayer(camadaRota);
        camadaRota = null;
    }

    if (marcadorDenuncia) {
        mapa.removeLayer(marcadorDenuncia);
        marcadorDenuncia = null;
    }

    if (marcadorDestino) {
        mapa.removeLayer(marcadorDestino);
        marcadorDestino = null;
    }

}


// 6. EXIBIR MARCADORES E CALCULAR ROTA

async function exibirResultado(dados) {

    const denuncia = dados.denuncia;
    const ponto = dados.ponto_mais_proximo;

    const resultado = document.getElementById('resultado-rota');

    limparPesquisaAnterior();

    // 6.1 MARCADOR DA LOCALIZAÇÃO DO USUÁRIO

    marcadorDenuncia = L.marker([
        denuncia.latitude,
        denuncia.longitude
    ]).addTo(mapa);

    marcadorDenuncia.bindPopup(`
        <h3>Local da denúncia</h3>
        <p>${denuncia.endereco}</p>
    `);


    // 6.2 MARCADOR DO PONTO DE COLETA

    marcadorDestino = L.marker([
        ponto.latitude,
        ponto.longitude
    ]).addTo(mapa);

    marcadorDestino.bindPopup(`
        <h3>${ponto.nome}</h3>
        <p><strong>Bairro:</strong> ${ponto.bairro}</p>
        <p><strong>Status:</strong> ${ponto.status}</p>
    `);


    // 6.3 CONSULTAR ROTA COM OSRM

    const coordenadas = [
        `${denuncia.longitude},${denuncia.latitude}`,
        `${ponto.longitude},${ponto.latitude}`
    ].join(';');

    const urlRota =
        `https://router.project-osrm.org/route/v1/driving/${coordenadas}?overview=full&geometries=geojson`;

    const respostaRota = await fetch(urlRota);

    if (!respostaRota.ok) {
        throw new Error(
            'Não foi possível consultar o serviço de rotas.'
        );
    }

    const dadosRota = await respostaRota.json();

    if (
        dadosRota.code !== 'Ok' ||
        !dadosRota.routes ||
        dadosRota.routes.length === 0
    ) {
        throw new Error('Nenhuma rota foi encontrada.');
    }

    const rota = dadosRota.routes[0];


    // 6.4 DESENHAR ROTA NO MAPA

    camadaRota = L.geoJSON(rota.geometry, {

        style: {
            color: '#198754',
            weight: 6,
            opacity: 0.85
        }

    }).addTo(mapa);


    // Ajustar o zoom para mostrar o percurso

    const limites = L.featureGroup([
        camadaRota,
        marcadorDenuncia,
        marcadorDestino
    ]);

    mapa.fitBounds(limites.getBounds(), {
        padding: [40, 40]
    });


    // 6.5 CALCULAR DISTÂNCIA E TEMPO DA ROTA

    const distanciaKm = (
        rota.distance / 1000
    ).toFixed(2);

    const duracaoMinutos = Math.round(
        rota.duration / 60
    );


    // 6.6 EXIBIR RESULTADO

    resultado.innerHTML = `

        <div class="resultado-card">

            <h3>📍 Ponto de coleta mais próximo encontrado!</h3>

            <div class="resultado-grid">

                <div class="info-item">
                    <span>Nome do ponto</span>
                    <strong>${ponto.nome}</strong>
                </div>

                <div class="info-item">
                    <span>Bairro</span>
                    <strong>${ponto.bairro}</strong>
                </div>

                <div class="info-item">
                    <span>Endereço</span>
                    <strong>${ponto.endereco}</strong>
                </div>

                <div class="info-item">
                    <span>Status de validação</span>
                    <strong class="status-pendente">
                        ${ponto.status}
                    </strong>
                </div>

            </div>

            <div class="resultado-rota-info">

                <div class="rota-dado">
                    <span>Distância pela rota</span>
                    <strong>${distanciaKm} km</strong>
                </div>

                <div class="rota-dado">
                    <span>Tempo estimado de carro</span>
                    <strong>${duracaoMinutos} min</strong>
                </div>

            </div>

        </div>

    `;

    marcadorDestino.openPopup();

}


// 7. PESQUISAR ENDEREÇO MANUALMENTE

document.getElementById('form-denuncia').addEventListener(
    'submit',
    async function(event) {

        event.preventDefault();

        const endereco = document
            .getElementById('endereco-denuncia')
            .value.trim();

        const resultado = document.getElementById('resultado-rota');

        if (!endereco) {
            resultado.textContent = 'Informe um endereço válido.';
            return;
        }

        resultado.textContent =
            'Localizando endereço e ponto de coleta...';

        try {

            const parametros = new URLSearchParams({
                endereco: endereco,
                raio_metros: 500
            });

            const dados = await consultarPontoMaisProximo(
                `/geoprocessamento/ponto-mais-proximo-endereco?${parametros}`
            );

            await exibirResultado(dados);

        } catch (erro) {

            console.error('Erro na pesquisa:', erro);

            resultado.textContent = erro.message;

        }

    }

);


// 8. UTILIZAR LOCALIZAÇÃO ATUAL

document.getElementById('btn-localizacao').addEventListener(
    'click',
    function() {

        const resultado = document.getElementById('resultado-rota');
        const botao = document.getElementById('btn-localizacao');

        // Verifica se o navegador oferece geolocalização

        if (!navigator.geolocation) {

            resultado.textContent =
                'Seu navegador não suporta geolocalização.';

            return;

        }

        resultado.textContent =
            'Obtendo sua localização atual...';

        botao.disabled = true;

        const textoOriginal = botao.textContent;
        botao.textContent = 'Obtendo localização...';


        // Solicita permissão e obtém as coordenadas

        navigator.geolocation.getCurrentPosition(

            async function(posicao) {

                try {

                    const latitude = posicao.coords.latitude;
                    const longitude = posicao.coords.longitude;

                    console.log('Latitude:', latitude);
                    console.log('Longitude:', longitude);

                    resultado.textContent =
                        'Localização encontrada! Procurando ponto de coleta...';


                    // Envia as coordenadas para o FastAPI

                    const parametros = new URLSearchParams({
                        latitude: latitude,
                        longitude: longitude,
                        raio_metros: 500
                    });

                    const dados = await consultarPontoMaisProximo(
                        `/geoprocessamento/ponto-mais-proximo-coordenadas?${parametros}`
                    );

                    // Exibe o ponto e a rota no mapa

                    await exibirResultado(dados);

                } catch (erro) {

                    console.error('Erro na geolocalização:', erro);

                    resultado.textContent = erro.message;

                } finally {

                    botao.disabled = false;
                    botao.textContent = textoOriginal;

                }

            },

            function(erro) {

                let mensagem;

                switch (erro.code) {

                    case erro.PERMISSION_DENIED:
                        mensagem =
                            'Você não permitiu o acesso à sua localização.';
                        break;

                    case erro.POSITION_UNAVAILABLE:
                        mensagem =
                            'Não foi possível identificar sua localização.';
                        break;

                    case erro.TIMEOUT:
                        mensagem =
                            'O tempo para obter sua localização terminou.';
                        break;

                    default:
                        mensagem =
                            'Ocorreu um erro ao obter sua localização.';

                }

                resultado.textContent = mensagem;

                botao.disabled = false;
                botao.textContent = textoOriginal;

            },

            {
                enableHighAccuracy: true,
                timeout: 15000,
                maximumAge: 0
            }

        );

    }

);


// 9. CARREGAR PONTOS AO ABRIR A PÁGINA

carregarPontosColeta();

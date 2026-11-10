

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

            // Ignora pontos sem coordenadas
            if (
                ponto.latitude == null ||
                ponto.longitude == null
            ) {
                return;
            }

            // Cria marcador do ponto de coleta
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



// 4. PESQUISAR ENDEREÇO DA DENÚNCIA


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

            
            // 4.1 CONSULTAR A API
            

            const resposta = await fetch(
                `/geoprocessamento/ponto-mais-proximo-endereco?endereco=${encodeURIComponent(endereco)}&raio_metros=500`
            );

            const dados = await resposta.json();

            if (!resposta.ok) {

                throw new Error(
                    dados.detail || 'Não foi possível localizar o endereço.'
                );

            }

            const denuncia = dados.denuncia;
            const ponto = dados.ponto_mais_proximo;

            
            // 4.2 REMOVER PESQUISA ANTERIOR
            

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

            
            // 4.3 MARCADOR DA DENÚNCIA
            

            marcadorDenuncia = L.marker([
                denuncia.latitude,
                denuncia.longitude
            ]).addTo(mapa);

            marcadorDenuncia.bindPopup(`
                <h3>Local da denúncia</h3>
                <p>${endereco}</p>
            `);

            
            // 4.4 MARCADOR DO PONTO DE COLETA
            

            marcadorDestino = L.marker([
                ponto.latitude,
                ponto.longitude
            ]).addTo(mapa);

            marcadorDestino.bindPopup(`
                <h3>${ponto.nome}</h3>
                <p><strong>Bairro:</strong> ${ponto.bairro}</p>
                <p><strong>Status:</strong> ${ponto.status}</p>
            `);

            
            // 4.5 CALCULAR ROTA COM OSRM
            

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

            
            // 4.6 DESENHAR ROTA NO MAPA
            

            camadaRota = L.geoJSON(rota.geometry, {

                style: {
                    color: '#198754',
                    weight: 6,
                    opacity: 0.85
                }

            }).addTo(mapa);

            // Ajusta o zoom para mostrar todo o percurso
            const limites = L.featureGroup([
                camadaRota,
                marcadorDenuncia,
                marcadorDestino
            ]);

            mapa.fitBounds(limites.getBounds(), {
                padding: [40, 40]
            });

            
            // 4.7 CALCULAR DISTÂNCIA E TEMPO
            

            const distanciaKm = (
                rota.distance / 1000
            ).toFixed(2);

            const duracaoMinutos = Math.round(
                rota.duration / 60
            );

            
            // 4.8 EXIBIR RESULTADO FORMATADO
            

            resultado.innerHTML = `

                <div class="resultado-card">

                    <h3>📍 Ponto de coleta mais proximo da sua localidade encontrado!!</h3>

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

            // Abre o popup do ponto de coleta
            marcadorDestino.openPopup();

        } catch (erro) {

            console.error('Erro na pesquisa:', erro);

            resultado.textContent = erro.message;

        }

    }

);



// 5. CARREGAR PONTOS AO ABRIR A PÁGINA


carregarPontosColeta();

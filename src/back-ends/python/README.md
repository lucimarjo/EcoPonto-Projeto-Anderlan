# 🐍 Python

Responsável pelo microsserviço de geoprocessamento do EcoPonto.

- Integração com as APIs do OpenStreetMap;
- Geolocalização de endereços;
- Cálculo de distâncias geográficas;
- Identificação dos pontos de coleta mais próximos;
- Exibição de rotas e pontos de coleta em mapa.

## 🤔 Por que escolhemos?

O **Python** foi escolhido por possuir um ecossistema de bibliotecas geográficas simples e eficiente, permitindo trabalhar com geolocalização, análise espacial e integração com APIs externas.

O **FastAPI** foi escolhido para estruturar o microsserviço e disponibilizar seus endpoints, além de gerar documentação interativa automaticamente.

## 📂 Estrutura da pasta

```text
python/
├── README.md
└── geoprocessamento/
    ├── app/
    │   ├── data/
    │   ├── routes/
    │   ├── services/
    │   ├── static/
    │   ├── main.py
    │   └── preencher_coordenadas.py
    └── requirements.txt
```

## ⚙️ Tecnologias utilizadas

- Python
- FastAPI
- Uvicorn
- OpenStreetMap
- Leaflet
- OSRM
- Nominatim
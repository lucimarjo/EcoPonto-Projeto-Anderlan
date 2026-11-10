import requests


def buscar_localizacao(endereco):

    url = "https://nominatim.openstreetmap.org/search"

    parametros = {
        "q": endereco,
        "format": "json",
        "limit": 1
    }

    cabecalho = {
        "User-Agent": "EcoPonto/1.0"
    }

    resposta = requests.get(
        url,
        params=parametros,
        headers=cabecalho,
        timeout=15
    )

    resposta.raise_for_status()

    dados = resposta.json()

    if not dados:
        return None

    return {
        "endereco": dados[0]["display_name"],
        "latitude": float(dados[0]["lat"]),
        "longitude": float(dados[0]["lon"])
    }

import json
from pathlib import Path

from app.services.analise_espacial import calcular_distancia


def encontrar_ponto_mais_proximo(denuncia_lat, denuncia_lon):

    # Caminho do arquivo com os pontos cadastrados
    arquivo = Path(__file__).resolve().parents[1] / "data" / "pontos_coleta.json"

    # Carrega os pontos de coleta
    with open(arquivo, "r", encoding="utf-8") as f:
        pontos = json.load(f)

    ponto_mais_proximo = None
    menor_distancia = None

    # Percorre todos os pontos cadastrados
    for ponto in pontos:

        latitude = ponto.get("latitude")
        longitude = ponto.get("longitude")

        # Ignora pontos sem coordenadas
        if latitude is None or longitude is None:
            continue

        # Calcula a distância entre a denúncia e o ponto
        distancia = calcular_distancia(
            denuncia_lat,
            denuncia_lon,
            latitude,
            longitude
        )

        # Verifica se é o ponto mais próximo encontrado
        if menor_distancia is None or distancia < menor_distancia:

            menor_distancia = distancia
            ponto_mais_proximo = ponto

    if ponto_mais_proximo is None:
        return None

    return {
        "ponto": ponto_mais_proximo,
        "distancia_metros": menor_distancia
    }
def listar_pontos_coleta():

    # Caminho do arquivo com os pontos cadastrados
    arquivo = Path(__file__).resolve().parents[1] / "data" / "pontos_coleta.json"

    # Carrega os pontos de coleta
    with open(arquivo, "r", encoding="utf-8") as f:
        pontos = json.load(f)

    return pontos
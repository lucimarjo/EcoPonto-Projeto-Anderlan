
import json
import time

from pathlib import Path
from requests.exceptions import RequestException

from app.services.openstreetmap import buscar_localizacao


# Caminho do arquivo JSON
arquivo = Path(__file__).parent / "data" / "pontos_coleta.json"


# Abre e lê os pontos cadastrados
with open(arquivo, "r", encoding="utf-8") as f:
    pontos = json.load(f)


# Percorre todos os pontos de coleta
for ponto in pontos:

    nome = ponto.get("nome", "Ponto sem nome")
    endereco = ponto.get("endereco")

    

    print(f"\nConsultando: {nome}")

    try:

        consulta = f"{endereco}, {ponto['bairro']}, Maceió, AL, Brasil"

        resultado = buscar_localizacao(consulta)    

        if resultado is None:

            ponto["latitude"] = None
            ponto["longitude"] = None
            ponto["status"] = "PENDENTE_GEOCODIFICACAO"

            print("Endereço não encontrado.")

        else:

            ponto["latitude"] = resultado["latitude"]
            ponto["longitude"] = resultado["longitude"]

            # Guarda o endereço retornado para conferência
            ponto["endereco_geocodificado"] = resultado["endereco"]

            ponto["status"] = "PENDENTE_VALIDACAO"

            print("Coordenadas encontradas!")
            print("Latitude:", resultado["latitude"])
            print("Longitude:", resultado["longitude"])

    except RequestException as erro:

        ponto["status"] = "ERRO_CONSULTA"
        print("Erro na consulta:", erro)

    # Salva o progresso após cada ponto
    with open(arquivo, "w", encoding="utf-8") as f:
        json.dump(pontos, f, ensure_ascii=False, indent=4)

    # Respeita o intervalo de consultas do OpenStreetMap
    time.sleep(1)


print("\nProcesso finalizado!")

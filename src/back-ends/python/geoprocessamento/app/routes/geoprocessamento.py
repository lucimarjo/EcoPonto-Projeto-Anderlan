from fastapi import APIRouter, HTTPException, Query
from requests.exceptions import RequestException
from app.services.analise_espacial import calcular_distancia
from app.services.openstreetmap import buscar_localizacao
import time
from app.services.pontos_coleta import encontrar_ponto_mais_proximo
from app.services.pontos_coleta import listar_pontos_coleta

router = APIRouter(
    prefix="/geoprocessamento",
    tags=["Geoprocessamento"]
)


@router.get("/localizar")
def localizar(endereco: str = Query(min_length=3)):

    try:
        resultado = buscar_localizacao(endereco)

        if resultado is None:
            raise HTTPException(
                status_code=404,
                detail="Localização não encontrada"
            )

        return resultado

    except RequestException:
        raise HTTPException(
            status_code=502,
            detail="Erro ao consultar o OpenStreetMap"
        )

@router.get("/analisar-distancia")
def analisar_distancia(
    denuncia_lat: float = Query(ge=-90, le=90),
    denuncia_lon: float = Query(ge=-180, le=180),
    coleta_lat: float = Query(ge=-90, le=90),
    coleta_lon: float = Query(ge=-180, le=180),
    raio_metros: float = Query(default=500, gt=0)
):

    distancia = calcular_distancia(
        denuncia_lat,
        denuncia_lon,
        coleta_lat,
        coleta_lon
    )

    return {
        "distancia_metros": round(distancia, 2),
        "raio_metros": raio_metros,
        "dentro_do_raio": distancia <= raio_metros
    }

@router.get("/analisar-enderecos")
def analisar_enderecos(
    endereco_denuncia: str = Query(min_length=3),
    endereco_coleta: str = Query(min_length=3),
    raio_metros: float = Query(default=500, gt=0)
):

    try:
        # Localiza o endereço da denúncia
        denuncia = buscar_localizacao(endereco_denuncia)

        if denuncia is None:
            raise HTTPException(
                status_code=404,
                detail="Endereço da denúncia não encontrado"
            )

        # Aguarda antes da próxima consulta ao OpenStreetMap
        time.sleep(1)

        # Localiza o endereço do ponto de coleta
        coleta = buscar_localizacao(endereco_coleta)

        if coleta is None:
            raise HTTPException(
                status_code=404,
                detail="Endereço do ponto de coleta não encontrado"
            )

        # Calcula a distância entre os dois locais
        distancia = calcular_distancia(
            denuncia["latitude"],
            denuncia["longitude"],
            coleta["latitude"],
            coleta["longitude"]
        )

        return {
            "denuncia": denuncia,
            "ponto_coleta": coleta,
            "distancia_metros": round(distancia, 2),
            "raio_metros": raio_metros,
            "dentro_do_raio": distancia <= raio_metros
        }

    except RequestException:
        raise HTTPException(
            status_code=502,
            detail="Erro ao consultar o OpenStreetMap"
        )

@router.get("/ponto-mais-proximo-endereco")
def ponto_mais_proximo_endereco(
    endereco: str = Query(min_length=5),
    raio_metros: float = Query(default=500, gt=0)
):

    try:
        # Converte o endereço informado em coordenadas
        denuncia = buscar_localizacao(endereco)

        if denuncia is None:
            raise HTTPException(
                status_code=404,
                detail="Endereço da denúncia não encontrado"
            )

        # Encontra o ponto de coleta mais próximo
        resultado = encontrar_ponto_mais_proximo(
            denuncia["latitude"],
            denuncia["longitude"]
        )

        if resultado is None:
            raise HTTPException(
                status_code=404,
                detail="Nenhum ponto de coleta cadastrado"
            )

        ponto = resultado["ponto"]
        distancia = resultado["distancia_metros"]

        return {
            "denuncia": {
                "endereco": denuncia["endereco"],
                "latitude": denuncia["latitude"],
                "longitude": denuncia["longitude"]
            },
            "ponto_mais_proximo": {
                "id": ponto["id"],
                "nome": ponto["nome"],
                "bairro": ponto["bairro"],
                "endereco": ponto["endereco"],
                "latitude": ponto["latitude"],
                "longitude": ponto["longitude"],
                "status": ponto["status"]
            },
            "distancia_metros": round(distancia, 2),
            "raio_metros": raio_metros,
            "dentro_do_raio": distancia <= raio_metros
        }

    except RequestException:
        raise HTTPException(
            status_code=502,
            detail="Erro ao consultar o OpenStreetMap"
        )


@router.get("/pontos-coleta")
def listar_pontos():

    pontos = listar_pontos_coleta()

    return {
        "total": len(pontos),
        "pontos_coleta": pontos
    }
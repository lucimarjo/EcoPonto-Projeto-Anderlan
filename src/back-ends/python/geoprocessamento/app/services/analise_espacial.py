from shapely.geometry import Point
from pyproj import Geod


def calcular_distancia(
    denuncia_lat,
    denuncia_lon,
    coleta_lat,
    coleta_lon
):

    # Criando os pontos geográficos
    denuncia = Point(denuncia_lon, denuncia_lat)
    coleta = Point(coleta_lon, coleta_lat)

    # Calculando a distância geográfica
    geod = Geod(ellps="WGS84")

    azimute, azimute_inverso, distancia = geod.inv(
        denuncia.x,
        denuncia.y,
        coleta.x,
        coleta.y
    )

    return distancia
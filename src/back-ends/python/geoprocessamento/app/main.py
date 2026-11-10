from fastapi import FastAPI
from fastapi.staticfiles import StaticFiles
from fastapi.responses import FileResponse
from pathlib import Path
from app.routes import geoprocessamento

app = FastAPI(
    title="EcoPonto - Geoprocessamento",
    description="Microsserviço de geoprocessamento",
    version="1.0.0"
)

# Caminho da pasta static
CAMINHO_STATIC = Path(__file__).resolve().parent / "static"

# Disponibiliza os arquivos CSS e JavaScript
app.mount(
    "/static",
    StaticFiles(directory=CAMINHO_STATIC),
    name="static"
)

# Registra as rotas de geoprocessamento
app.include_router(geoprocessamento.router)


@app.get("/")
def inicio():
    return FileResponse(CAMINHO_STATIC / "index.html")

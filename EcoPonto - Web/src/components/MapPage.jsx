
import { useState } from "react";

const registros = [
  {
    id: "DEN-2026-001",
    local: "Centro",
    categoria: "Descarte irregular",
    prioridade: "Alta",
    status: "Nova",
    data: "22 set. 2026",
    descricao: "Resíduos descartados em área próxima à via principal.",
    x: 54,
    y: 32,
  },
  {
    id: "DEN-2026-002",
    local: "Chã do Pilar",
    categoria: "Acúmulo de lixo",
    prioridade: "Média",
    status: "Em análise",
    data: "21 set. 2026",
    descricao: "Acúmulo de resíduos em espaço público.",
    x: 29,
    y: 43,
  },
  {
    id: "DEN-2026-004",
    local: "Santa Luzia",
    categoria: "Descarte irregular",
    prioridade: "Baixa",
    status: "Nova",
    data: "20 set. 2026",
    descricao: "Descarte de materiais em terreno aberto.",
    x: 69,
    y: 66,
  },
  {
    id: "DEN-2026-005",
    local: "Alto do Cruzeiro",
    categoria: "Entulho",
    prioridade: "Média",
    status: "Resolvida",
    data: "19 set. 2026",
    descricao: "Descarte de entulho em área residencial.",
    x: 43,
    y: 79,
  },
];

function MapPage() {
  const [status, setStatus] = useState("Todos");
  const [categoria, setCategoria] = useState("Todas");
  const [prioridade, setPrioridade] = useState("Todas");
  const [periodo, setPeriodo] = useState("Últimos 30 dias");
  const [selecionada, setSelecionada] = useState(registros[0]);

  const registrosFiltrados = registros.filter((item) => (
    (status === "Todos" || item.status === status) &&
    (categoria === "Todas" || item.categoria === categoria) &&
    (prioridade === "Todas" || item.prioridade === prioridade)
  ));

  const registroSelecionado = registrosFiltrados.find(
    (item) => item.id === selecionada?.id
  );

  function selecionarRegistro(item) {
    setSelecionada(item);
  }

  function limparFiltros() {
    setStatus("Todos");
    setCategoria("Todas");
    setPrioridade("Todas");
    setPeriodo("Últimos 30 dias");
    setSelecionada(registros[0]);
  }

  return (
    <div className="eco-map-page">
      <section className="eco-map-heading">
        <div>
          <span className="eco-eyebrow">LOCALIZAÇÃO E MONITORAMENTO</span>
          <h1>Mapa de denúncias</h1>
          <p>
            Visualize a distribuição geográfica dos registros recentes em Pilar.
          </p>
        </div>
      </section>

      <section className="eco-map-filters">
        <label>
          Status
          <select value={status} onChange={(e) => setStatus(e.target.value)}>
            <option>Todos</option>
            <option>Nova</option>
            <option>Em análise</option>
            <option>Resolvida</option>
          </select>
        </label>

        <label>
          Categoria
          <select value={categoria} onChange={(e) => setCategoria(e.target.value)}>
            <option>Todas</option>
            <option>Descarte irregular</option>
            <option>Acúmulo de lixo</option>
            <option>Entulho</option>
          </select>
        </label>

        <label>
          Prioridade
          <select
            value={prioridade}
            onChange={(e) => setPrioridade(e.target.value)}
          >
            <option>Todas</option>
            <option>Alta</option>
            <option>Média</option>
            <option>Baixa</option>
          </select>
        </label>

        <label>
          Período
          <select value={periodo} onChange={(e) => setPeriodo(e.target.value)}>
            <option>Últimos 7 dias</option>
            <option>Últimos 30 dias</option>
            <option>Últimos 90 dias</option>
            <option>Todos os períodos</option>
          </select>
        </label>

        <button className="eco-map-clear" onClick={limparFiltros}>
          Limpar filtros
        </button>
      </section>

      <section className="eco-map-layout">
        <article className="eco-map-panel">
          <div className="eco-map-topline">
            <div>
              <strong>Distribuição geográfica</strong>
              <span>{registrosFiltrados.length} registros encontrados</span>
            </div>

            <span className="eco-map-location">⌖ Pilar — AL</span>
          </div>

          <div className="eco-map-canvas">
            <div className="eco-map-road road-one" />
            <div className="eco-map-road road-two" />
            <div className="eco-map-road road-three" />
            <div className="eco-map-road road-four" />
            <div className="eco-map-road road-five" />
            <div className="eco-map-road road-six" />

            <div className="eco-map-area area-one" />
            <div className="eco-map-area area-two" />
            <div className="eco-map-area area-three" />

            <span className="eco-map-neighborhood neighborhood-one">
              CHÃ DO PILAR
            </span>

            <span className="eco-map-neighborhood neighborhood-two">
              CENTRO
            </span>

            <span className="eco-map-neighborhood neighborhood-three">
              SANTA LUZIA
            </span>

            <span className="eco-map-neighborhood neighborhood-four">
              ALTO DO CRUZEIRO
            </span>

            {registrosFiltrados.map((item) => (
              <button
                key={item.id}
                className={`eco-map-marker marker-${item.prioridade.toLowerCase()} ${
                  selecionada?.id === item.id ? "marker-selected" : ""
                }`}
                style={{
                  left: `${item.x}%`,
                  top: `${item.y}%`,
                }}
                onClick={() => selecionarRegistro(item)}
                title={item.id}
                aria-label={`Selecionar ${item.id}`}
              >
                <span>●</span>
              </button>
            ))}

            {registroSelecionado && (
              <div className="eco-map-popup-reference">
                <div className="eco-map-popup-top">
                  <strong>{registroSelecionado.id}</strong>

                  <span className={`eco-map-badge badge-${registroSelecionado.status.toLowerCase().replace(" ", "-")}`}>
                    {registroSelecionado.status}
                  </span>
                </div>

                <p>{registroSelecionado.categoria}</p>

                <span className="eco-map-popup-description">
                  {registroSelecionado.descricao}
                </span>

                <div className="eco-map-popup-info">
                  <span>Bairro</span>
                  <strong>{registroSelecionado.local}</strong>

                  <span>Prioridade</span>
                  <strong>{registroSelecionado.prioridade}</strong>

                  <span>Data</span>
                  <strong>{registroSelecionado.data}</strong>
                </div>

                <button
                  onClick={() =>
                    window.alert(
                      `${registroSelecionado.id}\n\n${registroSelecionado.descricao}\nLocal: ${registroSelecionado.local}\nStatus: ${registroSelecionado.status}`
                    )
                  }
                >
                  Ver detalhes
                </button>
              </div>
            )}

            <div className="eco-map-legend">
              <span><i className="legend-high" /> Alta</span>
              <span><i className="legend-medium" /> Atenção</span>
              <span><i className="legend-low" /> Resolvida</span>
            </div>
          </div>
        </article>

        <aside className="eco-map-recent">
          <div className="eco-map-recent-heading">
            <h2>Registros recentes</h2>
            <p>Clique em um registro para abrir os detalhes.</p>
          </div>

          <div className="eco-map-record-list">
            {registrosFiltrados.map((item) => (
              <button
                key={item.id}
                className={`eco-map-record ${
                  selecionada?.id === item.id ? "record-active" : ""
                }`}
                onClick={() => selecionarRegistro(item)}
              >
                <strong>{item.id}</strong>
                <span>
                  {item.local} · {item.prioridade}
                </span>
                <small>{item.status}</small>
              </button>
            ))}

            {registrosFiltrados.length === 0 && (
              <p className="eco-map-empty">
                Nenhum registro encontrado.
              </p>
            )}
          </div>
        </aside>
      </section>
    </div>
  );
}

export default MapPage;


import { useState } from "react";
import Sidebar from "../components/Sidebar";
import Topbar from "../components/Topbar";
import StatCard from "../components/StatCard";
import "../App.css";

const denuncias = [
  { id: "DEN-001", local: "Centro — Rua Principal", categoria: "Descarte irregular", data: "06/11/2026", status: "Nova" },
  { id: "DEN-002", local: "Bairro São José", categoria: "Acúmulo de lixo", data: "05/11/2026", status: "Em análise" },
  { id: "DEN-003", local: "Praça da Matriz", categoria: "Entulho", data: "05/11/2026", status: "Resolvida" },
  { id: "DEN-004", local: "Rua das Flores", categoria: "Descarte irregular", data: "04/11/2026", status: "Em análise" },
];

const atividades = [
  { icon: "✓", titulo: "Denúncia resolvida", descricao: "DEN-003 foi marcada como resolvida.", tempo: "Há 15 minutos" },
  { icon: "＋", titulo: "Nova denúncia recebida", descricao: "Uma nova ocorrência foi registrada.", tempo: "Há 42 minutos" },
  { icon: "↻", titulo: "Denúncia em análise", descricao: "DEN-002 está sendo analisada.", tempo: "Há 1 hora" },
];

function Icon({ name }) {
  const icons = {
    arrow: "↗",
  };

  return (
    <span className="eco-icon" aria-hidden="true">
      {icons[name] || "•"}
    </span>
  );
}

function Home() {
  const [paginaAtiva, setPaginaAtiva] = useState("Dashboard");
  const [periodo, setPeriodo] = useState("Últimos 7 dias");
  const [pesquisa, setPesquisa] = useState("");
  const [menuAberto, setMenuAberto] = useState(false);

  const denunciasFiltradas = denuncias.filter((item) =>
    `${item.id} ${item.local} ${item.categoria} ${item.status}`
      .toLowerCase()
      .includes(pesquisa.toLowerCase())
  );

  return (
    <div className="eco-dashboard">
      <Sidebar
        paginaAtiva={paginaAtiva}
        setPaginaAtiva={setPaginaAtiva}
        menuAberto={menuAberto}
        setMenuAberto={setMenuAberto}
      />

      <main className="eco-main">
        <Topbar
          paginaAtiva={paginaAtiva}
          pesquisa={pesquisa}
          setPesquisa={setPesquisa}
          menuAberto={menuAberto}
          setMenuAberto={setMenuAberto}
        />

        <div className="eco-content">
          <section className="eco-welcome">
            <div>
              <span className="eco-eyebrow">VISÃO GERAL</span>
              <h1>
                {paginaAtiva === "Dashboard"
                  ? "Central de Denúncias"
                  : paginaAtiva}
              </h1>
              <p>
                Acompanhe e gerencie as ocorrências ambientais da sua cidade.
              </p>
            </div>

            <select
              className="eco-period-select"
              value={periodo}
              onChange={(event) => setPeriodo(event.target.value)}
              aria-label="Selecionar período"
            >
              <option>Últimos 7 dias</option>
              <option>Últimos 30 dias</option>
              <option>Últimos 90 dias</option>
            </select>
          </section>

          {/* INDICADORES */}
          <section className="eco-kpi-grid">
            <StatCard
              titulo="Novas denúncias"
              valor="128"
              icone="＋"
              cor="green"
              variacao="↑ 12,5%"
              descricao="em relação ao período anterior"
            />

            <StatCard
              titulo="Em análise"
              valor="36"
              icone="◷"
              cor="orange"
              variacao="Aguardando"
              descricao="providências da equipe"
              tipoVariacao="neutral"
            />

            <StatCard
              titulo="Denúncias resolvidas"
              valor="92"
              icone="✓"
              cor="blue"
              variacao="↑ 8,2%"
              descricao="em relação ao período anterior"
            />

            <StatCard
              titulo="Tempo médio de resolução"
              valor="2,4"
              unidade="dias"
              icone="◷"
              cor="purple"
              variacao="↓ 15%"
              descricao="redução no tempo médio"
            />
          </section>

          {/* GRÁFICOS */}
          <section className="eco-charts-grid">
            <article className="eco-panel eco-evolution">
              <div className="eco-panel-heading">
                <div>
                  <h2>Visão geral das denúncias</h2>
                  <p>Acompanhe a evolução das ocorrências.</p>
                </div>
                <span className="eco-chart-legend">
                  <i /> Denúncias recebidas
                </span>
              </div>

              <div className="eco-bar-chart">
                <div className="eco-y-labels">
                  <span>40</span>
                  <span>30</span>
                  <span>20</span>
                  <span>10</span>
                  <span>0</span>
                </div>

                <div className="eco-chart-area">
                  <div className="eco-chart-lines">
                    {[1, 2, 3, 4, 5].map((line) => (
                      <span key={line} />
                    ))}
                  </div>

                  <div className="eco-bars">
                    {[
                      ["Seg", 42],
                      ["Ter", 65],
                      ["Qua", 52],
                      ["Qui", 83],
                      ["Sex", 62],
                      ["Sáb", 95],
                      ["Dom", 72],
                    ].map(([dia, altura]) => (
                      <div className="eco-bar-column" key={dia}>
                        <div className="eco-bar-track">
                          <div
                            className="eco-bar"
                            style={{ height: `${altura}%` }}
                          />
                        </div>
                        <span>{dia}</span>
                      </div>
                    ))}
                  </div>
                </div>
              </div>
            </article>

            <article className="eco-panel eco-distribution">
              <div className="eco-panel-heading">
                <div>
                  <h2>Denúncias por status</h2>
                  <p>Distribuição das ocorrências.</p>
                </div>
              </div>

              <div className="eco-donut-area">
                <div className="eco-donut">
                  <div className="eco-donut-center">
                    <strong>128</strong>
                    <span>Total</span>
                  </div>
                </div>
              </div>

              <div className="eco-status-legend">
                <div>
                  <i className="status-green" />
                  <span>Resolvidas</span>
                  <strong>72%</strong>
                </div>
                <div>
                  <i className="status-orange" />
                  <span>Em análise</span>
                  <strong>20%</strong>
                </div>
                <div>
                  <i className="status-gray" />
                  <span>Novas</span>
                  <strong>8%</strong>
                </div>
              </div>
            </article>
          </section>

          {/* DENÚNCIAS E ATIVIDADES */}
          <section className="eco-bottom-grid">
            <article className="eco-panel eco-recent">
              <div className="eco-panel-heading">
                <div>
                  <h2>Denúncias recentes</h2>
                  <p>Acompanhe as últimas ocorrências registradas.</p>
                </div>

                <button
                  className="eco-text-button"
                  onClick={() => setPaginaAtiva("Lista de denúncias")}
                >
                  Ver todas <Icon name="arrow" />
                </button>
              </div>

              <div className="eco-table-wrapper">
                <table className="eco-table">
                  <thead>
                    <tr>
                      <th>IDENTIFICAÇÃO</th>
                      <th>LOCAL</th>
                      <th>CATEGORIA</th>
                      <th>DATA</th>
                      <th>STATUS</th>
                    </tr>
                  </thead>

                  <tbody>
                    {denunciasFiltradas.map((item) => (
                      <tr key={item.id}>
                        <td>
                          <strong className="eco-id">{item.id}</strong>
                        </td>
                        <td>{item.local}</td>
                        <td>{item.categoria}</td>
                        <td>{item.data}</td>
                        <td>
                          <span
                            className={`eco-status status-${item.status
                              .toLowerCase()
                              .replace(" ", "-")}`}
                          >
                            {item.status}
                          </span>
                        </td>
                      </tr>
                    ))}

                    {denunciasFiltradas.length === 0 && (
                      <tr>
                        <td colSpan="5" className="eco-empty">
                          Nenhuma denúncia encontrada.
                        </td>
                      </tr>
                    )}
                  </tbody>
                </table>
              </div>
            </article>

            <article className="eco-panel eco-activity">
              <div className="eco-panel-heading">
                <div>
                  <h2>Atividades recentes</h2>
                  <p>Últimas atualizações do sistema.</p>
                </div>
              </div>

              <div className="eco-activity-list">
                {atividades.map((atividade, index) => (
                  <div className="eco-activity-item" key={index}>
                    <div className="eco-activity-icon">
                      {atividade.icon}
                    </div>
                    <div>
                      <strong>{atividade.titulo}</strong>
                      <p>{atividade.descricao}</p>
                      <span>{atividade.tempo}</span>
                    </div>
                  </div>
                ))}
              </div>
            </article>
          </section>

          <footer className="eco-footer">
            <span>
              © 2026 EcoPonto — Sistema de Gestão Ambiental
            </span>
            <span>
              Juntos por uma cidade mais limpa <b>♥</b>
            </span>
          </footer>
        </div>
      </main>
    </div>
  );
}

export default Home;

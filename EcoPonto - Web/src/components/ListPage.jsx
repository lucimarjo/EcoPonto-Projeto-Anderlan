
import { useMemo, useState } from "react";

const DENUNCIAS_INICIAIS = [
  {
    id: "DEN-2026-001",
    local: "Centro — Rua Principal",
    categoria: "Descarte irregular",
    data: "06/11/2026",
    prioridade: "Alta",
    status: "Nova",
    responsavel: "Equipe Centro",
    descricao: "Resíduos descartados em área próxima à via principal.",
  },
  {
    id: "DEN-2026-002",
    local: "Bairro São José",
    categoria: "Acúmulo de lixo",
    data: "05/11/2026",
    prioridade: "Média",
    status: "Em análise",
    responsavel: "Equipe Norte",
    descricao:
      "Moradores relatam atraso na rota de coleta seletiva e presença de materiais recicláveis acumulados na calçada.",
  },
  {
    id: "DEN-2026-003",
    local: "Praça da Matriz",
    categoria: "Entulho",
    data: "05/11/2026",
    prioridade: "Baixa",
    status: "Resolvida",
    responsavel: "Equipe Centro",
    descricao: "Entulho recolhido pela equipe responsável.",
  },
  {
    id: "DEN-2026-004",
    local: "Rua das Flores",
    categoria: "Descarte irregular",
    data: "04/11/2026",
    prioridade: "Alta",
    status: "Em análise",
    responsavel: "Equipe Sul",
    descricao: "Descarte de materiais em calçada pública.",
  },
  {
    id: "DEN-2026-005",
    local: "Bairro Vila Nova",
    categoria: "Queimada",
    data: "03/11/2026",
    prioridade: "Alta",
    status: "Nova",
    responsavel: "Não atribuído",
    descricao: "Relato de queimada em terreno próximo às residências.",
  },
  {
    id: "DEN-2026-006",
    local: "Avenida Central",
    categoria: "Poluição",
    data: "02/11/2026",
    prioridade: "Média",
    status: "Arquivada",
    responsavel: "Equipe Centro",
    descricao: "Ocorrência analisada e arquivada.",
  },
];

const EQUIPES = [
  "Equipe Centro",
  "Equipe Norte",
  "Equipe Sul",
  "Equipe Leste",
  "Equipe Oeste",
];

function Icon({ children }) {
  return <span className="detail-icon">{children}</span>;
}

function DetalhesDenuncia({ denuncia, onVoltar, onAtualizar }) {
  const [status, setStatus] = useState(denuncia.status);
  const [equipe, setEquipe] = useState(denuncia.responsavel);
  const [observacao, setObservacao] = useState("");
  const [observacoes, setObservacoes] = useState([]);

  const [historico, setHistorico] = useState([
    {
      titulo: "Denúncia recebida",
      descricao: "Registro encaminhado pelos canais oficiais do EcoPonto.",
      data: "05 nov. 2026 · 09:42",
      concluido: true,
    },
    {
      titulo: "Triagem realizada",
      descricao: "Categoria e prioridade confirmadas pela central.",
      data: "05 nov. 2026 · 10:06",
      concluido: true,
    },
    {
      titulo: "Em análise",
      descricao: "Acompanhamento pela equipe responsável.",
      data: "Em andamento",
      concluido: status === "Em análise",
    },
    {
      titulo: "Resolução",
      descricao: "Conclusão do atendimento da ocorrência.",
      data: "Aguardando conclusão",
      concluido: status === "Resolvida",
    },
  ]);

  function atualizarStatus(novoStatus) {
    setStatus(novoStatus);
    onAtualizar(denuncia.id, {
      status: novoStatus,
      responsavel: equipe,
    });

    setHistorico((anterior) => [
      ...anterior,
      {
        titulo: `Status atualizado: ${novoStatus}`,
        descricao: "Atualização realizada pelo administrador.",
        data: new Date().toLocaleString("pt-BR"),
        concluido: true,
      },
    ]);
  }

  function assumirDenuncia() {
    const novaEquipe = "Equipe Centro";
    setEquipe(novaEquipe);
    onAtualizar(denuncia.id, {
      responsavel: novaEquipe,
      status: status === "Nova" ? "Em análise" : status,
    });

    if (status === "Nova") {
      setStatus("Em análise");
    }

    setHistorico((anterior) => [
      ...anterior,
      {
        titulo: "Equipe assumiu a denúncia",
        descricao: `${novaEquipe} ficou responsável pelo atendimento.`,
        data: new Date().toLocaleString("pt-BR"),
        concluido: true,
      },
    ]);
  }

  function atribuirEquipe(event) {
    const novaEquipe = event.target.value;
    setEquipe(novaEquipe);

    onAtualizar(denuncia.id, {
      responsavel: novaEquipe,
    });

    setHistorico((anterior) => [
      ...anterior,
      {
        titulo: "Equipe responsável atualizada",
        descricao: `Ocorrência atribuída à ${novaEquipe}.`,
        data: new Date().toLocaleString("pt-BR"),
        concluido: true,
      },
    ]);
  }

  function adicionarObservacao(event) {
    event.preventDefault();

    if (!observacao.trim()) return;

    setObservacoes((anterior) => [
      ...anterior,
      {
        texto: observacao,
        data: new Date().toLocaleString("pt-BR"),
      },
    ]);

    setObservacao("");
  }

  return (
    <section className="eco-detail-page">
      <style>{`
        .eco-detail-page {
          padding: 25px 28px 40px;
          color: #20352a;
          max-width: 1500px;
          margin: 0 auto;
        }

        .detail-back {
          border: 0;
          background: transparent;
          color: #16834b;
          font-weight: 600;
          cursor: pointer;
          padding: 5px 0 18px;
          font-size: 13px;
        }

        .detail-header {
          display: flex;
          justify-content: space-between;
          align-items: center;
          gap: 18px;
          flex-wrap: wrap;
          margin-bottom: 22px;
        }

        .detail-title-area h1 {
          font-size: 23px;
          margin: 0 0 10px;
          color: #173d2b;
        }

        .detail-subtitle {
          font-size: 12px;
          color: #839087;
          margin: 10px 0 0;
        }

        .detail-tags {
          display: flex;
          gap: 8px;
          flex-wrap: wrap;
          align-items: center;
        }

        .detail-tag {
          padding: 5px 10px;
          border-radius: 20px;
          font-size: 11px;
          font-weight: 700;
        }

        .detail-tag.nova { background: #edf4ff; color: #3973ae; }
        .detail-tag.em-analise { background: #fff6df; color: #a87816; }
        .detail-tag.resolvida { background: #e8f7ee; color: #24824c; }
        .detail-tag.arquivada { background: #f0f1f2; color: #69736d; }
        .detail-tag.alta { background: #fff0ed; color: #c34d39; }
        .detail-tag.media { background: #fff6df; color: #a87816; }
        .detail-tag.baixa { background: #eaf4ff; color: #3973ae; }

        .detail-actions {
          display: flex;
          gap: 9px;
          flex-wrap: wrap;
        }

        .detail-button {
          padding: 10px 14px;
          border-radius: 8px;
          border: 1px solid #dce8df;
          background: white;
          color: #16834b;
          font-weight: 600;
          cursor: pointer;
          font-size: 12px;
        }

        .detail-button.primary {
          background: #16834b;
          color: white;
          border-color: #16834b;
        }

        .detail-button.primary:hover {
          background: #11683b;
        }

        .detail-grid {
          display: grid;
          grid-template-columns: minmax(0, 1.8fr) minmax(250px, .8fr);
          gap: 14px;
          align-items: start;
        }

        .detail-main-column,
        .detail-side-column {
          display: flex;
          flex-direction: column;
          gap: 14px;
          min-width: 0;
        }

        .detail-card {
          background: white;
          border: 1px solid #e7eee9;
          border-radius: 12px;
          padding: 19px;
          min-width: 0;
        }

        .detail-card h2 {
          margin: 0 0 12px;
          color: #203b2b;
          font-size: 16px;
          display: flex;
          align-items: center;
          gap: 8px;
        }

        .detail-card p {
          color: #77847a;
          font-size: 12px;
          line-height: 1.7;
          margin: 0;
        }

        .detail-icon {
          color: #16834b;
          font-size: 15px;
        }

        .detail-timeline {
          display: flex;
          flex-direction: column;
          margin-top: 17px;
        }

        .detail-timeline-item {
          display: flex;
          gap: 13px;
          min-height: 63px;
          position: relative;
        }

        .detail-timeline-marker {
          width: 9px;
          height: 9px;
          border-radius: 50%;
          background: #cbd7ce;
          border: 2px solid #e6eee8;
          flex-shrink: 0;
          margin-top: 4px;
          z-index: 1;
        }

        .detail-timeline-marker.active {
          background: #16834b;
          border-color: #d5efdf;
        }

        .detail-timeline-item:not(:last-child)::after {
          content: "";
          position: absolute;
          top: 13px;
          left: 5px;
          width: 1px;
          height: calc(100% - 13px);
          background: #dce8df;
        }

        .detail-timeline-content {
          padding-bottom: 15px;
        }

        .detail-timeline-content strong {
          display: block;
          color: #405346;
          font-size: 12px;
          margin-bottom: 4px;
        }

        .detail-timeline-content p {
          font-size: 11px;
          margin-bottom: 4px;
        }

        .detail-timeline-content small {
          font-size: 10px;
          color: #9aa69e;
        }

        .detail-map {
          height: 112px;
          border-radius: 9px;
          margin-top: 12px;
          overflow: hidden;
          position: relative;
          background-color: #edf9f1;
          background-image:
            linear-gradient(170deg, transparent 42%, #fff 43%, #fff 47%, transparent 48%),
            linear-gradient(20deg, transparent 52%, #fff 53%, #fff 57%, transparent 58%),
            linear-gradient(90deg, transparent 40%, #d9eddf 41%, #d9eddf 43%, transparent 44%);
          border: 1px solid #e6f0e8;
        }

        .detail-map::after {
          content: "";
          position: absolute;
          width: 12px;
          height: 12px;
          border-radius: 50% 50% 50% 0;
          background: #e34f4f;
          border: 2px solid white;
          transform: rotate(-45deg);
          top: 45%;
          left: 57%;
          box-shadow: 0 2px 5px #0002;
        }

        .detail-location {
          color: #405346 !important;
          font-weight: 600;
        }

        .detail-team-box {
          background: #f0faf3;
          border: 1px solid #e3f1e7;
          border-radius: 9px;
          padding: 13px;
          margin-top: 12px;
        }

        .detail-team-box strong {
          display: block;
          color: #244532;
          font-size: 12px;
          margin-bottom: 5px;
        }

        .detail-team-box p {
          font-size: 11px;
        }

        .detail-team-select {
          width: 100%;
          margin-top: 12px;
          padding: 10px;
          border: 1px solid #dce8df;
          border-radius: 8px;
          background: white;
          color: #405346;
          font-size: 12px;
        }

        .detail-attachment {
          display: flex;
          align-items: center;
          gap: 10px;
          border: 1px solid #edf1ed;
          border-radius: 9px;
          padding: 11px;
          margin-top: 10px;
          background: #fff;
        }

        .detail-file-icon {
          width: 34px;
          height: 34px;
          border-radius: 8px;
          display: grid;
          place-items: center;
          color: #16834b;
          background: #eaf7ee;
          font-size: 16px;
        }

        .detail-attachment strong {
          display: block;
          font-size: 11px;
          color: #405346;
        }

        .detail-attachment small {
          display: block;
          color: #9aa69e;
          font-size: 10px;
          margin-top: 4px;
        }

        .detail-note-form {
          display: flex;
          gap: 9px;
          margin-top: 13px;
        }

        .detail-note-form input {
          flex: 1;
          min-width: 0;
          padding: 10px;
          border: 1px solid #dce8df;
          border-radius: 8px;
          outline-color: #16834b;
          font-size: 12px;
        }

        .detail-note-form button {
          border: 0;
          background: #16834b;
          color: white;
          border-radius: 8px;
          padding: 9px 12px;
          font-weight: 600;
          cursor: pointer;
          font-size: 11px;
        }

        .detail-note {
          border-top: 1px solid #edf1ed;
          margin-top: 12px;
          padding-top: 12px;
        }

        .detail-note strong {
          display: block;
          font-size: 11px;
          color: #405346;
          margin-bottom: 4px;
        }

        .detail-note small {
          color: #9aa69e;
          font-size: 10px;
        }

        .detail-note p {
          margin-top: 5px;
        }

        .detail-status-select {
          width: 100%;
          margin-top: 9px;
          padding: 10px;
          border: 1px solid #dce8df;
          border-radius: 8px;
          background: white;
          color: #405346;
          font-size: 12px;
        }

        @media (max-width: 900px) {
          .detail-grid {
            grid-template-columns: 1fr;
          }
          .detail-side-column {
            display: grid;
            grid-template-columns: repeat(2, minmax(0, 1fr));
          }
        }

        @media (max-width: 560px) {
          .eco-detail-page {
            padding: 18px 13px 30px;
          }
          .detail-side-column {
            display: flex;
          }
          .detail-header {
            align-items: flex-start;
          }
          .detail-title-area h1 {
            font-size: 19px;
          }
          .detail-card {
            padding: 15px;
          }
        }
      `}</style>

      <button className="detail-back" onClick={onVoltar}>
        ← Voltar para lista de denúncias
      </button>

      <header className="detail-header">
        <div className="detail-title-area">
          <h1>{denuncia.id}</h1>

          <div className="detail-tags">
            <span className={`detail-tag ${status.toLowerCase().replace(" ", "-")}`}>
              {status}
            </span>
            <span className={`detail-tag ${denuncia.prioridade.toLowerCase()}`}>
              {denuncia.prioridade} prioridade
            </span>
          </div>

          <p className="detail-subtitle">
            {denuncia.categoria} · Recebida em {denuncia.data}
          </p>
        </div>

        <div className="detail-actions">
          <button className="detail-button" onClick={assumirDenuncia}>
            Atribuir equipe
          </button>

          <button
            className="detail-button primary"
            onClick={() =>
              atualizarStatus(
                status === "Resolvida" ? "Em análise" : "Resolvida"
              )
            }
          >
            {status === "Resolvida" ? "Reabrir denúncia" : "Atualizar status"}
          </button>
        </div>
      </header>

      <div className="detail-grid">
        <div className="detail-main-column">
          <article className="detail-card">
            <h2>Descrição da denúncia</h2>
            <p>{denuncia.descricao}</p>
          </article>

          <article className="detail-card">
            <h2><Icon>◷</Icon> Histórico de atualizações</h2>

            <div className="detail-timeline">
              {historico.map((item, index) => (
                <div className="detail-timeline-item" key={index}>
                  <div
                    className={`detail-timeline-marker ${
                      item.concluido ? "active" : ""
                    }`}
                  />

                  <div className="detail-timeline-content">
                    <strong>{item.titulo}</strong>
                    <p>{item.descricao}</p>
                    <small>{item.data}</small>
                  </div>
                </div>
              ))}
            </div>
          </article>

          <article className="detail-card">
            <h2><Icon>✎</Icon> Observações internas</h2>

            {observacoes.length === 0 && (
              <p>Ainda não há observações operacionais registradas.</p>
            )}

            {observacoes.map((item, index) => (
              <div className="detail-note" key={index}>
                <strong>Administrador</strong>
                <small>{item.data}</small>
                <p>{item.texto}</p>
              </div>
            ))}

            <form className="detail-note-form" onSubmit={adicionarObservacao}>
              <input
                value={observacao}
                onChange={(event) => setObservacao(event.target.value)}
                placeholder="Escreva uma observação..."
              />
              <button type="submit">Adicionar</button>
            </form>
          </article>
        </div>

        <aside className="detail-side-column">
          <article className="detail-card">
            <h2><Icon>⌖</Icon> Localização</h2>
            <p className="detail-location">{denuncia.local}</p>
            <p>Chã do Pilar · Pilar, AL</p>
            <div className="detail-map" />
            <p style={{ marginTop: 9, fontSize: 10 }}>
              Localização ilustrativa. Coordenadas reais ainda não cadastradas.
            </p>
          </article>

          <article className="detail-card">
            <h2><Icon>♙</Icon> Equipe responsável</h2>

            <div className="detail-team-box">
              <strong>{equipe}</strong>
              <p>Equipe habilitada para atendimento desta ocorrência.</p>
            </div>

            <label
              style={{
                display: "block",
                marginTop: 13,
                color: "#16834b",
                fontSize: 11,
                fontWeight: 700,
              }}
            >
              Atribuir equipe
            </label>

            <select
              className="detail-team-select"
              value={equipe}
              onChange={atribuirEquipe}
            >
              <option>Não atribuído</option>
              {EQUIPES.map((item) => (
                <option key={item}>{item}</option>
              ))}
            </select>
          </article>

          <article className="detail-card">
            <h2><Icon>♧</Icon> Anexos</h2>

            <div className="detail-attachment">
              <div className="detail-file-icon">▧</div>
              <div>
                <strong>registro-local.jpg</strong>
                <small>Anexo ilustrativo · 1,2 MB</small>
              </div>
            </div>

            <p style={{ marginTop: 10, fontSize: 10 }}>
              Nenhum arquivo real foi enviado para esta ocorrência.
            </p>
          </article>

          <article className="detail-card">
            <h2><Icon>✓</Icon> Situação atual</h2>

            <p>Atualize a situação do atendimento:</p>

            <select
              className="detail-status-select"
              value={status}
              onChange={(event) => atualizarStatus(event.target.value)}
            >
              <option>Nova</option>
              <option>Em análise</option>
              <option>Resolvida</option>
              <option>Arquivada</option>
            </select>
          </article>
        </aside>
      </div>
    </section>
  );
}

function ListPage() {
  const [denuncias, setDenuncias] = useState(DENUNCIAS_INICIAIS);
  const [pesquisa, setPesquisa] = useState("");
  const [filtroStatus, setFiltroStatus] = useState("Todos");
  const [filtroPrioridade, setFiltroPrioridade] = useState("Todas");
  const [selecionada, setSelecionada] = useState(null);
  const [modalNova, setModalNova] = useState(false);

  const [novaDenuncia, setNovaDenuncia] = useState({
    local: "",
    categoria: "Descarte irregular",
    prioridade: "Média",
    descricao: "",
  });

  const denunciasFiltradas = useMemo(() => {
    return denuncias.filter((item) => {
      const termo = pesquisa.toLowerCase();

      const correspondePesquisa =
        `${item.id} ${item.local} ${item.categoria} ${item.responsavel}`
          .toLowerCase()
          .includes(termo);

      const correspondeStatus =
        filtroStatus === "Todos" || item.status === filtroStatus;

      const correspondePrioridade =
        filtroPrioridade === "Todas" ||
        item.prioridade === filtroPrioridade;

      return (
        correspondePesquisa &&
        correspondeStatus &&
        correspondePrioridade
      );
    });
  }, [denuncias, pesquisa, filtroStatus, filtroPrioridade]);

  function cadastrarDenuncia(event) {
    event.preventDefault();

    const nova = {
      id: `DEN-2026-${String(denuncias.length + 1).padStart(3, "0")}`,
      local: novaDenuncia.local,
      categoria: novaDenuncia.categoria,
      prioridade: novaDenuncia.prioridade,
      descricao: novaDenuncia.descricao,
      data: new Date().toLocaleDateString("pt-BR"),
      status: "Nova",
      responsavel: "Não atribuído",
    };

    setDenuncias((anterior) => [nova, ...anterior]);

    setNovaDenuncia({
      local: "",
      categoria: "Descarte irregular",
      prioridade: "Média",
      descricao: "",
    });

    setModalNova(false);
  }

  function atualizarDenuncia(id, alteracoes) {
    setDenuncias((anterior) =>
      anterior.map((item) =>
        item.id === id ? { ...item, ...alteracoes } : item
      )
    );

    setSelecionada((anterior) =>
      anterior?.id === id ? { ...anterior, ...alteracoes } : anterior
    );
  }

  if (selecionada) {
    return (
      <DetalhesDenuncia
        denuncia={selecionada}
        onVoltar={() => setSelecionada(null)}
        onAtualizar={atualizarDenuncia}
      />
    );
  }

  return (
    <section className="eco-list-page">
      <style>{`
        .eco-list-page {
          padding: 28px;
          color: #20352a;
        }

        .eco-list-heading {
          display: flex;
          justify-content: space-between;
          align-items: center;
          gap: 16px;
          margin-bottom: 25px;
          flex-wrap: wrap;
        }

        .eco-list-heading h1 {
          margin: 0 0 7px;
          font-size: 25px;
          color: #173d2b;
        }

        .eco-list-heading p {
          margin: 0;
          color: #78877d;
          font-size: 14px;
        }

        .eco-list-primary {
          border: 0;
          border-radius: 9px;
          padding: 12px 18px;
          background: #16834b;
          color: white;
          font-weight: 600;
          cursor: pointer;
        }

        .eco-list-primary:hover { background: #11683b; }

        .eco-list-summary {
          display: grid;
          grid-template-columns: repeat(4, minmax(0, 1fr));
          gap: 15px;
          margin-bottom: 22px;
        }

        .eco-list-summary article {
          background: white;
          border: 1px solid #e8eee9;
          border-radius: 12px;
          padding: 18px;
        }

        .eco-list-summary span {
          display: block;
          color: #78877d;
          font-size: 13px;
          margin-bottom: 10px;
        }

        .eco-list-summary strong {
          font-size: 25px;
          color: #183e2c;
        }

        .eco-list-filters {
          display: flex;
          flex-wrap: wrap;
          gap: 12px;
          padding: 18px;
          background: white;
          border: 1px solid #e8eee9;
          border-radius: 12px;
          margin-bottom: 18px;
        }

        .eco-list-filters input,
        .eco-list-filters select {
          min-height: 42px;
          border: 1px solid #dfe7e1;
          border-radius: 8px;
          padding: 0 12px;
          background: #fff;
          color: #304438;
          font-size: 13px;
        }

        .eco-list-search {
          flex: 1;
          min-width: 200px;
        }

        .eco-list-table-card {
          background: white;
          border: 1px solid #e8eee9;
          border-radius: 12px;
          overflow: hidden;
        }

        .eco-list-table-title {
          padding: 19px 20px;
          border-bottom: 1px solid #edf1ed;
          display: flex;
          justify-content: space-between;
          align-items: center;
          gap: 10px;
        }

        .eco-list-table-title h2 {
          font-size: 16px;
          margin: 0;
          color: #214330;
        }

        .eco-list-table-title span {
          font-size: 12px;
          color: #829087;
        }

        .eco-list-table-wrap {
          width: 100%;
          overflow-x: auto;
        }

        .eco-list-table {
          width: 100%;
          min-width: 0;
          table-layout: fixed;
          border-collapse: collapse;
          font-size: 12px;
        }

        .eco-list-table th {
          text-align: left;
          padding: 14px 10px;
          background: #f7faf7;
          color: #718075;
          font-size: 10px;
          letter-spacing: .25px;
          overflow-wrap: anywhere;
        }

        .eco-list-table td {
          padding: 14px 10px;
          border-top: 1px solid #edf1ed;
          color: #526257;
          overflow-wrap: anywhere;
          word-break: normal;
          vertical-align: middle;
        }

        .eco-list-table th:first-child,
        .eco-list-table td:first-child {
          width: 16%;
        }

        .eco-list-table th:nth-child(2),
        .eco-list-table td:nth-child(2) {
          width: 17%;
        }

        .eco-list-table th:nth-child(3),
        .eco-list-table td:nth-child(3) {
          width: 15%;
        }

        .eco-list-table th:nth-child(4),
        .eco-list-table td:nth-child(4) {
          width: 10%;
        }

        .eco-list-table th:nth-child(5),
        .eco-list-table td:nth-child(5) {
          width: 12%;
        }

        .eco-list-table th:nth-child(6),
        .eco-list-table td:nth-child(6) {
          width: 14%;
        }

        .eco-list-table th:last-child,
        .eco-list-table td:last-child {
          width: 16%;
          text-align: center;
        }

        .eco-list-id {
          overflow-wrap: anywhere;
        }

        .eco-list-action {
          white-space: nowrap;
          padding: 7px 9px;
        }

        @media (max-width: 1100px) {
          .eco-list-table {
            font-size: 11px;
          }

          .eco-list-table th,
          .eco-list-table td {
            padding: 12px 7px;
          }

          .eco-list-tag {
            padding: 5px 7px;
            font-size: 10px;
          }

          .eco-list-action {
            padding: 6px 7px;
            font-size: 11px;
          }
        }

        @media (max-width: 700px) {
          .eco-list-table-wrap {
            overflow-x: auto;
          }

          .eco-list-table {
            min-width: 760px;
            table-layout: auto;
          }
        }

        .eco-list-table tbody tr:hover { background: #f9fcf9; }

        .eco-list-id {
          color: #16834b;
          font-weight: 700;
        }

        .eco-list-tag {
          display: inline-block;
          padding: 5px 9px;
          border-radius: 20px;
          font-size: 11px;
          font-weight: 600;
          white-space: nowrap;
        }

        .eco-priority-alta { background: #fff0ed; color: #c34d39; }
        .eco-priority-média { background: #fff6df; color: #a87816; }
        .eco-priority-baixa { background: #eaf4ff; color: #3973ae; }

        .eco-status-nova { background: #edf4ff; color: #3973ae; }
        .eco-status-em-análise { background: #fff6df; color: #a87816; }
        .eco-status-resolvida { background: #e8f7ee; color: #24824c; }
        .eco-status-arquivada { background: #f0f1f2; color: #69736d; }

        .eco-list-action {
          border: 1px solid #dce8df;
          color: #16834b;
          background: white;
          padding: 7px 11px;
          border-radius: 7px;
          cursor: pointer;
          font-weight: 600;
          font-size: 12px;
        }

        .eco-list-empty {
          padding: 35px !important;
          text-align: center;
          color: #849087 !important;
        }

        .eco-list-modal-bg {
          position: fixed;
          inset: 0;
          z-index: 1000;
          background: rgba(15, 35, 23, .48);
          display: flex;
          align-items: center;
          justify-content: center;
          padding: 18px;
        }

        .eco-list-modal {
          width: 100%;
          max-width: 520px;
          max-height: 90vh;
          overflow-y: auto;
          background: white;
          border-radius: 14px;
          padding: 24px;
          box-shadow: 0 18px 55px rgba(0,0,0,.18);
        }

        .eco-list-modal h2 {
          margin: 0 0 20px;
          color: #183e2c;
          font-size: 20px;
        }

        .eco-list-modal label {
          display: block;
          margin: 13px 0 6px;
          color: #526257;
          font-size: 13px;
          font-weight: 600;
        }

        .eco-list-modal input,
        .eco-list-modal select,
        .eco-list-modal textarea {
          width: 100%;
          box-sizing: border-box;
          border: 1px solid #dfe7e1;
          border-radius: 8px;
          padding: 11px;
          font: inherit;
          color: #304438;
          background: white;
        }

        .eco-list-modal textarea {
          min-height: 90px;
          resize: vertical;
        }

        .eco-list-modal-actions {
          display: flex;
          justify-content: flex-end;
          gap: 10px;
          margin-top: 22px;
        }

        .eco-list-secondary {
          border: 1px solid #dfe7e1;
          border-radius: 8px;
          padding: 10px 15px;
          background: white;
          cursor: pointer;
          color: #526257;
        }

        @media (max-width: 850px) {
          .eco-list-summary { grid-template-columns: repeat(2, 1fr); }
          .eco-list-page { padding: 20px 14px; }
        }

        @media (max-width: 480px) {
          .eco-list-summary { grid-template-columns: 1fr 1fr; gap: 9px; }
          .eco-list-summary article { padding: 13px; }
          .eco-list-summary strong { font-size: 21px; }
        }
      `}</style>

      <div className="eco-list-heading">
        <div>
          <h1>Lista de denúncias</h1>
          <p>Consulte, filtre e acompanhe as ocorrências ambientais.</p>
        </div>

        <button
          className="eco-list-primary"
          onClick={() => setModalNova(true)}
        >
          + Nova denúncia
        </button>
      </div>

      <div className="eco-list-summary">
        <article>
          <span>Total de denúncias</span>
          <strong>{denuncias.length}</strong>
        </article>

        <article>
          <span>Novas</span>
          <strong>{denuncias.filter((d) => d.status === "Nova").length}</strong>
        </article>

        <article>
          <span>Em análise</span>
          <strong>{denuncias.filter((d) => d.status === "Em análise").length}</strong>
        </article>

        <article>
          <span>Resolvidas</span>
          <strong>{denuncias.filter((d) => d.status === "Resolvida").length}</strong>
        </article>
      </div>

      <div className="eco-list-filters">
        <input
          className="eco-list-search"
          placeholder="Buscar por código, local ou categoria..."
          value={pesquisa}
          onChange={(event) => setPesquisa(event.target.value)}
        />

        <select
          value={filtroStatus}
          onChange={(event) => setFiltroStatus(event.target.value)}
        >
          <option>Todos</option>
          <option>Nova</option>
          <option>Em análise</option>
          <option>Resolvida</option>
          <option>Arquivada</option>
        </select>

        <select
          value={filtroPrioridade}
          onChange={(event) => setFiltroPrioridade(event.target.value)}
        >
          <option>Todas</option>
          <option>Alta</option>
          <option>Média</option>
          <option>Baixa</option>
        </select>
      </div>

      <div className="eco-list-table-card">
        <div className="eco-list-table-title">
          <h2>Ocorrências registradas</h2>
          <span>{denunciasFiltradas.length} resultado(s)</span>
        </div>

        <div className="eco-list-table-wrap">
          <table className="eco-list-table">
            <colgroup>
              <col style={{ width: "16%" }} />
              <col style={{ width: "17%" }} />
              <col style={{ width: "15%" }} />
              <col style={{ width: "10%" }} />
              <col style={{ width: "12%" }} />
              <col style={{ width: "14%" }} />
              <col style={{ width: "16%" }} />
            </colgroup>
            <thead>
              <tr>
                <th>IDENTIFICAÇÃO</th>
                <th>LOCAL</th>
                <th>CATEGORIA</th>
                <th>DATA</th>
                <th>PRIORIDADE</th>
                <th>STATUS</th>
                <th>AÇÃO</th>
              </tr>
            </thead>

            <tbody>
              {denunciasFiltradas.map((item) => (
                <tr key={item.id}>
                  <td><span className="eco-list-id">{item.id}</span></td>
                  <td>{item.local}</td>
                  <td>{item.categoria}</td>
                  <td>{item.data}</td>
                  <td>
                    <span className={`eco-list-tag eco-priority-${item.prioridade.toLowerCase()}`}>
                      {item.prioridade}
                    </span>
                  </td>
                  <td>
                    <span className={`eco-list-tag eco-status-${item.status.toLowerCase().replace(" ", "-")}`}>
                      {item.status}
                    </span>
                  </td>
                  <td>
                    <button
                      className="eco-list-action"
                      onClick={() => setSelecionada(item)}
                    >
                      Detalhes
                    </button>
                  </td>
                </tr>
              ))}

              {denunciasFiltradas.length === 0 && (
                <tr>
                  <td colSpan="7" className="eco-list-empty">
                    Nenhuma denúncia encontrada com esses filtros.
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      </div>

      {modalNova && (
        <div
          className="eco-list-modal-bg"
          onClick={() => setModalNova(false)}
        >
          <form
            className="eco-list-modal"
            onSubmit={cadastrarDenuncia}
            onClick={(event) => event.stopPropagation()}
          >
            <h2>Registrar nova denúncia</h2>

            <label>Local da ocorrência</label>
            <input
              required
              value={novaDenuncia.local}
              onChange={(event) =>
                setNovaDenuncia({
                  ...novaDenuncia,
                  local: event.target.value,
                })
              }
              placeholder="Ex.: Centro — Rua Principal"
            />

            <label>Categoria</label>
            <select
              value={novaDenuncia.categoria}
              onChange={(event) =>
                setNovaDenuncia({
                  ...novaDenuncia,
                  categoria: event.target.value,
                })
              }
            >
              <option>Descarte irregular</option>
              <option>Acúmulo de lixo</option>
              <option>Entulho</option>
              <option>Queimada</option>
              <option>Poluição</option>
              <option>Outro</option>
            </select>

            <label>Prioridade</label>
            <select
              value={novaDenuncia.prioridade}
              onChange={(event) =>
                setNovaDenuncia({
                  ...novaDenuncia,
                  prioridade: event.target.value,
                })
              }
            >
              <option>Alta</option>
              <option>Média</option>
              <option>Baixa</option>
            </select>

            <label>Descrição</label>
            <textarea
              required
              value={novaDenuncia.descricao}
              onChange={(event) =>
                setNovaDenuncia({
                  ...novaDenuncia,
                  descricao: event.target.value,
                })
              }
              placeholder="Descreva a ocorrência..."
            />

            <div className="eco-list-modal-actions">
              <button
                type="button"
                className="eco-list-secondary"
                onClick={() => setModalNova(false)}
              >
                Cancelar
              </button>

              <button type="submit" className="eco-list-primary">
                Cadastrar denúncia
              </button>
            </div>
          </form>
        </div>
      )}
    </section>
  );
}

export default ListPage;

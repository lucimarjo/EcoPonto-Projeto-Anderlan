
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
    descricao: "Acúmulo de resíduos em espaço público.",
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

  function atualizarStatus(id, status) {
    setDenuncias((anterior) =>
      anterior.map((item) =>
        item.id === id ? { ...item, status } : item
      )
    );

    setSelecionada((anterior) =>
      anterior?.id === id ? { ...anterior, status } : anterior
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
        .eco-list-primary:hover {
          background: #11683b;
        }
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
          border-collapse: collapse;
          min-width: 850px;
          font-size: 13px;
        }
        .eco-list-table th {
          text-align: left;
          padding: 14px 16px;
          background: #f7faf7;
          color: #718075;
          font-size: 11px;
          letter-spacing: .4px;
        }
        .eco-list-table td {
          padding: 16px;
          border-top: 1px solid #edf1ed;
          color: #526257;
        }
        .eco-list-table tbody tr:hover {
          background: #f9fcf9;
        }
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
        .eco-priority-media { background: #fff6df; color: #a87816; }
        .eco-priority-baixa { background: #eaf4ff; color: #3973ae; }
        .eco-status-nova { background: #edf4ff; color: #3973ae; }
        .eco-status-em-analise { background: #fff6df; color: #a87816; }
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
        .eco-list-detail p {
          color: #5d6c61;
          font-size: 14px;
          line-height: 1.6;
        }
        .eco-list-detail strong {
          color: #244532;
        }
        .eco-list-detail select {
          margin-top: 8px;
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
        <div className="eco-list-modal-bg" onClick={() => setModalNova(false)}>
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
                setNovaDenuncia({ ...novaDenuncia, local: event.target.value })
              }
              placeholder="Ex.: Centro — Rua Principal"
            />

            <label>Categoria</label>
            <select
              value={novaDenuncia.categoria}
              onChange={(event) =>
                setNovaDenuncia({ ...novaDenuncia, categoria: event.target.value })
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
                setNovaDenuncia({ ...novaDenuncia, prioridade: event.target.value })
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
                setNovaDenuncia({ ...novaDenuncia, descricao: event.target.value })
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

      {selecionada && (
        <div className="eco-list-modal-bg" onClick={() => setSelecionada(null)}>
          <div
            className="eco-list-modal eco-list-detail"
            onClick={(event) => event.stopPropagation()}
          >
            <h2>Detalhes da denúncia</h2>
            <p><strong>Identificação:</strong> {selecionada.id}</p>
            <p><strong>Local:</strong> {selecionada.local}</p>
            <p><strong>Categoria:</strong> {selecionada.categoria}</p>
            <p><strong>Data:</strong> {selecionada.data}</p>
            <p><strong>Prioridade:</strong> {selecionada.prioridade}</p>
            <p><strong>Responsável:</strong> {selecionada.responsavel}</p>
            <p><strong>Descrição:</strong> {selecionada.descricao}</p>

            <label>Atualizar status</label>
            <select
              value={selecionada.status}
              onChange={(event) =>
                atualizarStatus(selecionada.id, event.target.value)
              }
            >
              <option>Nova</option>
              <option>Em análise</option>
              <option>Resolvida</option>
              <option>Arquivada</option>
            </select>

            <div className="eco-list-modal-actions">
              <button
                className="eco-list-primary"
                onClick={() => setSelecionada(null)}
              >
                Fechar
              </button>
            </div>
          </div>
        </div>
      )}
    </section>
  );
}

export default ListPage;

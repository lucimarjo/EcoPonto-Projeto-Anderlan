import { useMemo, useState } from "react";

const equipes = [
  {
    id: 1,
    nome: "Limpeza Urbana",
    especialidade: "Zeladoria e vias públicas",
    status: "Disponível",
    membros: 8,
    abertas: 12,
    resolucao: "92%",
    pessoas: [
      { iniciais: "AR", nome: "Ana Reis", funcao: "Coordenação", cor: "green" },
      { iniciais: "JL", nome: "João Lima", funcao: "Agente de campo", cor: "lime" },
      { iniciais: "MS", nome: "Marina Souza", funcao: "Agente de campo", cor: "yellow" },
    ],
    atividade: "Vistoria concluída no Alto do Cruzeiro.",
    tempo: "Há 42 minutos",
  },
  {
    id: 2,
    nome: "Coleta Seletiva",
    especialidade: "Recicláveis e triagem",
    status: "Em campo",
    membros: 6,
    abertas: 9,
    resolucao: "88%",
    pessoas: [
      { iniciais: "PC", nome: "Paulo Costa", funcao: "Coordenação", cor: "green" },
      { iniciais: "LC", nome: "Luiza Carvalho", funcao: "Agente de coleta", cor: "lime" },
      { iniciais: "RF", nome: "Rafael Freitas", funcao: "Agente de coleta", cor: "yellow" },
    ],
    atividade: "Coleta seletiva em andamento no Centro.",
    tempo: "Há 18 minutos",
  },
  {
    id: 3,
    nome: "Resíduos Especiais",
    especialidade: "Materiais de descarte controlado",
    status: "Disponível",
    membros: 5,
    abertas: 6,
    resolucao: "94%",
    pessoas: [
      { iniciais: "CM", nome: "Carlos Melo", funcao: "Coordenação", cor: "green" },
      { iniciais: "BA", nome: "Bianca Alves", funcao: "Agente de campo", cor: "lime" },
      { iniciais: "TS", nome: "Tiago Santos", funcao: "Agente de campo", cor: "yellow" },
    ],
    atividade: "Equipe disponível para novas ocorrências.",
    tempo: "Há 1 hora",
  },
  {
    id: 4,
    nome: "Fiscalização Ambiental",
    especialidade: "Vistorias e descarte irregular",
    status: "Em campo",
    membros: 7,
    abertas: 14,
    resolucao: "89%",
    pessoas: [
      { iniciais: "FA", nome: "Fernanda Araújo", funcao: "Coordenação", cor: "green" },
      { iniciais: "GM", nome: "Gabriel Martins", funcao: "Fiscal", cor: "lime" },
      { iniciais: "LP", nome: "Laura Pereira", funcao: "Fiscal", cor: "yellow" },
    ],
    atividade: "Vistoria de descarte irregular em andamento.",
    tempo: "Há 2 horas",
  },
];

function TeamsPage() {
  const [pesquisa, setPesquisa] = useState("");
  const [disponibilidade, setDisponibilidade] = useState("Todas");
  const [equipeAtiva, setEquipeAtiva] = useState(equipes[0]);
  const [modalAberto, setModalAberto] = useState(false);
  const [avisoAtribuicao, setAvisoAtribuicao] = useState(false);

  const equipesFiltradas = useMemo(
    () =>
      equipes.filter((equipe) => {
        const termo = pesquisa.toLowerCase();

        const combinaPesquisa = `${equipe.nome} ${equipe.especialidade}`
          .toLowerCase()
          .includes(termo);

        const combinaStatus =
          disponibilidade === "Todas" ||
          equipe.status === disponibilidade;

        return combinaPesquisa && combinaStatus;
      }),
    [pesquisa, disponibilidade]
  );

  return (
    <div className="eco-content eco-teams-page">
      <section className="eco-teams-heading">
        <div>
          <h1>Gestão de equipes</h1>
          <p>
            Acompanhe disponibilidade, carga e desempenho das equipes.
          </p>
        </div>

        <button
          className="eco-team-primary"
          onClick={() => setModalAberto(true)}
        >
          <span>＋</span> Nova equipe
        </button>
      </section>

      <section
        className="eco-teams-toolbar"
        aria-label="Pesquisa e filtros de equipes"
      >
        <label className="eco-team-search">
          <span>⌕</span>

          <input
            type="search"
            placeholder="Buscar equipe ou especialidade"
            value={pesquisa}
            onChange={(event) => setPesquisa(event.target.value)}
          />
        </label>

        <label className="eco-team-filter">
          <span className="eco-team-filter-label">
            Disponibilidade:
          </span>

          <select
            value={disponibilidade}
            onChange={(event) =>
              setDisponibilidade(event.target.value)
            }
          >
            <option value="Todas">Todas</option>
            <option value="Disponível">Disponíveis</option>
            <option value="Em campo">Em campo</option>
          </select>
        </label>
      </section>

      <section className="eco-teams-layout">
        <div className="eco-team-grid">
          {equipesFiltradas.map((equipe) => (
            <button
              type="button"
              key={equipe.id}
              className={`eco-team-card ${
                equipeAtiva.id === equipe.id ? "selected" : ""
              }`}
              onClick={() => {
                setEquipeAtiva(equipe);
                setAvisoAtribuicao(false);
              }}
              aria-pressed={equipeAtiva.id === equipe.id}
            >
              <div className="eco-team-card-top">
                <div>
                  <h2>{equipe.nome}</h2>
                  <p>{equipe.especialidade}</p>
                </div>

                <span
                  className={`eco-team-status ${
                    equipe.status === "Disponível"
                      ? "available"
                      : "working"
                  }`}
                >
                  {equipe.status}
                </span>
              </div>

              <div className="eco-team-metrics">
                <div>
                  <strong>{equipe.membros}</strong>
                  <span>membros</span>
                </div>

                <div>
                  <strong>{equipe.abertas}</strong>
                  <span>abertas</span>
                </div>

                <div>
                  <strong>{equipe.resolucao}</strong>
                  <span>resolução</span>
                </div>
              </div>
            </button>
          ))}

          {equipesFiltradas.length === 0 && (
            <div className="eco-team-empty">
              Nenhuma equipe encontrada com esses filtros.
            </div>
          )}
        </div>

        <aside className="eco-team-details">
          <div className="eco-team-detail-title">
            <h2>{equipeAtiva.nome}</h2>
            <p>{equipeAtiva.especialidade}</p>
          </div>

          <div className="eco-team-detail-section">
            <h3>MEMBROS</h3>

            <div className="eco-team-members">
              {equipeAtiva.pessoas.map((pessoa) => (
                <div
                  className="eco-team-member"
                  key={pessoa.iniciais}
                >
                  <span
                    className={`eco-team-avatar ${pessoa.cor}`}
                  >
                    {pessoa.iniciais}
                  </span>

                  <span>
                    <strong>{pessoa.nome}</strong>
                    <small>{pessoa.funcao}</small>
                  </span>
                </div>
              ))}
            </div>
          </div>

          <div className="eco-team-last-activity">
            <h3>ÚLTIMA ATIVIDADE</h3>
            <p>{equipeAtiva.atividade}</p>
            <span>{equipeAtiva.tempo}</span>
          </div>

          <button
            className="eco-team-assign"
            onClick={() => setAvisoAtribuicao(true)}
          >
            Atribuir denúncia
          </button>

          {avisoAtribuicao && (
            <p className="eco-team-feedback">
              Demonstração visual: atribuição disponível na integração futura.
            </p>
          )}
        </aside>
      </section>

      {modalAberto && (
        <div
          className="eco-team-modal-backdrop"
          role="presentation"
          onMouseDown={(event) => {
            if (event.target === event.currentTarget) {
              setModalAberto(false);
            }
          }}
        >
          <section
            className="eco-team-modal"
            role="dialog"
            aria-modal="true"
            aria-labelledby="eco-team-modal-title"
          >
            <button
              className="eco-team-modal-close"
              onClick={() => setModalAberto(false)}
              aria-label="Fechar"
            >
              ×
            </button>

            <span className="eco-eyebrow">
              GESTÃO DE EQUIPES
            </span>

            <h2 id="eco-team-modal-title">Nova equipe</h2>

            <p>
              Prévia visual do formulário de cadastro de equipe.
            </p>

            <label>
              Nome da equipe
              <input placeholder="Ex.: Limpeza Urbana" />
            </label>

            <label>
              Especialidade
              <input placeholder="Ex.: Zeladoria e vias públicas" />
            </label>

            <label>
              Disponibilidade
              <select defaultValue="Disponível">
                <option>Disponível</option>
                <option>Em campo</option>
              </select>
            </label>

            <div className="eco-team-modal-actions">
              <button
                className="eco-team-cancel"
                onClick={() => setModalAberto(false)}
              >
                Cancelar
              </button>

              <button
                className="eco-team-primary"
                onClick={() => setModalAberto(false)}
              >
                Concluir demonstração
              </button>
            </div>

            <small>
              Os dados não serão salvos. Esta é somente uma demonstração da interface.
            </small>
          </section>
        </div>
      )}
    </div>
  );
}

export default TeamsPage;
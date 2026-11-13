
import logoEcoPonto from "../assets/logo-ecoponto.png";

const menuItems = [
  { nome: "Dashboard", icon: "▦" },
  { nome: "Mapa de denúncias", icon: "⌖" },
  { nome: "Lista de denúncias", icon: "☷" },
  { nome: "Gestão de equipes", icon: "♙" },
];

function Sidebar({ paginaAtiva, setPaginaAtiva, menuAberto, setMenuAberto }) {
  return (
    <aside className={`eco-sidebar ${menuAberto ? "eco-sidebar-open" : ""}`}>
      <div className="eco-brand">
        <img src={logoEcoPonto} alt="Logo EcoPonto" />

        <div>
          <strong>EcoPonto</strong>
          <span>Central de gestão</span>
        </div>
      </div>

      <div className="eco-menu-label">MENU PRINCIPAL</div>

      <nav className="eco-navigation">
        {menuItems.map((item) => (
          <button
            key={item.nome}
            className={`eco-nav-item ${
              paginaAtiva === item.nome ? "active" : ""
            }`}
            onClick={() => {
              setPaginaAtiva(item.nome);
              setMenuAberto(false);
            }}
          >
            <span className="eco-icon">{item.icon}</span>
            <span>{item.nome}</span>

            {paginaAtiva === item.nome && (
              <span className="eco-nav-indicator" />
            )}
          </button>
        ))}
      </nav>

      <div className="eco-sidebar-bottom">
        <button
          className={`eco-nav-item ${
            paginaAtiva === "Configurações" ? "active" : ""
          }`}
          onClick={() => setPaginaAtiva("Configurações")}
        >
          <span className="eco-icon">⚙</span>
          <span>Configurações</span>
        </button>

        <div className="eco-sidebar-profile">
          <div className="eco-avatar">AD</div>

          <div className="eco-profile-info">
            <strong>Administrador</strong>
            <span>Gestor do sistema</span>
          </div>

          <span className="eco-profile-dots">•••</span>
        </div>
      </div>
    </aside>
  );
}

export default Sidebar;


function Icon({ name }) {
  const icons = {
    search: "⌕",
    bell: "♧",
    menu: "☰",
  };

  return (
    <span className="eco-icon" aria-hidden="true">
      {icons[name] || "•"}
    </span>
  );
}

function Topbar({
  paginaAtiva,
  pesquisa,
  setPesquisa,
  menuAberto,
  setMenuAberto,
}) {
  return (
    <header className="eco-topbar">
      <button
        className="eco-mobile-menu"
        onClick={() => setMenuAberto(!menuAberto)}
        aria-label="Abrir menu"
      >
        <Icon name="menu" />
      </button>

      <div className="eco-breadcrumb">
        <span>EcoPonto</span>
        <span>/</span>
        <strong>{paginaAtiva}</strong>
      </div>

      <div className="eco-topbar-actions">
        <label className="eco-search">
          <Icon name="search" />
          <input
            type="search"
            placeholder="Pesquisar..."
            value={pesquisa}
            onChange={(event) => setPesquisa(event.target.value)}
          />
          <kbd>⌘ K</kbd>
        </label>

        <button
          className="eco-notification"
          aria-label="Notificações"
        >
          <Icon name="bell" />
          <span />
        </button>

        <div className="eco-top-profile">
          <div className="eco-avatar">AD</div>
          <div>
            <strong>Administrador</strong>
            <span>Admin</span>
          </div>
        </div>
      </div>
    </header>
  );
}

export default Topbar;

import "../App.css";
import logoEcoPonto from "../assets/logo-ecoponto.png";

function Home() {
  return (
    <div className="home">

      {/* CABEÇALHO */}
      <header className="header">
        <div className="header-container">

          <a href="/" className="logo">
            <img src={logoEcoPonto} alt="Logo EcoPonto" />
            <span>EcoPonto</span>
          </a>

          <nav className="menu">
            <a href="/" className="menu-active">
              Início
            </a>

            <a href="/cadastro">
              Cadastro
            </a>

            <a href="/denuncia">
              Denúncias
            </a>

            <a href="/conta">
              Minha conta
            </a>
          </nav>

          <a href="/cadastro" className="header-button">
            Criar conta
          </a>

        </div>
      </header>


      {/* HERO */}
      <main>

        <section className="hero">
          <div className="hero-container">

            <div className="hero-content">

              <span className="hero-tag">
                ♻️ Cuidando do meio ambiente
              </span>

              <h1>
                Um lugar mais limpo
                <span> começa com você.</span>
              </h1>

              <p>
                O EcoPonto facilita o descarte correto de resíduos
                e aproxima a população dos serviços de coleta.
              </p>

              <div className="hero-buttons">

                <a href="/cadastro" className="button-primary">
                  👤 Criar minha conta
                </a>

                <a href="/mapa" className="button-secondary">
                  🗺️ Ver pontos de coleta
                </a>

              </div>

            </div>

          </div>
        </section>


        {/* FUNCIONALIDADES */}
        <section className="features">

          <div className="section-container">

            <div className="section-title">

              <span>O QUE VOCÊ PODE FAZER</span>

              <h2>
                Participe e ajude a cuidar
                <br />
                da nossa cidade
              </h2>

              <p>
                Com o EcoPonto, você tem acesso a ferramentas
                que ajudam a manter o ambiente mais limpo.
              </p>

            </div>


            <div className="cards">

              {/* CADASTRO */}
              <div className="feature-card">

                <div className="card-icon">
                  👤
                </div>

                <h3>Cadastro</h3>

                <p>
                  Crie sua conta para acessar as funcionalidades
                  do EcoPonto e participar da comunidade.
                </p>

                <a href="/cadastro">
                  Fazer cadastro →
                </a>

              </div>


              {/* MAPA */}
              <div className="feature-card">

                <div className="card-icon">
                  🗺️
                </div>

                <h3>Mapa de coleta</h3>

                <p>
                  Consulte os pontos de coleta e encontre
                  locais adequados para realizar o descarte.
                </p>

                <a href="/mapa">
                  Explorar mapa →
                </a>

              </div>


              {/* DENÚNCIA */}
              <div className="feature-card">

                <div className="card-icon">
                  📝
                </div>

                <h3>Criar denúncia</h3>

                <p>
                  Encontrou um local com acúmulo de lixo?
                  Registre uma denúncia e ajude a resolver o problema.
                </p>

                <a href="/denuncia">
                  Criar denúncia →
                </a>

              </div>


              {/* MINHAS DENÚNCIAS */}
              <div className="feature-card">

                <div className="card-icon">
                  📋
                </div>

                <h3>Minhas denúncias</h3>

                <p>
                  Acompanhe as denúncias realizadas e
                  consulte o andamento de cada solicitação.
                </p>

                <a href="/minhas-denuncias">
                  Ver denúncias →
                </a>

              </div>


              {/* CONTA */}
              <div className="feature-card">

                <div className="card-icon">
                  ⚙️
                </div>

                <h3>Minha conta</h3>

                <p>
                  Gerencie seus dados pessoais e mantenha
                  suas informações sempre atualizadas.
                </p>

                <a href="/conta">
                  Acessar conta →
                </a>

              </div>

            </div>

          </div>

        </section>


        {/* MAPA */}
        <section className="map-section">

          <div className="map-container">

            <div className="map-content">

              <span className="map-tag">
                🗺️ PONTOS DE COLETA
              </span>

              <h2>
                Encontre um ponto de coleta
                perto de você.
              </h2>

              <p>
                Consulte os locais disponíveis para descarte
                correto de resíduos e contribua para uma cidade
                mais limpa e sustentável.
              </p>

              <a href="/mapa" className="map-button">
                Explorar mapa →
              </a>

            </div>


            {/* REPRESENTAÇÃO DO MAPA */}
            <div className="map-preview">

              <div className="map-grid">

                <div className="map-road road-1"></div>
                <div className="map-road road-2"></div>
                <div className="map-road road-3"></div>

                <div className="map-location location-1">
                  ♻️
                </div>

                <div className="map-location location-2">
                  ♻️
                </div>

                <div className="map-location location-3">
                  ♻️
                </div>

                <div className="map-location location-4">
                  ♻️
                </div>

              </div>

              <div className="map-label">
                📍 Pontos de coleta
              </div>

            </div>

          </div>

        </section>


        {/* SOBRE */}
        <section className="about">

          <div className="about-container">

            <div className="about-content">

              <span className="about-tag">
                SOBRE O ECOPONTO
              </span>

              <h2>
                Pequenas atitudes podem
                gerar grandes mudanças.
              </h2>

              <p>
                O EcoPonto foi desenvolvido para facilitar a
                comunicação entre a população e os serviços
                responsáveis pela coleta de resíduos.
              </p>

              <p>
                A plataforma permite que os usuários encontrem
                informações importantes, realizem denúncias de
                locais com acúmulo de lixo e acompanhem suas
                solicitações.
              </p>

              <div className="about-items">

                <div>
                  <strong>♻️</strong>
                  <span>
                    Descarte mais consciente
                  </span>
                </div>

                <div>
                  <strong>🌱</strong>
                  <span>
                    Cuidado com o meio ambiente
                  </span>
                </div>

                <div>
                  <strong>🤝</strong>
                  <span>
                    Participação da comunidade
                  </span>
                </div>

              </div>

            </div>

          </div>

        </section>


        {/* CTA */}
        <section className="cta">

          <div className="cta-container">

            <div>

              <span>
                FAÇA PARTE DESSA MUDANÇA
              </span>

              <h2>
                Juntos podemos construir
                um lugar melhor.
              </h2>

              <p>
                Crie sua conta e participe do EcoPonto.
              </p>

            </div>

            <a href="/cadastro" className="cta-button">
              Criar minha conta →
            </a>

          </div>

        </section>

      </main>


      {/* RODAPÉ */}
      <footer className="footer">

        <div className="footer-container">

          <div className="footer-brand">

            <div className="footer-logo">

              <img
                src={logoEcoPonto}
                alt="EcoPonto"
              />

              <span>
                EcoPonto
              </span>

            </div>

            <p>
              Tecnologia e sustentabilidade
              para uma cidade melhor.
            </p>

          </div>


          <div className="footer-links">

            <h4>Navegação</h4>

            <a href="/">Início</a>
            <a href="/cadastro">Cadastro</a>
            <a href="/mapa">Mapa</a>
            <a href="/denuncia">Denúncias</a>
            <a href="/conta">Minha conta</a>

          </div>


          <div className="footer-links">

            <h4>EcoPonto</h4>

            <a href="/cadastro">
              Criar conta
            </a>

            <a href="/minhas-denuncias">
              Minhas denúncias
            </a>

          </div>

        </div>


        <div className="footer-bottom">

          <p>
            © 2026 EcoPonto. Projeto acadêmico de Ciência da Computação.
          </p>

        </div>

      </footer>

    </div>
  );
}

export default Home;
<template>
  <div class="cadastro-page">

    <!-- CONTEÚDO PRINCIPAL -->
    <main class="cadastro-main">

      <div class="cadastro-card">

        <!-- LOGO DO ECOPONTO -->
        <div class="cadastro-logo">
          <img
            src="../assets/logo-ecoponto.png"
            alt="Logo EcoPonto"
          />

          <h2>EcoPonto</h2>
        </div>


        <!-- TÍTULO -->
        <div class="cadastro-title">

          <span>FAÇA PARTE DO ECOPONTO</span>

          <h1>Crie sua conta</h1>

          <p>
            Cadastre-se para acessar todas as
            funcionalidades do EcoPonto.
          </p>

        </div>


        <!-- FORMULÁRIO -->
        <form class="cadastro-form">

          <!-- NOME -->
          <div class="form-group">

            <label for="nome">
              Nome completo
            </label>

            <div class="input-container">
              <span class="input-icon">♙</span>

              <input
                id="nome"
                type="text"
                placeholder="Digite seu nome completo"
              />
            </div>

          </div>


          <!-- E-MAIL -->
          <div class="form-group">

            <label for="email">
              E-mail
            </label>

            <div class="input-container">
              <span class="input-icon">✉</span>

              <input
                id="email"
                type="email"
                placeholder="Digite seu e-mail"
              />
            </div>

          </div>


          <!-- SENHA -->
          <div class="form-group">

            <label for="senha">
              Senha
            </label>

            <div class="input-container">
              <span class="input-icon">♙</span>

              <input
                id="senha"
                type="password"
                placeholder="Digite sua senha"
              />
            </div>

          </div>


          <!-- CONFIRMAR SENHA -->
          <div class="form-group">

            <label for="confirmarSenha">
              Confirmar senha
            </label>

            <div class="input-container">
              <span class="input-icon">♙</span>

              <input
                id="confirmarSenha"
                type="password"
                placeholder="Digite sua senha novamente"
              />
            </div>

          </div>


          <!-- TERMOS -->
          <div class="termos">

            <input
              id="termos"
              type="checkbox"
            />

            <label for="termos">
              Concordo com os
              <strong>termos de uso e política</strong>
              de privacidade do EcoPonto.
            </label>

          </div>


          <!-- BOTÃO -->
          <button
            type="submit"
            class="cadastro-button"
          >
            Criar minha conta
          </button>

        </form>


        <!-- LOGIN -->
        <div class="ja-tem-conta">

          <span>
            Já possui uma conta?
          </span>

          <a href="#">
            Entrar
          </a>

        </div>

      </div>

    </main>


    <!-- RODAPÉ -->
    <footer class="cadastro-footer">

      <p>
        © 2026 EcoPonto — Projeto acadêmico de Ciência da Computação.
      </p>

    </footer>

  </div>
</template>


<style scoped>

/* =========================
   PÁGINA
========================= */

.cadastro-page {
  min-height: 100vh;

  display: flex;
  flex-direction: column;

  background: #f2f9f3;

  color: #174333;
}


/* =========================
   ÁREA PRINCIPAL
========================= */

.cadastro-main {
  flex: 1;

  display: flex;
  justify-content: center;
  align-items: flex-start;

  padding: 45px 20px 30px;
}


/* =========================
   CARD
========================= */

.cadastro-card {
  width: 100%;
  max-width: 700px;

  padding: 42px 55px;

  background: #ffffff;

  border-radius: 20px;

  box-shadow:
    0 15px 45px rgba(23, 92, 50, 0.10);

  border: 1px solid #e2eee5;
}


/* =========================
   LOGO
========================= */

.cadastro-logo {
  display: flex;

  flex-direction: column;

  align-items: center;

  justify-content: center;

  margin-bottom: 28px;
}

.cadastro-logo img {
  width: 105px;
  height: 105px;

  object-fit: contain;

  margin-bottom: 5px;
}

.cadastro-logo h2 {
  margin: 0;

  color: #168343;

  font-size: 30px;

  font-weight: 800;

  letter-spacing: -1px;
}


/* =========================
   TÍTULO
========================= */

.cadastro-title {
  text-align: center;

  margin-bottom: 32px;
}

.cadastro-title span {
  display: block;

  color: #18864a;

  font-size: 15px;

  font-weight: 800;

  letter-spacing: 2px;

  margin-bottom: 8px;
}

.cadastro-title h1 {
  margin: 0 0 12px;

  color: #174333;

  font-size: 46px;

  line-height: 1.1;

  font-weight: 800;

  letter-spacing: -1px;
}

.cadastro-title p {
  max-width: 560px;

  margin: 0 auto;

  color: #61776d;

  font-size: 20px;

  line-height: 1.5;
}


/* =========================
   FORMULÁRIO
========================= */

.cadastro-form {
  display: flex;

  flex-direction: column;

  gap: 22px;
}


/* =========================
   CAMPOS
========================= */

.form-group {
  display: flex;

  flex-direction: column;

  gap: 9px;
}

.form-group label {
  color: #174b38;

  font-size: 19px;

  font-weight: 750;
}


/* CONTAINER DO INPUT */

.input-container {
  display: flex;

  align-items: center;

  width: 100%;

  min-height: 65px;

  border: 1.5px solid #cbdcd3;

  border-radius: 11px;

  background: #ffffff;

  transition: 0.2s;
}

.input-container:focus-within {
  border-color: #218c51;

  box-shadow:
    0 0 0 4px rgba(33, 140, 81, 0.10);
}


/* ÍCONE */

.input-icon {
  width: 65px;

  display: flex;

  align-items: center;

  justify-content: center;

  color: #376a58;

  font-size: 28px;

  flex-shrink: 0;
}


/* INPUT */

.input-container input {
  width: 100%;

  height: 63px;

  border: none;

  outline: none;

  background: transparent;

  padding: 0 18px 0 0;

  color: #174333;

  font-size: 18px;
}

.input-container input::placeholder {
  color: #8998a1;

  font-size: 18px;
}


/* =========================
   TERMOS
========================= */

.termos {
  display: flex;

  align-items: flex-start;

  gap: 13px;

  margin-top: 2px;
}

.termos input {
  width: 22px;

  height: 22px;

  margin-top: 3px;

  flex-shrink: 0;

  accent-color: #168343;

  cursor: pointer;
}

.termos label {
  color: #476258;

  font-size: 16px;

  line-height: 1.5;

  cursor: pointer;
}

.termos strong {
  color: #168343;

  font-weight: 800;
}


/* =========================
   BOTÃO
========================= */

.cadastro-button {
  width: 100%;

  min-height: 64px;

  border: none;

  border-radius: 11px;

  background: #199653;

  color: #ffffff;

  font-size: 21px;

  font-weight: 800;

  cursor: pointer;

  transition: 0.2s;
}

.cadastro-button:hover {
  background: #137a41;

  transform: translateY(-1px);

  box-shadow:
    0 7px 18px rgba(25, 150, 83, 0.20);
}

.cadastro-button:active {
  transform: translateY(0);
}


/* =========================
   LOGIN
========================= */

.ja-tem-conta {
  display: flex;

  align-items: center;

  justify-content: center;

  gap: 7px;

  margin-top: 28px;

  font-size: 17px;
}

.ja-tem-conta span {
  color: #52685f;
}

.ja-tem-conta a {
  color: #168343;

  font-weight: 800;

  text-decoration: none;
}

.ja-tem-conta a:hover {
  text-decoration: underline;
}


/* =========================
   RODAPÉ
========================= */

.cadastro-footer {
  padding: 22px 20px;

  text-align: center;

  background: transparent;
}

.cadastro-footer p {
  margin: 0;

  color: #668078;

  font-size: 13px;
}


/* =========================
   RESPONSIVIDADE
========================= */

@media (max-width: 700px) {

  .cadastro-main {
    padding: 25px 15px 20px;
  }

  .cadastro-card {
    padding: 32px 22px;

    border-radius: 16px;
  }

  .cadastro-logo img {
    width: 80px;

    height: 80px;
  }

  .cadastro-logo h2 {
    font-size: 25px;
  }

  .cadastro-title span {
    font-size: 12px;

    letter-spacing: 1.5px;
  }

  .cadastro-title h1 {
    font-size: 35px;
  }

  .cadastro-title p {
    font-size: 16px;
  }

  .form-group label {
    font-size: 16px;
  }

  .input-container {
    min-height: 58px;
  }

  .input-container input {
    height: 56px;

    font-size: 16px;
  }

  .input-container input::placeholder {
    font-size: 15px;
  }

  .input-icon {
    width: 52px;

    font-size: 23px;
  }

  .termos label {
    font-size: 14px;
  }

  .cadastro-button {
    min-height: 58px;

    font-size: 18px;
  }

  .ja-tem-conta {
    font-size: 15px;
  }
}

</style>
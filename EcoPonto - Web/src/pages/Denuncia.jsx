function Denuncia() {
  return (
    <div>
      <h1>Criar denúncia 🚨</h1>

      <p>
        Encontrou um local com acúmulo de lixo?
        Informe o problema.
      </p>

      <input
        type="text"
        placeholder="Local da ocorrência"
      />

      <br />

      <textarea
        placeholder="Descreva o problema"
      />

      <br />

      <button>
        Enviar denúncia
      </button>
    </div>
  );
}

export default Denuncia;
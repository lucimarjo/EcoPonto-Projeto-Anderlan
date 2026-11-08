
function StatCard({
  titulo,
  valor,
  unidade,
  icone,
  cor,
  variacao,
  descricao,
  tipoVariacao = "positive",
}) {
  return (
    <article className="eco-kpi-card">
      <div className="eco-kpi-top">
        <span>{titulo}</span>

        <div className={`eco-kpi-icon ${cor}`}>
          <span className="eco-icon" aria-hidden="true">
            {icone}
          </span>
        </div>
      </div>

      <strong>
        {valor}
        {unidade && <small> {unidade}</small>}
      </strong>

      <div className="eco-kpi-footer">
        <span className={`eco-${tipoVariacao}`}>
          {variacao}
        </span>

        <span>{descricao}</span>
      </div>
    </article>
  );
}

export default StatCard;

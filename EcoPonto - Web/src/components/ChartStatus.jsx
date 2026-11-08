
function ChartStatus() {
  return (
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
  );
}

export default ChartStatus;

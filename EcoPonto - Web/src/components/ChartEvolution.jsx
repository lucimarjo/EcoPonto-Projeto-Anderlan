
function ChartEvolution() {
  const dados = [
    ["Seg", 42],
    ["Ter", 65],
    ["Qua", 52],
    ["Qui", 83],
    ["Sex", 62],
    ["Sáb", 95],
    ["Dom", 72],
  ];

  return (
    <article className="eco-panel eco-evolution">
      <div className="eco-panel-heading">
        <div>
          <h2>Visão geral das denúncias</h2>
          <p>Acompanhe a evolução das ocorrências.</p>
        </div>

        <span className="eco-chart-legend">
          <i /> Denúncias recebidas
        </span>
      </div>

      <div className="eco-bar-chart">
        <div className="eco-y-labels">
          <span>40</span>
          <span>30</span>
          <span>20</span>
          <span>10</span>
          <span>0</span>
        </div>

        <div className="eco-chart-area">
          <div className="eco-chart-lines">
            {[1, 2, 3, 4, 5].map((line) => (
              <span key={line} />
            ))}
          </div>

          <div className="eco-bars">
            {dados.map(([dia, altura]) => (
              <div className="eco-bar-column" key={dia}>
                <div className="eco-bar-track">
                  <div
                    className="eco-bar"
                    style={{ height: `${altura}%` }}
                  />
                </div>
                <span>{dia}</span>
              </div>
            ))}
          </div>
        </div>
      </div>
    </article>
  );
}

export default ChartEvolution;

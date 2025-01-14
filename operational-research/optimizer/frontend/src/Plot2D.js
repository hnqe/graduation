import React from 'react';
import Plot from 'react-plotly.js';

function Plot2D({ constraints, solution, xRange = [0, 10], yRange = [0, 10] }) {
  const step = 0.1;
  let lines = [];
  let feasibleRegion = [];
  let extremePoints = [];

  // 1) Gerar linhas para cada restrição
  constraints.forEach((constr, idx) => {
    const { a1, a2, op, b } = constr;

    if (a2 !== 0) {
      let lineX = [];
      let lineY = [];
      for (let x = xRange[0]; x <= xRange[1]; x += step) {
        const y = (b - a1 * x) / a2;
        lineX.push(x);
        lineY.push(y);
      }
      lines.push({
        x: lineX,
        y: lineY,
        mode: 'lines',
        name: `Restrição #${idx + 1}: ${a1}x + ${a2}y ${op} ${b}`,
      });
    } else {
      // a2 == 0 => reta vertical
      if (a1 !== 0) {
        const X = b / a1;
        let lineY = [];
        for (let y = yRange[0]; y <= yRange[1]; y += step) {
          lineY.push(y);
        }
        let lineX = lineY.map(() => X);
        lines.push({
          x: lineX,
          y: lineY,
          mode: 'lines',
          name: `Restrição #${idx + 1}: ${a1}x + ${a2}y ${op} ${b}`,
        });
      }
    }
  });

  // 2) Identificar interseções e pontos de borda
  const isFeasiblePoint = (x, y) => {
    return constraints.every(({ a1, a2, op, b }) => {
      const value = a1 * x + a2 * y;
      return (
        (op === "<=" && value <= b) ||
        (op === ">=" && value >= b) ||
        (op === "==" && value === b)
      );
    });
  };

  const intersections = [];

  for (let i = 0; i < constraints.length; i++) {
    for (let j = i + 1; j < constraints.length; j++) {
      const { a1: a1i, a2: a2i, b: bi } = constraints[i];
      const { a1: a1j, a2: a2j, b: bj } = constraints[j];

      const determinant = a1i * a2j - a1j * a2i;
      if (determinant !== 0) {
        const x = (bi * a2j - bj * a2i) / determinant;
        const y = (a1i * bj - a1j * bi) / determinant;

        // Verifica se está dentro dos limites
        if (x >= xRange[0] && x <= xRange[1] && y >= yRange[0] && y <= yRange[1]) {
          intersections.push({ x, y });
        }
      }
    }
  }

  // Adicionar interseções com os eixos
  constraints.forEach(({ a1, a2, b }) => {
    if (a1 !== 0) {
      const x = b / a1;
      if (x >= xRange[0] && x <= xRange[1]) intersections.push({ x, y: 0 });
    }
    if (a2 !== 0) {
      const y = b / a2;
      if (y >= yRange[0] && y <= yRange[1]) intersections.push({ x: 0, y });
    }
  });

  // Filtrar pontos extremos dentro da região factível
  extremePoints = intersections.filter(({ x, y }) => isFeasiblePoint(x, y));

  const extremePointsTrace = {
    x: extremePoints.map((p) => p.x),
    y: extremePoints.map((p) => p.y),
    mode: 'markers',
    marker: {
      color: 'blue',
      size: 8,
      symbol: 'circle',
    },
    name: 'Pontos Extremos',
  };

  // 3) Gerar região factível
  let feasibleX = [];
  let feasibleY = [];
  for (let x = xRange[0]; x <= xRange[1]; x += step) {
    for (let y = yRange[0]; y <= yRange[1]; y += step) {
      if (isFeasiblePoint(x, y)) {
        feasibleX.push(x);
        feasibleY.push(y);
      }
    }
  }

  const feasibleRegionTrace = {
    x: feasibleX,
    y: feasibleY,
    mode: 'markers',
    marker: {
      color: 'rgba(0, 200, 0, 0.3)', // Verde claro transparente
      size: 3,
    },
    name: 'Região Factível',
  };

  // 4) Adicionar solução ótima como ponto vermelho
  const solutionTrace = {
    x: [solution.x1],
    y: [solution.x2],
    mode: 'markers',
    marker: { color: 'red', size: 10 },
    name: 'Solução Ótima',
  };

  // 5) Layout
  const layout = {
    title: 'Região Factível (2D)',
    xaxis: { range: xRange, title: 'x1' },
    yaxis: { range: yRange, title: 'x2' },
    showlegend: true,
    autosize: true,
  };

  return (
    <Plot
      data={[...lines, feasibleRegionTrace, extremePointsTrace, solutionTrace]}
      layout={layout}
      useResizeHandler={true}
      style={{
        width: '100%',
        height: '60vh',
      }}
    />
  );
}

export default Plot2D;
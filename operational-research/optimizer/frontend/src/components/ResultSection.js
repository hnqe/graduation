import React from 'react';
import Plot2D from '../Plot2D'; 

// Esse componente mostra o status, função objetivo, valores das variáveis e,
// se tiver 2 variáveis, exibe o gráfico.

function ResultSection({ solution, mappedConstraints }) {
  if (!solution) return null; // se não tiver solução ainda, não exibe nada

  return (
    <div
      style={{
        backgroundColor: '#ffffff',
        padding: '2rem',
        marginTop: '2rem',
        borderRadius: '10px',
        boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
        maxWidth: '800px',
        marginLeft: 'auto',
        marginRight: 'auto',
        textAlign: 'left',
      }}
    >
      <h2
        style={{
          color: '#1d3557',
          fontWeight: 'bold',
          textAlign: 'center',
          marginBottom: '1rem',
        }}
      >
        Resultado
      </h2>

      <p style={{ fontSize: '1.2rem', color: '#333', marginBottom: '1rem' }}>
        <strong style={{ color: '#007bff' }}>Status:</strong> {solution.status}
      </p>
      <p style={{ fontSize: '1.2rem', color: '#333', marginBottom: '1rem' }}>
        <strong style={{ color: '#007bff' }}>Função Objetivo:</strong> {solution.objective}
      </p>
      <p style={{ fontSize: '1.2rem', color: '#333', marginBottom: '1rem' }}>
        <strong style={{ color: '#007bff' }}>Variáveis:</strong>
      </p>
      <ul style={{ listStyleType: 'none', padding: 0 }}>
        {solution.variables.map((val, idx) => (
          <li
            key={idx}
            style={{
              background: '#f1f5f9',
              margin: '0.5rem 0',
              padding: '0.7rem 1rem',
              borderRadius: '6px',
              borderLeft: '4px solid #007bff',
              fontSize: '1.2rem',
            }}
          >
            x{idx + 1} = {val}
          </li>
        ))}
      </ul>

      {/* Exibir gráfico 2D se houver 2 variáveis */}
      {solution.variables.length === 2 && (
        <div
          style={{
            backgroundColor: '#ffffff',
            padding: '1rem',
            marginTop: '2rem',
            borderRadius: '10px',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
          }}
        >
          <Plot2D
            constraints={mappedConstraints}
            solution={{
              x1: solution.variables[0],
              x2: solution.variables[1]
            }}
            xRange={[0, 10]}
            yRange={[0, 10]}
          />
        </div>
      )}
    </div>
  );
}

export default ResultSection;

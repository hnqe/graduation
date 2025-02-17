import React from 'react';
import { Table } from 'react-bootstrap';

function GAStatsTable({ history }) {
  // Se não há histórico, não renderiza nada (ou poderia renderizar mensagem)
  if (!history || history.length === 0) return null;

  return (
    <Table striped bordered hover size="sm">
      <thead>
        <tr>
          <th>Geração</th>
          <th>Melhor (Global)</th>
          <th>Mínimo</th>
          <th>Média</th>
          <th>Desvio Padrão</th>
        </tr>
      </thead>
      <tbody>
        {history.map((genData) => (
          <tr key={genData.generation}>
            <td>{genData.generation}</td>
            <td>{genData.best_fitness}</td>
            <td>{genData.min_fitness}</td>
            <td>{genData.avg_fitness.toFixed(2)}</td>
            <td>{genData.std_fitness.toFixed(2)}</td>
          </tr>
        ))}
      </tbody>
    </Table>
  );
}

export default GAStatsTable;
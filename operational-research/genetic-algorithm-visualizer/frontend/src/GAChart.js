import React from 'react';
import {
  LineChart, Line, XAxis, YAxis, CartesianGrid, Tooltip, Legend, ResponsiveContainer
} from 'recharts';

function GAChart({ history }) {
  if (!history || history.length === 0) {
    return <p style={{ textAlign: 'center' }}>Nenhum dado disponível ainda.</p>;
  }

  return (
    <ResponsiveContainer width="100%" height="100%">
      <LineChart data={history}>
        <CartesianGrid strokeDasharray="3 3" />
        <XAxis dataKey="generation" />
        <YAxis />
        <Tooltip />
        <Legend />

        {/* Melhor global (roxo) */}
        <Line
          type="monotone"
          dataKey="best_fitness"
          stroke="#8884d8"
          activeDot={{ r: 8 }}
          name="Melhor (Global)"
        />

        {/* Média (verde) */}
        <Line
          type="monotone"
          dataKey="avg_fitness"
          stroke="#82ca9d"
          name="Média"
        />

        {/* Mínimo (vermelho) */}
        <Line
          type="monotone"
          dataKey="min_fitness"
          stroke="#ff7f7f"
          name="Mínimo"
        />

        {/* Desvio padrão (laranja) */}
        <Line
          type="monotone"
          dataKey="std_fitness"
          stroke="#ffa500"
          name="Desvio Padrão"
        />
      </LineChart>
    </ResponsiveContainer>
  );
}

export default GAChart;
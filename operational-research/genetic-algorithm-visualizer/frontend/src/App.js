import React, { useState } from 'react';
import GAChart from './GAChart';
import GAStatsTable from './GAStatsTable';
import { Container, Row, Col, Form, Button, Card } from 'react-bootstrap';

function App() {
  const [history, setHistory] = useState([]);
  const [bestFitness, setBestFitness] = useState(null);
  const [bestSolution, setBestSolution] = useState([]);

  // Parâmetros simples
  const [popSize, setPopSize] = useState(30);
  const [maxGenerations, setMaxGenerations] = useState(100);
  const [mutationRate, setMutationRate] = useState(0.05);
  const [crossoverRate, setCrossoverRate] = useState(0.8);
  const [capacity, setCapacity] = useState(50);

  // Em vez de string, array de { value, weight }
  const [items, setItems] = useState([
    { value: 60, weight: 10 },
    { value: 100, weight: 20 },
    { value: 120, weight: 30 },
    { value: 80, weight: 15 },
    { value: 90, weight: 18 },
  ]);

  // Funções para manipular array de itens
  function handleItemChange(index, field, newValue) {
    setItems((prev) => {
      const newItems = [...prev];
      newItems[index] = {
        ...newItems[index],
        [field]: parseInt(newValue || '0', 10),
      };
      return newItems;
    });
  }

  function handleAddItem() {
    setItems((prev) => [...prev, { value: 0, weight: 0 }]);
  }

  function handleRemoveItem(idx) {
    setItems((prev) => prev.filter((_, i) => i !== idx));
  }

  const handleRunGA = (e) => {
    e.preventDefault();
    const bodyData = {
      pop_size: parseInt(popSize),
      max_generations: parseInt(maxGenerations),
      mutation_rate: parseFloat(mutationRate),
      crossover_rate: parseFloat(crossoverRate),
      capacity: parseInt(capacity),
      items: items  // agora um array de obj
    };

    fetch('http://localhost:5000/run_ga', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(bodyData)
    })
      .then(response => {
        if (!response.ok) {
          throw new Error(`Erro na requisição: ${response.status}`);
        }
        return response.json();
      })
      .then(data => {
        setHistory(data.history);
        setBestFitness(data.best_fitness);
        setBestSolution(data.best_solution);
      })
      .catch(err => console.error(err));
  };

  return (
    <Container className="my-4">
      <Row className="mb-1">
        <Col>
          <h2 className="text-center">Algoritmo Genético - Knapsack</h2>
        </Col>
      </Row>

      <Row>
        <Col md={4}>
          <Card>
            <Card.Header><strong>Parâmetros do GA</strong></Card.Header>
            <Card.Body>
              <Form onSubmit={handleRunGA}>
                <Form.Group className="mb-3">
                  <Form.Label>Tamanho da População</Form.Label>
                  <Form.Control
                    type="number"
                    value={popSize}
                    onChange={(e) => setPopSize(e.target.value)}
                  />
                </Form.Group>

                <Form.Group className="mb-3">
                  <Form.Label>Máx. Gerações</Form.Label>
                  <Form.Control
                    type="number"
                    value={maxGenerations}
                    onChange={(e) => setMaxGenerations(e.target.value)}
                  />
                </Form.Group>

                <Form.Group className="mb-3">
                  <Form.Label>Taxa de Mutação</Form.Label>
                  <Form.Control
                    type="number"
                    step="0.01"
                    value={mutationRate}
                    onChange={(e) => setMutationRate(e.target.value)}
                  />
                </Form.Group>

                <Form.Group className="mb-3">
                  <Form.Label>Taxa de Crossover</Form.Label>
                  <Form.Control
                    type="number"
                    step="0.01"
                    value={crossoverRate}
                    onChange={(e) => setCrossoverRate(e.target.value)}
                  />
                </Form.Group>

                <Form.Group className="mb-3">
                  <Form.Label>Capacidade da Mochila (W)</Form.Label>
                  <Form.Control
                    type="number"
                    value={capacity}
                    onChange={(e) => setCapacity(e.target.value)}
                  />
                </Form.Group>

                {/* Form Dinâmico de Itens */}
                <Form.Group className="mb-3">
                  <Form.Label>Itens (valor/peso)</Form.Label>
                  {items.map((item, index) => (
                    <div key={index} style={{ display: 'flex', gap: '8px', marginBottom: '4px' }}>
                      <Form.Control
                        type="number"
                        value={item.value}
                        onChange={(e) => handleItemChange(index, 'value', e.target.value)}
                        placeholder="Valor"
                        style={{ width: '40%' }}
                      />
                      <Form.Control
                        type="number"
                        value={item.weight}
                        onChange={(e) => handleItemChange(index, 'weight', e.target.value)}
                        placeholder="Peso"
                        style={{ width: '40%' }}
                      />
                      <Button variant="danger" onClick={() => handleRemoveItem(index)}>x</Button>
                    </div>
                  ))}
                  <Button variant="secondary" onClick={handleAddItem}>
                    Adicionar Item
                  </Button>
                </Form.Group>

                <Button variant="primary" type="submit" className="w-100">
                  Executar GA
                </Button>
              </Form>
            </Card.Body>
          </Card>
        </Col>

        <Col md={8}>
          {/* Resultados */}
          <Card className="mb-3">
            <Card.Body>
              <Card.Title>Melhor Resultado</Card.Title>
              {bestFitness !== null ? (
                <>
                  <h4 className="text-success">
                    Melhor Fitness: {bestFitness}
                  </h4>
                  <p>
                    <strong>Melhor Solução:</strong> [{bestSolution.join(', ')}]
                  </p>
                </>
              ) : (
                <p className="text-muted">Nenhum resultado ainda.</p>
              )}
            </Card.Body>
          </Card>

          {/* Gráfico */}
          <Card>
            <Card.Body>
              <Card.Title>Evolução do Fitness</Card.Title>
              <div style={{ width: '100%', height: '400px' }}>
                <GAChart history={history} />
              </div>
            </Card.Body>
          </Card>

          {/* Tabela de Estatísticas (opcional) */}
          {history.length > 0 && (
            <Card className="mt-3">
              <Card.Body>
                <Card.Title>Estatísticas Detalhadas</Card.Title>
                <GAStatsTable history={history} />
              </Card.Body>
            </Card>
          )}
        </Col>
      </Row>
    </Container>
  );
}

export default App;
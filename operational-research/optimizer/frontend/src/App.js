import React, { useState } from 'react';

import ObjectiveForm from './components/ObjectiveForm';
import ConstraintsForm from './components/ConstraintsForm';
import ResultSection from './components/ResultSection';

function App() {
  // ----------------------------------
  // Estados
  // ----------------------------------
  const [objectiveType, setObjectiveType] = useState("maximize");
  const [objCoefs, setObjCoefs] = useState([""]);
  const [constraints, setConstraints] = useState([
    { coefficients: [""], operator: "<=", rhs: "" }
  ]);
  const [variableBounds, setVariableBounds] = useState([{ min: 0, max: null }]);
  const [solution, setSolution] = useState(null);

  // ----------------------------------
  // Função de SUBMIT (enviar ao back-end)
  // ----------------------------------
  const handleSubmit = (e) => {
    e.preventDefault();
    
    const payload = {
      objectiveFunction: {
        type: objectiveType,
        coefficients: objCoefs.map(Number),
      },
      constraints: constraints.map((c) => ({
        coefficients: c.coefficients.map(Number),
        operator: c.operator,
        rhs: Number(c.rhs),
      })),
      variableBounds: variableBounds.map((b) => ({
        min: b.min !== "" ? (b.min !== null ? Number(b.min) : null) : null,
        max: b.max !== "" ? (b.max !== null ? Number(b.max) : null) : null,
      })),
    };
    
    fetch("http://localhost:5000/optimize", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    })
      .then((res) => res.json())
      .then((data) => {
        setSolution(data);
        console.log("Resultado da Otimização:", data);
      })
      .catch((err) => {
        console.error("Erro ao chamar /optimize:", err);
      });
  };

  // ----------------------------------
  // Adicionar / Remover Variáveis
  // ----------------------------------
  const addVariable = () => {
    setObjCoefs([...objCoefs, ""]);
    setVariableBounds([...variableBounds, { min: 0, max: null }]);
    const newConstraints = constraints.map((c) => ({
      ...c,
      coefficients: [...c.coefficients, ""],
    }));
    setConstraints(newConstraints);
  };

  const removeVariable = (index) => {
    if (objCoefs.length <= 1) return; // não remover se só tem 1

    const newObjCoefs = [...objCoefs];
    newObjCoefs.splice(index, 1);
    setObjCoefs(newObjCoefs);

    const newVarBounds = [...variableBounds];
    newVarBounds.splice(index, 1);
    setVariableBounds(newVarBounds);

    const newConstraints = constraints.map((c) => {
      const newCoefficients = [...c.coefficients];
      newCoefficients.splice(index, 1);
      return { ...c, coefficients: newCoefficients };
    });
    setConstraints(newConstraints);
  };

  // ----------------------------------
  // Adicionar / Remover Restrições
  // ----------------------------------
  const addConstraint = () => {
    const newConstraint = {
      coefficients: objCoefs.map(() => ""),
      operator: "<=",
      rhs: ""
    };
    setConstraints([...constraints, newConstraint]);
  };

  const removeConstraint = (index) => {
    if (constraints.length <= 1) return;
    const newConstraints = [...constraints];
    newConstraints.splice(index, 1);
    setConstraints(newConstraints);
  };

  // ----------------------------------
  // Adicionar Não-Negatividade
  // ----------------------------------
  const addNonNegativityConstraints = () => {
    let updatedConstraints = [...constraints];
    const numVars = objCoefs.length;

    for (let i = 0; i < numVars; i++) {
      const nonNegCoef = Array(numVars).fill(0);
      nonNegCoef[i] = 1;

      const alreadyExists = updatedConstraints.some((constr) => {
        if (constr.operator !== ">=") return false;
        if (Number(constr.rhs) !== 0) return false;
        if (constr.coefficients.length !== numVars) return false;
        return constr.coefficients.every((cc, idx) => {
          const numCC = Number(cc);
          return (idx === i) ? (numCC === 1) : (numCC === 0);
        });
      });

      if (!alreadyExists) {
        const newConstraint = {
          coefficients: nonNegCoef.map(String),
          operator: ">=",
          rhs: "0",
        };
        updatedConstraints.push(newConstraint);
      }
    }

    setConstraints(updatedConstraints);
  };

  // ----------------------------------
  // 1) Carregar Exemplo
  // ----------------------------------
  const loadExample = () => {
    // Exemplo clássico: Max 5x1 + 3x2
    // s.t. x1 <= 4, 2x2 <= 12, 3x1+2x2 <=18, x1,x2 >=0
    setObjectiveType("maximize");
    setObjCoefs(["5", "3"]);
    setConstraints([
      { coefficients: ["1","0"], operator: "<=", rhs: "4" },
      { coefficients: ["0","2"], operator: "<=", rhs: "12" },
      { coefficients: ["3","2"], operator: "<=", rhs: "18" }
    ]);
    setVariableBounds([
      { min: 0, max: null },
      { min: 0, max: null }
    ]);
    setSolution(null);
  };

  // ----------------------------------
  // 2) Resetar Tudo
  // ----------------------------------
  const resetAll = () => {
    setObjectiveType("maximize");
    setObjCoefs([""]);
    setConstraints([
      { coefficients: [""], operator: "<=", rhs: "" }
    ]);
    setVariableBounds([{ min: 0, max: null }]);
    setSolution(null);
  };

  // ----------------------------------
  // Mapeamento p/ Gráfico (2 variáveis)
  // ----------------------------------
  const mappedConstraints = constraints.map((c) => {
    const a1 = Number(c.coefficients[0]) || 0;
    const a2 = Number(c.coefficients[1]) || 0;
    const op = c.operator;
    const b  = Number(c.rhs) || 0;
    return { a1, a2, op, b };
  });

  // ----------------------------------
  // Render
  // ----------------------------------
  return (
    <div style={{ margin: "2rem" }}>
      <div className="container">
        
        <h1 className="text-primary my-4">Formulário de Otimização</h1>

        <form onSubmit={handleSubmit} className="card p-4 shadow-sm">
          {/* Formulário da Função Objetivo */}
          <ObjectiveForm
            objectiveType={objectiveType}
            setObjectiveType={setObjectiveType}
            objCoefs={objCoefs}
            setObjCoefs={setObjCoefs}
            addVariable={addVariable}
            removeVariable={removeVariable}
          />

          {/* Formulário de Restrições */}
          <ConstraintsForm
            constraints={constraints}
            setConstraints={setConstraints}
            objCoefs={objCoefs}
            addConstraint={addConstraint}
            removeConstraint={removeConstraint}
            addNonNegativityConstraints={addNonNegativityConstraints}
          />

          {/* Botão de Resolver */}
          <button type="submit" className="btn btn-primary mt-2">
            Resolver
          </button>

          {/* Botões Extras: Carregar Exemplo e Resetar */}
          <div className="mt-3">
            <button
              type="button"
              className="btn btn-info me-2"
              onClick={loadExample}
            >
              Carregar Exemplo
            </button>
            <button
              type="button"
              className="btn btn-warning"
              onClick={resetAll}
            >
              Resetar Tudo
            </button>
          </div>
        </form>
      </div>

      {/* Se houver solução, exibe ResultSection */}
      <ResultSection
        solution={solution}
        mappedConstraints={mappedConstraints}
      />
    </div>
  );
}

export default App;
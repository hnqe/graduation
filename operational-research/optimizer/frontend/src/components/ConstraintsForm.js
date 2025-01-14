import React from 'react';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faPlus, faMinus } from '@fortawesome/free-solid-svg-icons';

// Este componente cuida da lista de restrições, com seus coeficientes, operadores,
// termo independente, além dos botões de adicionar/remover restrição e adicionar não negatividade.

function ConstraintsForm({
  constraints,
  setConstraints,
  objCoefs,
  addConstraint,
  removeConstraint,
  addNonNegativityConstraints
}) {
  return (
    <div>
      <h2 className="text-secondary">Restrições</h2>
      {constraints.map((constr, i) => (
        <div key={i} className="mb-3">
          {constr.coefficients.map((val, j) => (
            <input
              key={j}
              type="number"
              step="any"
              value={val}
              onChange={(e) => {
                const newConstrs = [...constraints];
                newConstrs[i].coefficients[j] = e.target.value;
                setConstraints(newConstrs);
              }}
              className="form-control d-inline w-25 me-2"
              placeholder={`Coef x${j + 1}`}
            />
          ))}
          <select
            value={constr.operator}
            onChange={(e) => {
              const newConstrs = [...constraints];
              newConstrs[i].operator = e.target.value;
              setConstraints(newConstrs);
            }}
            className="form-select d-inline w-auto me-2"
          >
            <option value="<=">&le;</option>
            <option value=">=">&ge;</option>
            <option value="==">=</option>
          </select>
          <input
            type="number"
            step="any"
            value={constr.rhs}
            onChange={(e) => {
              const newConstrs = [...constraints];
              newConstrs[i].rhs = e.target.value;
              setConstraints(newConstrs);
            }}
            className="form-control d-inline w-25 me-2"
            placeholder="Termo independente"
          />

          {/* Botão de remover restrição individual */}
          {constraints.length > 1 && (
            <button
              type="button"
              className="btn btn-outline-danger"
              onClick={() => removeConstraint(i)}
            >
              <FontAwesomeIcon icon={faMinus} />
            </button>
          )}
        </div>
      ))}

      <div className="mb-3">
        {/* Botão de adicionar restrição normal */}
        <button
          type="button"
          onClick={addConstraint}
          className="btn btn-outline-primary me-2"
        >
          <FontAwesomeIcon icon={faPlus} /> Restrição
        </button>

        {/* Botão de adicionar não negatividade para todas as variáveis */}
        <button
          type="button"
          className="btn btn-success"
          onClick={addNonNegativityConstraints}
        >
          Adicionar Não-Negatividade
        </button>
      </div>
    </div>
  );
}

export default ConstraintsForm;
